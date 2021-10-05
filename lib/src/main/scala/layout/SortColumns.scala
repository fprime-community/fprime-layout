package fpl.layout

import fpl.topology._

object SortColumns {

  /** Sort the column of cv at index i */
  def sort(top: Topology)(sortOn: Port.Kind)(cv: ColumnVector, i: Int): ColumnVector.Column = {
    val portRanks = PortRanks.visitColumnVector(PortRanks.State(top))(cv)
    val c0 = cv.columns(i)
    val portVectorScores = PortVectorScores.visitColumn(
      PortVectorScores.State(portRanks)
    )(c0)
    val instanceScores = InstanceScores.visitColumn(
      InstanceScores.State(portVectorScores)
    )(c0)
    val c1 = SortInstances.transformColumn(
      SortInstances.State(sortOn, instanceScores)
    )(c0)
    val c2 = SortPortVectors.transformColumn(
      SortPortVectors.State(sortOn, portVectorScores)
    )(c1)
    c2
  }

  private def find(p: (Int, Int) => Boolean)(vector: Vector[Int]): Int =
    vector.fold(vector(0))((a, b) => if (p(b, a)) b else a)

  private val findMin = find(_ < _) _

  private val findMax = find(_ > _) _

  /** Computes a rank for each port.
   *  Input ports: Ports that are down and to the right have higher rank.
   *  Output ports: Ports that are down have higher rank. */
  private object PortRanks extends ColumnVectorVisitor {

    case class State(
      val top: Topology,
      val ranks: Map[Port, Int] = Map(),
      val nextRankMap: Map[Port.Kind, Int] = Map(Port.Input -> 0, Port.Output -> 0)
    ) {

      def getRank(kind: Port.Kind)(ps: Port.Syntax): Int = {
        val port = top.getPortOpt(kind)(ps).get
        ranks(port)
      }

      def getConnectedRanks(kind: Port.Kind, ps: Port.Syntax): Vector[Int] = {
        def getOutputRanks(ps: Port.Syntax) = {
          val outPort = top.getPortOpt(Port.Output)(ps).get
          val inPort = top.connectionsTo(outPort)
          Vector(ranks(inPort))
        }
        def getInputRanks(ps: Port.Syntax) = {
          val inPort = top.getPortOpt(Port.Input)(ps).get
          val outPorts = top.connectionsFrom(inPort)
          outPorts.map(ranks).toVector
        }
        kind match {
          case Port.Output => getOutputRanks(ps)
          case Port.Input => getInputRanks(ps)
        }
      }

    }

    override def visitColumn(s: State)(column: ColumnVector.Column): State = {
      val nextRankMap = s.nextRankMap + (Port.Output -> 0)
      val s1 = s.copy(nextRankMap = nextRankMap)
      column.elements.foldLeft(s1)((s2, element) => visitElement(s2)(element))
    }

    override def visitPortVector(s: State)(
      kind: Port.Kind,
      instanceName: String,
      vector: ColumnVector.PortVector
    ) =
      vector.indices.foldLeft(s)((s, index) => {
        val ps = Port.Syntax(instanceName, vector.name, index)
        val port = s.top.getPortOpt(kind)(ps).get
        val ranks = s.ranks + (port -> s.nextRankMap(kind))
        val nextRank = s.nextRankMap(kind) + 1
        val nextRankMap = s.nextRankMap + (kind -> nextRank)
        s.copy(ranks = ranks, nextRankMap = nextRankMap)
      })

  }

  private case class ScoreInterval(
    val min: Int,
    val max: Int
  ) extends Ordered[ScoreInterval] {
    
    override def compare(that: ScoreInterval): Int =
      if (this.max < that.min) -1
      else if (this.min > that.max) 1
      else 0

  }

  private object ScoreInterval {

    /** Default should not compare less than or greater to any real interval */
    val default = ScoreInterval(0, 100000)

  }

  private object PortVectorScores extends ColumnVectorVisitor {

    type Scores = Map[(String, String), ScoreInterval]

    case class State(
      val portRanks: PortRanks.State,
      val scoreMap: Map[Port.Kind, Scores] = Port.emptyMaps
    )

    override def visitPortVector(s: State)(
      kind: Port.Kind,
      instanceName: String,
      vector: ColumnVector.PortVector
    ): State =
      if (vector.indices.length == 0) s else {
        val ranks = vector.indices.flatMap(i => {
          val ps = Port.Syntax(instanceName, vector.name, i)
          s.portRanks.getConnectedRanks(kind, ps)
        })
        val interval = if (ranks.size == 0) ScoreInterval.default else {
          val min = findMin(ranks)
          val max = findMax(ranks)
          ScoreInterval(min, max)
        }
        val scores = s.scoreMap(kind) + ((instanceName, vector.name) -> interval)
        s.copy(scoreMap = s.scoreMap + (kind -> scores))
      }

  }

  private object InstanceScores extends ColumnVectorVisitor {

    type Scores = Map[String, ScoreInterval]

    case class State(
      val portVectorScores: PortVectorScores.State,
      val scoreMap: Map[Port.Kind, Scores] = Port.emptyMaps
    )

    override def visitElementWithKind(s: State)(
      kind: Port.Kind,
      e: ColumnVector.Element
    ): State = {
      val scores = s.scoreMap(kind)
      val portVectorScores = s.portVectorScores.scoreMap(kind)
      val vectorScores = e.ports(kind).map(a => {
        val pair = (e.instanceName, a.name)
        portVectorScores(pair)
      })
      val interval = if (vectorScores.size == 0) ScoreInterval.default else {
        val min = findMin(vectorScores.map(_.min))
        val max = findMax(vectorScores.map(_.max))
        ScoreInterval(min, max)
      }
      val scores1 = scores + (e.instanceName -> interval)
      s.copy(scoreMap = s.scoreMap + (kind -> scores1))
    }

  }

  private object SortInstances extends ColumnVectorTransformer {

    case class State(
      val kind: Port.Kind,
      val instanceScores: InstanceScores.State
    )

    override def transformColumn(s: State)(column: ColumnVector.Column): ColumnVector.Column = {
      val scores = s.instanceScores.scoreMap(s.kind)
      val elements = column.elements.sortWith((a, b) => scores(a.instanceName) < scores(b.instanceName))
      column.copy(elements = elements)
    }

  }

  private object SortPortVectors extends ColumnVectorTransformer {

    case class State(
      val kind: Port.Kind,
      val portVectorScores: PortVectorScores.State
    )

    override def transformElement(s: State)(e: ColumnVector.Element) = {
      val scores = s.portVectorScores.scoreMap(s.kind)
      def score(a: ColumnVector.PortVector) = scores(e.instanceName, a.name)
      val vector = e.ports(s.kind).sortWith((a, b) => score(a) < score(b))
      val ports = e.ports + (s.kind -> vector)
      e.copy(ports = ports)
    }

  }

}

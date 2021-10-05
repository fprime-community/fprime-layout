package fpl.codegen

import fpl.topology._
import fpl.layout._

object WriteJson {

  val q = "\""

  def qs(s: String) = s"$q$s$q"

  def keyValue(key: String, value: String) = s"${qs(key)} : $value"

  def indent(s: String) = "  " ++ s.replaceAll("\n", "\n  ")

  def seq(delims: (String, String))(elts: Seq[String]) = 
    if (elts.size == 0) s"${delims._1}${delims._2}" else 
      s"${delims._1}\n" ++ indent(elts.mkString(",\n")) ++ s"\n${delims._2}"

  val array = seq("[", "]") _

  def obj(elts: (String, String)*) =
    seq("{", "}")(elts.map({ case (key, value) => keyValue(key, value) }))

  def members(elts: (String, String)*): Seq[String] = {
    elts.map({ case (key, value) => keyValue(key, value) })
  }

  def run(top: Topology, cv: ColumnVector): String = {
    obj(
      "columns" -> WriteColumnVector.visitColumnVector("")(cv),
      "connections" -> writeConnections(top, cv)
    )
  }


  def writeConnections(top: Topology, cv: ColumnVector): String = {
    val portMap = ConstructPortMap.run(top, cv)
    def indexTupleToArray(it: ConstructPortMap.IndexTuple): String =
      array(Vector(it._1, it._2, it._3, it._4).map(_.toString))
    def portToArray(kind: Port.Kind)(port: Port): String =
      indexTupleToArray(portMap(kind)(port))
    array(
      top.connectionsTo.toVector.map({ 
        case (from, to) => array(
          Vector(
            portToArray(Port.Output)(from),
            portToArray(Port.Input)(to),
          )
        )
      })
    )
  }

  object ConstructPortMap extends ColumnVectorVisitor {

    type IndexTuple = (Int, Int, Int, Int)

    type PortMap = Map[Port.Kind, Map[Port, IndexTuple]]

    case class State(
      val top: Topology,
      val columnIndex: Int = 0,
      val elementIndex: Int = 0,
      val portVectorIndex: Int = 0,
      val portMap: PortMap = Port.emptyMaps
    )

    def run(top: Topology, cv: ColumnVector): PortMap =
      visitColumnVector(State(top))(cv).portMap

    override def visitColumnVector(s: State)(cv: ColumnVector): State =
      cv.columns.foldLeft(s)((s1, c) => {
        visitColumn(s1.copy(columnIndex = c.index))(c)
      })

    override def visitColumn(s: State)(c: ColumnVector.Column): State = 
      c.elements.indices.foldLeft(s)((s1, i) => {
        visitElement(s1.copy(elementIndex = i))(c.elements(i))
      })

    override def visitElementWithKind(s: State)(
      kind: Port.Kind,
      e: ColumnVector.Element
    ): State =
      e.ports(kind).indices.foldLeft(s)((s1, i) => {
        visitPortVector(s1.copy(portVectorIndex = i))(
          kind,
          e.instanceName,
          e.ports(kind)(i)
        )
      })

    override def visitPortVector(s: State)(
      kind: Port.Kind,
      instanceName: String,
      pv: ColumnVector.PortVector
    ) = {
      pv.indices.foldLeft(s)((s1, i) => {
        val ps = Port.Syntax(instanceName, pv.name, i)
        val port = s1.top.getPort(kind)(ps)
        val indexTuple = (s1.columnIndex, s1.elementIndex, s1.portVectorIndex, i)
        val map = s1.portMap(kind) + (port -> indexTuple)
        s1.copy(portMap = s1.portMap + (kind -> map))
      })

    }

  }

  object WriteColumnVector extends ColumnVectorVisitor {

    type State = String

    override def visitColumnVector(s: State)(cv: ColumnVector) =
      array(cv.columns.map(c => visitColumn("")(c)))

    override def visitColumn(s: State)(c: ColumnVector.Column) =
      array(c.elements.map(e => visitElement("")(e)))

    override def visitElement(s: State)(e: ColumnVector.Element): State = {
      def ports(kind: Port.Kind) = {
        val elts = e.ports(kind).map(pv => {
          visitPortVector("")(kind, e.instanceName, pv)
        })
        array(elts)
      }
      obj(
        ("instanceName" -> qs(e.instanceName)),
        ("inputPorts" -> ports(Port.Input)),
        ("outputPorts" -> ports(Port.Output))
      )
    }

    override def visitPortVector(s: State)(
      kind: Port.Kind,
      instanceName: String,
      pv: ColumnVector.PortVector
    ) = {
      val portNumbers = {
        val elts = pv.indices.map(_.toString)
        array(elts)
      }
      obj(
        ("name" -> qs(pv.name)),
        ("portNumbers" -> portNumbers)
      )
    }

  }

}

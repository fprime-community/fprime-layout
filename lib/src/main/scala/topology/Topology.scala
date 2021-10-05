package fpl.topology

import fpl.input._
import fpl.util._

case class Topology(
  val ports: Topology.Ports = Map(),
  val instances: Topology.Instances = Map(),
  val connectionsTo: Topology.ConnectionsTo = Map(),
  val connectionsFrom: Topology.ConnectionsFrom = Map()
) {

  private def createPort(kind: Port.Kind)(ps: Port.Syntax): Topology = {
    val instance = this.instances.get(ps.instanceName).
      getOrElse(Instance()).
      createPort(kind)(ps.arrayName, ps.index)
    this.copy(instances = instances + (ps.instanceName -> instance))
  }

  def addConnection(fromSyntax: Port.Syntax, toSyntax: Port.Syntax) = {
    val topology = this.
      createPort(Port.Output)(fromSyntax).
      createPort(Port.Input)(toSyntax)
    val from = topology.getPortOpt(Port.Output)(fromSyntax).get
    val to = topology.getPortOpt(Port.Input)(toSyntax).get
    topology.copy(
      ports = ports + (from -> fromSyntax) + (to -> toSyntax),
      connectionsTo = connectionsTo + (from -> to),
      connectionsFrom = {
        val fromSet = connectionsFrom.getOrElse(to, Set()) + from
        connectionsFrom + (to -> fromSet)
      }
    )
  }

  def getPortOpt(kind: Port.Kind)(ps: Port.Syntax): Option[Port] = {
    for {
      instance <- this.instances.get(ps.instanceName)
      array <- instance.portMaps(kind).get(ps.arrayName)
      port <- array.slots.get(ps.index)
    } yield port
  }

  def getPort(kind: Port.Kind)(ps: Port.Syntax): Port = getPortOpt(kind)(ps).get

}

object Topology {

  type Ports = Map[Port,Port.Syntax]

  type Instances = Map[String,Instance]

  type ConnectionsFrom = Map[Port,Set[Port]]

  type ConnectionsTo = Map[Port,Port]

  /** Reads an ASCII table and forms a topology, if it can.
   *  Otherwise returns an error message. */
  def fromAsciiTable(table: AsciiTable): Result.Result[Topology] = {
    def portSyntax(lineNum: Int, lines: Vector[String]): Result.Result[Port.Syntax] = {
      try {
        val instanceName = lines(0)
        val arrayName = lines(1)
        val index = lines(2).toInt
        Right(Port.Syntax(instanceName, arrayName, index))
      }
      catch {
        case e: Exception => Left(s"malformed index at line ${lineNum + 2}")
      }
    }
    def helper(blocks: AsciiTable.Blocks, top: Topology): Result.Result[Topology] = {
      if (blocks.size == 0) Right(top)
      else {
        val block = blocks(0)
        if (block.lines.size == 6) {
          val fromLines = block.lines.slice(0, 3)
          val toLines = block.lines.slice(3, 6)
          val result = for {
            fromSyntax <- portSyntax(block.lineNum, fromLines)
            toSyntax <- portSyntax(block.lineNum + 3, toLines)
          }
          yield top.addConnection(fromSyntax, toSyntax)
          result match {
            case Right(top) => helper(blocks.tail, top)
            case Left(s) => Left(s)
          }
        }
        else Left(s"malformed block at line ${block.lineNum}")
      }
    }
    helper(table.blocks, Topology())
  }

}

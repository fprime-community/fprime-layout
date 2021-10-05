package fpl.topology

case class Port(val id: Port.Id)

object Port {

  sealed trait Kind
  case object Input extends Kind
  case object Output extends Kind

  type Id = Int

  var nextId: Id = 0

  def create: Port = {
    val sp = Port(nextId)
    nextId = nextId + 1
    sp
  }

  /** Port syntax */
  case class Syntax(
    val instanceName: String,
    val arrayName: String,
    val index: Int
  )

  def emptyMaps[A,B]: Map[Kind, Map[A,B]] = Map(
    Input -> Map(),
    Output -> Map()
  )

}

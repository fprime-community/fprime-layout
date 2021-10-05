package fpl.topology

case class Instance(
  val portMaps: Instance.PortMaps = Port.emptyMaps
) {

  def createPort(kind: Port.Kind)(mapName: String, index: Int): Instance = {
    val portMap = portMaps(kind).get(mapName).getOrElse(PortMap()).createPort(index)
    val kindMap = portMaps(kind) + (mapName -> portMap)
    copy(portMaps = portMaps + (kind -> kindMap))
  }

  def getPorts(kind: Port.Kind): Iterable[Port] =
    portMaps(kind).values.flatMap(_.slots.values)

}

object Instance {

  type PortMaps = Map[Port.Kind, Map[String, PortMap]]
  
}

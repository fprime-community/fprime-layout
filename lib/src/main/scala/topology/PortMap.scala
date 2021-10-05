package fpl.topology

case class PortMap(
  val slots: Map[Int, Port] = Map(),
) {

  def createPort(index: Int): PortMap = slots.get(index) match {
    case Some(_) => this
    case None => this.copy(slots = slots + (index -> Port.create))
  }


}

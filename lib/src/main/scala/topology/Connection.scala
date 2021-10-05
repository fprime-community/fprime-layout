package fpl.topology

case class Connection[T](
  val from: T,
  val to: T
)

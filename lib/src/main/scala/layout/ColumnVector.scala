package fpl.layout

import fpl.topology._

case class ColumnVector(
  columns: ColumnVector.Columns
) {

  def print = for (i <- 0 until columns.length) {
    System.out.println(s"Column $i")
    columns(i).print
  }

}

object ColumnVector {

  type Columns = Vector[Column]

  def fromPlaceInstances(top: Topology)(columns: PlaceInstances.Columns): ColumnVector = {
    val indices = mapToIndexVector(columns)
    val columns1 = indices.map(i => Column.fromPlaceInstances(top)(i, columns(i)))
    ColumnVector(columns1)
  }

  def mapToIndexVector[T](map: Map[Int, T]): Vector[Int] =
    map.keys.toVector.sortWith(_ < _)

  case class Column(
    index: Int,
    elements: Vector[Element]
  ) {

    def print = for (i <- 0 until elements.length) {
      System.out.println(s"  Element $i")
      elements(i).print
    }

  }

  object Column {

    def fromPlaceInstances(top: Topology)(index: Int, column: PlaceInstances.Column): Column = {
      val elements = column.toVector.map(
        name => Element.fromInstance(name, top.instances(name))
      )
      Column(index, elements)
    }

  }

  case class Element(
    instanceName: String,
    ports: Map[Port.Kind, Vector[PortVector]]
  ) {

    def print = {
      System.out.println(s"    Instance Name: $instanceName")
      System.out.println("    Input Ports")
      for (i <- 0 until ports(Port.Input).length) {
        System.out.println(s"      Element $i")
        ports(Port.Input)(i).print
      }
      System.out.println("    Output Ports")
      for (i <- 0 until ports(Port.Output).length) {
        System.out.println(s"      Element $i")
        ports(Port.Output)(i).print
      }
    }

  }

  object Element {

    def fromInstance(name: String, instance: Instance) =
      Element(
        name,
        Map(
          Port.Input -> instance.portMaps(Port.Input).toVector.map(PortVector.fromPortMap),
          Port.Output -> instance.portMaps(Port.Output).toVector.map(PortVector.fromPortMap)
        )
      )

  }

  case class PortVector(
    name: String,
    indices: Vector[Int]
  ) {
    
    def print = {
      System.out.println(s"        Name: $name")
      val s = indices.foldLeft("")((s1, i) => s1 ++ s" $i")
      System.out.println(s"        Indices:$s")
    }

  }

  object PortVector {

    def fromPortMap(pair: (String, PortMap)): PortVector = {
      val (name, portMap) = pair
      PortVector(name, mapToIndexVector(portMap.slots))
    }

  }

}

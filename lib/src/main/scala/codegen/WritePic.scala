package fpl.codegen

import fpl.layout._
import fpl.topology._

object WritePic {

  val portVSpace = 0.125
  val portWidth = 0.375
  val portHeight = portWidth
  val labelHeight = 0.375
  val d = "$"
  val q = "\""

  case class State(
    val top: Topology,
    val cv: ColumnVector,
    val instanceColumnIndices: Map[String, Int] = Map(),
    val columnWidths: Map[Int, Double] = Map(),
    val instanceHeights: Map[Port.Kind, Map[String, Double]] = Port.emptyMaps
  )

  object InstanceColumnIndices extends ColumnVectorVisitor {

    type State = WritePic.State

    override def visitColumn(s: State)(column: ColumnVector.Column): State =
      column.elements.foldLeft(s)((s,e) => {
        s.copy(instanceColumnIndices = s.instanceColumnIndices + (e.instanceName -> column.index))
      })

  }

  object ColumnWidths extends ColumnVectorVisitor {

    type State = WritePic.State

    val defaultWidth = 1.5

    override def visitColumn(s: State)(column: ColumnVector.Column): State =
      s.copy(columnWidths = s.columnWidths + (column.index -> defaultWidth))

  }

  object InstanceHeights extends ColumnVectorVisitor {

    type State = WritePic.State

    val minHeight = 0.75

    override def visitElementWithKind(s: State)(
      kind: Port.Kind,
      e: ColumnVector.Element
    ): State = {
      val height = {
        val computedHeight = e.ports(kind).foldLeft(portHeight)((h, pv) => h + getPortVectorHeight(pv))
        if (computedHeight >= minHeight) computedHeight else minHeight
      }
      val map = s.instanceHeights(kind) + (e.instanceName -> height)
      s.copy(instanceHeights = s.instanceHeights + (kind -> map))
    }

    def getPortVectorHeight(pv: ColumnVector.PortVector): Double =
      portVSpace + pv.indices.size.toDouble * portHeight

  }

  object PictureHeight extends ColumnVectorVisitor {

    val max = 55

    case class State(
      val writePicState: WritePic.State,
      val height: Double
    )

    override def visitColumnVector(s: State)(columnVector: ColumnVector): State =
      columnVector.columns.foldLeft(s)((s1, column) => {
        val s2 = visitColumn(s)(column)
        if (s2.height > s1.height) s2 else s1
      })

    override def visitColumn(s: State)(column: ColumnVector.Column) = {
      val size = column.elements.size
      val height = if (size == 0) 0 else
        column.elements.foldLeft((size - 1) * EmitInstances.vSpace)((h, e) => {
          h + EmitInstances.getInstanceHeight(s.writePicState, e.instanceName)
        })
      s.copy(height = height)
    }

    /** Gets the height of the drawing */
    def get(wps: WritePic.State)(cv: ColumnVector): Double = {
      val h = visitColumnVector(State(wps, 1.0))(cv).height
      if (h > max) max else h
    }

  }

  object PictureWidth extends ColumnVectorVisitor {

    val max = 39

    case class State(
      val writePicState: WritePic.State,
      val width: Double
    )

    override def visitColumn(s: State)(c: ColumnVector.Column) = {
      val wps = s.writePicState
      val width = s.width + wps.columnWidths(c.index)
      s.copy(width = width)
    }

    override def visitColumnVector(s: State)(cv: ColumnVector): State = {
      val s1 = super.visitColumnVector(s)(cv)
      val size = cv.columns.size
      val width = if (size == 0) s1.width else {
        val hSpace = EmitInstances.getHSpace(s.writePicState)
        s1.width + (size - 1) * hSpace
      }
      s.copy(width = width)
    }

    /** Gets the width of the drawing */
    def get(wps: WritePic.State)(cv: ColumnVector): Double = {
      val w = visitColumnVector(State(wps, 1.0))(cv).width
      if (w > max) max else w
    }

  }

  def run(top: Topology, cv: ColumnVector) = {
    val s0 = State(top, cv)
    val s1 = InstanceColumnIndices.visitColumnVector(s0)(cv)
    val s2 = ColumnWidths.visitColumnVector(s1)(cv)
    val s3 = InstanceHeights.visitColumnVector(s2)(cv)
    val s = s3
    System.out.println(".PS")
    emitPreamble(s)(cv)
    EmitInstances.visitColumnVector(s)(cv)
    EmitPorts.visitColumnVector(s)(cv)
    emitConnections(s)
    System.out.println(".PE")
  }

  def emitPreamble(s: State)(cv: ColumnVector) = {
    val width = PictureWidth.get(s)(cv)
    val height = PictureHeight.get(s)(cv)
    System.out.println(
s"""
maxpswid=$width
maxpsht=$height

define label { [
  box invis height $labelHeight ${d}1
] }
define instance { [
  B: box invis ${d}1 width ${d}2 height $labelHeight
  box width ${d}2 height ${d}3 shaded "skyblue" with .n at B.s
] }
define port { [
  box ${d}1 width $portWidth height $portHeight shaded "white"
] }"""
    )
  
  }

  object EmitInstances extends ColumnVectorVisitor {

    type State = WritePic.State

    val vSpace = 0.2

    def getHSpace(s: State): Double = {
      val hs = 10.0 / s.cv.columns.size
      if (hs < 1) 1 else hs
    }

    def getInstanceHeight(s: State, instanceName: String) = 
      s.instanceHeights(Port.Input)(instanceName) +
      s.instanceHeights(Port.Output)(instanceName)


    override def visitColumn(s: State)(c: ColumnVector.Column): State = {
      val hSpace = getHSpace(s)
      val width = s.columnWidths(c.index)
      c.elements.map(e => {
        val height = getInstanceHeight(s, e.instanceName)
        System.out.println(s"""
Instance_${e.instanceName}: instance(\"\\fB${e.instanceName}\\fR\", $width, $height)
move down $vSpace from Instance_${e.instanceName}.s""")
      })
      val e = c.elements(0)
      if (c.index + 1 < s.cv.columns.size)
        System.out.println(s"move to Instance_${e.instanceName}.n + (${width + hSpace}, 0)")
      else ()
      s
    }

  }

  object EmitPorts extends ColumnVectorVisitor {

    type State = WritePic.State

    override def visitElementWithKind(s: State)(
      kind: Port.Kind,
      e: ColumnVector.Element
    ) = {
      System.out.println("")
      kind match {
        case Port.Input => System.out.println(s"move to Instance_${e.instanceName}.nw - (0, $labelHeight)")
        case Port.Output => 
          val vSpace = labelHeight + s.instanceHeights(Port.Input)(e.instanceName)
          System.out.println(s"move to Instance_${e.instanceName}.ne - (0, ${vSpace})")
      }
      e.ports(kind).foldLeft(s)((s, v) => {
        System.out.println("")
        System.out.println(s"move down $portVSpace")
        visitPortVector(s)(kind, e.instanceName, v)
      })
    }

    override def visitPortVector(s: State)(
      kind: Port.Kind,
      instanceName: String,
      pv: ColumnVector.PortVector
    ) = {
      def index(indexPos: Int) = pv.indices(indexPos)
      def label(indexPos: Int) = s"Port_${instanceName}_${pv.name}_${index(indexPos)}"
      System.out.println(s"${label(0)}: port($q${index(0)}$q)")
      val str = kind match {
        case Port.Input => s"$q \\fI${pv.name}\\fR$q at ${label(0)}.e ljust"
        case Port.Output => s"$q\\fI${pv.name}\\fR $q at ${label(0)}.w rjust"
      }
      System.out.println(str)
      System.out.println(s"move to ${label(0)}.s")
      for (i <- 1 until pv.indices.length) {
        System.out.println(s"${label(i)}: port($q${index(i)}$q) with .n at ${label(i-1)}.s")
      }
      s
    }

  }

  def emitConnections(s: State) = {
    System.out.println("")
    def label(port: Port): String = {
      val ps = s.top.ports(port)
      s"Port_${ps.instanceName}_${ps.arrayName}_${ps.index}"
    }
    def columnNumber(port: Port): Int = {
      val ps = s.top.ports(port)
      s.instanceColumnIndices(ps.instanceName)
    }
    s.top.connectionsTo.map(pair => {
      val (from, to) = pair
      System.out.print(s"arrow from ${label(from)}.e to ${label(to)}.w")
      if (columnNumber(to) <= columnNumber(from))
        System.out.print(" dashed")
      System.out.println("")
    })
  }

}


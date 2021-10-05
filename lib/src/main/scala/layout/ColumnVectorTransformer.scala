package fpl.layout

import fpl.topology._

/** Transforms a column vector */
trait ColumnVectorTransformer {

  type State

  def transformPortVector(s: State)(
    kind: Port.Kind,
    instanceName: String,
    vector: ColumnVector.PortVector
  ): ColumnVector.PortVector = vector

  def transformElement(s: State)(e: ColumnVector.Element): ColumnVector.Element = {
    val e1 = transformElementWithKind(s)(Port.Input, e)
    transformElementWithKind(s)(Port.Output, e1)
  }

  def transformElementWithKind(s: State)(
    kind: Port.Kind,
    e: ColumnVector.Element
  ): ColumnVector.Element = {
    val vector = e.ports(kind).map(a => transformPortVector(s)(kind, e.instanceName, a))
    val ports = e.ports + (kind -> vector)
    e.copy(ports = ports)
  }

  def transformColumn(s: State)(column: ColumnVector.Column): ColumnVector.Column = {
    val elements = column.elements.map(e => transformElement(s)(e))
    column.copy(elements = elements)
  }

  def transformColumnVector(s: State)(columnVector: ColumnVector): ColumnVector = {
    val columns = columnVector.columns.map(c => transformColumn(s)(c))
    columnVector.copy(columns = columns)
  }

}

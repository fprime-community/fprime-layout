package fpl.layout

import fpl.topology._

/** Visits a column vector */
trait ColumnVectorVisitor {

  type State

  def visitPortVector(s: State)(
    kind: Port.Kind,
    instanceName: String,
    vector: ColumnVector.PortVector
  ): State = s

  def visitElement(s: State)(e: ColumnVector.Element): State = {
    val s1 = visitElementWithKind(s)(Port.Input, e)
    visitElementWithKind(s1)(Port.Output, e)
  }

  def visitElementWithKind(s: State)(
    kind: Port.Kind,
    e: ColumnVector.Element
  ): State =
    e.ports(kind).foldLeft(s)((s, v) => {
      visitPortVector(s)(kind, e.instanceName, v)
    })

  def visitColumn(s: State)(column: ColumnVector.Column): State = 
    column.elements.foldLeft(s)((s1, element) => visitElement(s1)(element))

  def visitColumnVector(s: State)(columnVector: ColumnVector): State =
    columnVector.columns.foldLeft(s)((s1, column) => visitColumn(s1)(column))

}

package fpl.layout

import fpl.topology._

object AlphabetizeColumns extends ColumnVectorTransformer {

  type State = Unit

  override def transformColumn(s: State)(column: ColumnVector.Column): ColumnVector.Column = {
    val elements = column.elements.sortWith(
      (a, b) => a.instanceName.toLowerCase < b.instanceName.toLowerCase
    )
    column.copy(elements = elements)
  }

}

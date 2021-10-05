package fpl.layout

import fpl.topology._

object Layout {

  def replaceElement[T](v: Vector[T])(i: Int, e: T): Vector[T] =
    (v.slice(0, i) :+ e) ++ v.slice(i +1, v.size)

  /** Runs the layout algorithm */
  def run(top: Topology): ColumnVector = {
    val pi = PlaceInstances(top)
    val cv0 = ColumnVector.fromPlaceInstances(top)(pi.run)
    val cv1 = AlphabetizeColumns.transformColumnVector(())(cv0)
    cv1.columns.indices.foldLeft(cv1)((cv, i) => {
      val kind = if (i == 0) Port.Output else Port.Input
      val c = SortColumns.sort(top)(kind)(cv, i)
      ColumnVector(replaceElement(cv.columns)(i, c))
    })
  }

}

package fpl.layout

import fpl.topology._

case class PlaceInstances(top: Topology) {

  /** Runs the algorithm */
  def run: PlaceInstances.Columns = {
    val sortedInstanceNames = top.instances.keys.toVector.sortWith(sortFn _)
    def helper(state: State, instanceName: String): State = place(0, instanceName)(state)
    sortedInstanceNames.foldLeft(State())(helper).columns
  }

  private case class State(
    val visited: Set[String] = Set(),
    val columns: PlaceInstances.Columns = Map()
  ) {

    def addInstance(columnIndex: Int, instanceName: String) = {
      val column = columns.getOrElse(columnIndex, Set()) + instanceName
      this.copy(
        columns = columns + (columnIndex -> column),
        visited = visited + instanceName
      )
    }

  }

  private def getOutputAdjacencies(instanceName: String): Iterable[String] = {
    val outputPorts = top.instances(instanceName).getPorts(Port.Output)
    val inputPorts = outputPorts.map(p => top.connectionsTo(p))
    inputPorts.map(p => top.ports(p).instanceName)
  }

  private def place(columnIndex: Int, instanceName: String)(state: State): State =
    if (state.visited.contains(instanceName)) state else {
      val outputAdjacencies = getOutputAdjacencies(instanceName)
      val state1 = state.addInstance(columnIndex, instanceName)
      outputAdjacencies.toVector.sortWith(sortFn _).
        foldLeft(state1)((s, i) => place(columnIndex + 1, i)(s))
    }

  private def sortFn(a: String, b: String) = scoreMap(a) > scoreMap(b)

  /** Memoize the instance scores */
  private val scoreMap = {
    def score(instanceName: String): Int = {
      def helper(instanceName: String, visited: Set[String], numReached: Int): (Set[String], Int) =
        if (visited.contains(instanceName)) (visited, numReached) else {
          val outputAdjacencies = getOutputAdjacencies(instanceName)
          val state = (visited + instanceName, numReached + 1)
          outputAdjacencies.foldLeft(state)({ case ((v, n), i) => helper(i, v, n) })
        }
      helper(instanceName, Set(), 0)._2
    }
    top.instances.keys.foldLeft(Map(): Map[String, Int])((m, i) => m + (i -> score(i)))
  }

}

object PlaceInstances {

  type Column = Set[String]

  type Columns = Map[Int,Column]

  /** Prints the columns */
  def printColumns(columns: Columns): Unit = {
    val indices = columns.keys.toVector.sortWith(_ < _)
    def printColumn(index: Int) = {
      val column = columns(index)
      column.map(s => System.out.println(s))
      System.out.println("")
    }
    indices.map(printColumn)
    ()
  }

}

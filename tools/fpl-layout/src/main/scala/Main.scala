import fpl.codegen._
import fpl.input._
import fpl.layout._
import fpl.topology._

object Main {

  def main(args: Array[String]): Unit = {
    val table = AsciiTable.read
    Topology.fromAsciiTable(table) match {
      case Right(top) =>
        val cv = Layout.run(top)
        val s = WriteJson.run(top, cv)
        System.out.println(s)
      case Left(s) =>
        System.err.println(s"fpl-layout: error: $s")
        System.exit(1)
    }
  }

}

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
        WritePic.run(top, cv)
      case Left(s) =>
        System.err.println(s"fpl-write-pic: error: $s")
        System.exit(1)
    }
  }

}

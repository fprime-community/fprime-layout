import fpl.input._
import fpl.util._

object Main {

  def command(args: Array[String]): Result.Result[Unit] = {
    val fileName = getFileName(args)
    for {
      xmlFile <- parseXmlFile(fileName)
      asciiTable <- xmlFile.toAsciiTable
    }
    yield asciiTable.print
  }

  def getFileName(args: Array[String]): String = {
    if (args.size != 1) {
      System.err.println("usage: fpl-convert-xml file-name")
      System.exit(1)
    }
    args(0)
  }

  def main(args: Array[String]): Unit = {
    command(args) match {
      case Left(s) =>
        System.err.println(s"fpl-convert-xml: $s")
        System.exit(1)
      case _ =>
    }
    ()
  }

  def parseXmlFile(fileName: String): Result.Result[XmlReader.File] = {
    for {
      elem <- try Right(scala.xml.XML.loadFile(fileName))
      catch {
        case e: Exception => Left(s"parse error in $fileName\n${e.toString}")
      }
    }
    yield XmlReader.File(fileName, elem)
  }

}

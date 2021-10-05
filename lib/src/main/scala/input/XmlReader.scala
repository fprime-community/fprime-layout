package fpl.input

import fpl.util._

/** Reads an XML connection list */ 
object XmlReader {

  /** An FPL XML file */
  case class File(
    /** The file name */
    name: String,
    /** The XML element */
    elem: scala.xml.Elem
  ) {

    def error(msg: String) = Left(s"error in $name\n$msg")

    /** Gets an attribute string from a node, returning an error if it is not there */
    def getAttribute(node: scala.xml.Node, name: String): Result.Result[String] = 
      getAttributeOpt(node, name) match {
        case Some(s) => Right(s)
        case None => error(s"missing attribute $name for node ${node.toString}")
      }

    /** Gets a single child from a node, returning an error if it is not there */
    def getSingleChild(node: scala.xml.Node, name: String): Result.Result[scala.xml.Node] =
      getSingleChildOpt(node, name) match {
        case Right(Some(child)) => Right(child)
        case Right(None) => error(s"missing child $name for node ${node.toString}")
        case Left(e) => Left(e)
      }

    /** Gets an optional single child from a node */
    def getSingleChildOpt(node: scala.xml.Node, name: String): Result.Result[Option[scala.xml.Node]] = {
      val children = (node \ name)
      children.size match {
        case 0 => Right(None)
        case 1 => Right(Some(children(0)))
        case _ => error(s"multiple child nodes $name for node ${node.toString}")
      }
    }

    /** Converts an XML connection list to an ASCII table */
    def toAsciiTable: Result.Result[AsciiTable] = {
      def getPortLines(node: scala.xml.Node)(endpoint: String) =
        for {
          child <- getSingleChild(node, endpoint)
          instance <- getAttribute(child, "component")
          portName <- getAttribute(child, "port")
          portNumber <- getAttribute(child, "num")
        } yield Vector(instance, portName, portNumber)
      def getConnectionLines(node: scala.xml.Node): Result.Result[Vector[String]] =
        for {
          source <- getPortLines(node)("source")
          target <- getPortLines(node)("target")
        } yield source ++ target
      val xmlConnections = (elem \ "connection").toList
      for (connectionLines <- Result.map(xmlConnections, getConnectionLines))
        yield {
          val blocks0 = Vector(): Vector[AsciiTable.Block]
          val linesPerBlock = 7
          val (blocks, _) = connectionLines.foldLeft(blocks0, 1)({
            case ((blocks, lineNum), lines) => (
              blocks :+ AsciiTable.Block(lineNum, lines),
              lineNum + linesPerBlock
            )
          })
          AsciiTable(blocks)
        }
    }

  }

  /** Gets an optional attribute string */
  def getAttributeOpt(node: scala.xml.Node, name: String): Option[String] = 
    node.attribute(name).map(_.toList.head.toString)

}

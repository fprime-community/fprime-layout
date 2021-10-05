package fpl.input

/**
 *  A table represented as a list of rows of ASCII characters:
 *
 *  * Each row is a sequence of contiguous non-blank lines
 *  * Each non-blank line in a row R is a column of R
 *  * Sequences of blank lines separate rows
 */

case class AsciiTable(
  val blocks: AsciiTable.Blocks
) {

  /** Prints the ASCII table */
  def print = blocks.map(_.print)

}

object AsciiTable {

  /** An ASCII table block */
  case class Block(
    val lineNum: Int,
    val lines: Vector[String]
  ) {

    /** Prints the block */
    def print = {
      lines.map(System.out.println)
      System.out.println("")
    }
     
  }

  type Blocks = Vector[Block]

  sealed trait State
  case object Done extends State
  case object NotDone extends State

  /** Reads an ASCII table from standard input */
  def read: AsciiTable = {
    def helper(lineNum: Int, blocks: Blocks): Blocks = {
      readNextBlock(lineNum) match {
        case Some((lineNum1, block, Done)) => blocks :+ block
        case Some((lineNum1, block, NotDone)) => helper(lineNum1, blocks :+ block)
        case None => blocks
      }
    }
    AsciiTable(helper(0, Vector()))
  }

  private def readNextBlock(lineNum: Int): Option[(Int, Block, State)] = {
    def helper(lineNum: Int, lines: Vector[String]): (Int, Vector[String], State) = {
      val lineNum1 = lineNum + 1
      val line = scala.io.StdIn.readLine
      if (line == null) (lineNum1, lines, Done)
      else if (line.length == 0) (lineNum1, lines, NotDone)
      else helper(lineNum1, lines :+ line)
    }
    readNextNonBlankLine(lineNum).map(pair => {
      val (lineNumStart, line) = pair
      val (lineNumEnd, lines, state) = helper(lineNumStart, Vector(line))
      (lineNumEnd, Block(lineNumStart, lines), state)
    })
  }

  private def readNextNonBlankLine(lineNum: Int): Option[(Int, String)] = {
    def helper(lineNum: Int): Option[(Int, String)] = {
      val lineNum1 = lineNum + 1
      val line = scala.io.StdIn.readLine
      if (line == null) None 
      else if (line.length > 0) Some(lineNum1, line) 
      else helper(lineNum1)
    }
    helper(lineNum)
  }

}

package solution

import scala.io.Source

trait Solution {
  type Answer
  val day: Int

  // lazy until concrete class
  lazy val lines: List[String] = parseFile(day)

  def solveFirstPart(lines: List[String]): Answer

  def solveSecondPart(lines: List[String]): Answer

  def run(): Unit = main(Array())

  def main(args: Array[String]): Unit = {
    println(s"solution for day $day")

    val res1 = solveFirstPart(lines)
    println(s"1st part: $res1")
    val res2 = solveSecondPart(lines)
    println(s"2st part: $res2")

    println(Array.fill(20)("-").mkString)
  }

  private def parseFile(n: Int): List[String] = {
    val filename = f"input$n%02d.txt"
    val source = Source.fromFile(s"src/main/resources/$filename")
    val lines = source.getLines().toList
    source.close()
    lines
  }
}

package solution

import scala.collection.immutable.TreeMap

object Day5 extends Solution {
  override val day: Int = 5
  override type Answer = String

  override def solveFirstPart(lines: List[String]): Answer = {
    val (stacks, moves) = parse(lines)
    moves.foreach(stacks.processSingle)
    stacks.headString()
  }


  override def solveSecondPart(lines: List[String]): Answer = {
    val (stacks, moves) = parse(lines)
    moves.foreach(stacks.processBatch)
    stacks.headString()
  }

  def parseMoves(moves: List[String]): List[Move] =
    moves.map { line =>
      val Array(_, times, _, from, _, to) = line.split(" ")
      Move(from.toInt, to.toInt, times.toInt)
    }

  def parseStacks(stacks: List[String]): StackGroup = {
    val xs = stacks.reverse
    val num = "\\d".r.findAllIn(xs.head).size
    var res = (1 to num)
      .map(n => (n, List.empty[Char]))
      .to(TreeMap)

    xs.tail.foreach { line =>
      line.grouped(4)
        .map(s => "[A-Z]".r.findFirstIn(s))
        .zipWithIndex
        .toList
        .foreach {
          case (op, i) =>
            op match {
              case Some(v) => res = res.updated(i + 1, v.head :: res(i + 1))
              case None => ()
            }
        }
    }

    StackGroup(res)
  }

  def parse(lines: List[String]): (StackGroup, List[Move]) = {
    val separatorIndex = lines.indexOf("")
    val (stacks, moves) = lines.splitAt(separatorIndex)

    (parseStacks(stacks), parseMoves(moves.tail))
  }


  private type Stack = List[Char]

  case class StackGroup(private var stackMap: TreeMap[Int, Stack]) {
    def processSingle(move: Move): Unit = {
      val Move(from, to, times) = move
      Range.inclusive(1, times) foreach { _ =>
        val toMove :: xs = stackMap(from)
        val toStack = stackMap(to)
        stackMap += from -> xs
        stackMap += to -> (toMove :: toStack)
      }
    }

    def processBatch(move: Move): Unit = {
      val Move(from, to, times) = move
      val (toMove, rest) = stackMap(from).splitAt(times)
      stackMap += from -> rest
      stackMap += to -> (toMove ++ stackMap(to))
    }


    def headString(): String =
      stackMap.valuesIterator.map(_.head).mkString
  }

  case class Move(from: Int, to: Int, times: Int)

}


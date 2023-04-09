package solution

import munit.FunSuite
import solution.Day5._

import scala.collection.immutable.TreeMap

class Day5Suite extends FunSuite with BaseTest {
  override def input: String =
    """    [D]
      |[N] [C]
      |[Z] [M] [P]
      | 1   2   3
      |
      |move 1 from 2 to 1
      |move 3 from 1 to 3
      |move 2 from 2 to 1
      |move 1 from 1 to 2""".stripMargin

  test("first part answer") {
    assertEquals(
      solveFirstPart(lines),
      "CMZ"
    )
  }
  test("second part answer") {
    assertEquals(
      solveSecondPart(lines),
      "MCD"
    )
  }
  test("parse stacks correctly") {
    assertEquals(
      parseStacks(
        lines.splitAt(lines.indexOf(""))._1
      ),
      StackGroup(TreeMap(
        1 -> "ZN".toList.reverse,
        2 -> "MCD".toList.reverse,
        3 -> "P".toList.reverse,
      )),
    )
  }
  test("parse moves correctly") {
    assertEquals(
      parseMoves(List("move 1 from 2 to 1", "move 2 from 1 to 3")),
      List(Move(2, 1, 1), Move(1, 3, 2))
    )
  }

  test("parse return correct result") {
    assertEquals(
      parse(lines),
      (
        StackGroup(TreeMap(
          1 -> "ZN".toList.reverse,
          2 -> "MCD".toList.reverse,
          3 -> "P".toList.reverse,
        )),
        List(
          Move(2, 1, 1),
          Move(1, 3, 3),
          Move(2, 1, 2),
          Move(1, 2, 1),
        )
      )
    )
  }
}

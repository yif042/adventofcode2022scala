package solution

import munit.FunSuite
import solution.Day8._

class Day8Suite extends FunSuite with BaseTest {
  override def input: String =
    """30373
      |25512
      |65332
      |33549
      |35390
      |""".stripMargin

  test("first part answer") {
    assertEquals(
      solveFirstPart(lines),
      21
    )
  }

  test("second part answer") {
    assertEquals(
      solveSecondPart(lines),
      8
    )
  }

  test("parse forrest") {
    val forrest = parse(lines)
    assertEquals(forrest.size, 5)
    assertEquals(forrest.head, Vector(3, 0, 3, 7, 3).map(Height))
    assertEquals(forrest.last, Vector(3, 5, 3, 9, 0).map(Height))
  }

}

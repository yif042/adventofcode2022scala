package solution

import munit.FunSuite
import solution.Day9._

class Day9Suite extends FunSuite with BaseTest {
  override def input: String = """R 4
                                 |U 4
                                 |L 3
                                 |D 1
                                 |R 4
                                 |D 1
                                 |L 5
                                 |R 2""".stripMargin

  test("first part answer") {
    assertEquals(
      solveFirstPart(lines),
      13
    )
  }


  test("second part answer") {
    assertEquals(
      solveSecondPart(lines),
      1
    )
  }

  private val largerInputForSecond = """R 5
                               |U 8
                               |L 8
                               |D 3
                               |R 17
                               |D 10
                               |L 25
                               |U 20""".stripMargin

  test("larger second part answer") {
    assertEquals(
      solveSecondPart(largerInputForSecond.linesIterator.toList),
      36
    )
  }


  test("follow one step") {
    assertEquals(follow((2, 0), (0, 0)), (1, 0))
    assertEquals(follow((-2, 0), (0, 0)), (-1, 0))
    assertEquals(follow((0, 2), (0, 0)), (0, 1))
    assertEquals(follow((0, -2), (0, 0)), (0, -1))

    assertEquals(follow((4, 2), (3, 0)), (4, 1))
  }
}

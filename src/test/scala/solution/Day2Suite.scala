package solution

import munit.FunSuite
import solution.Day2._

class Day2Suite extends FunSuite with BaseTest {
  override def input: String =
    """A Y
      |B X
      |C Z""".stripMargin


  test("first part decode") {
    assertEquals(
      Day2.decodeFirst(lines),
      List(
        Game.make(Paper, Rock),
        Game.make(Rock, Paper),
        Game.make(Scissors, Scissors),
      )
    )
  }

  test("calculate score") {
    assertEquals(Game.make(Paper, Rock).point(), 8)
    assertEquals(Game.make(Rock, Paper).point(), 1)
    assertEquals(Game.make(Scissors, Scissors).point(), 6)
  }

  test("second part decode") {
    assertEquals(
      Day2.decodeSecond(lines),
      List(
        Game.make(Draw, Rock),
        Game.make(Lose, Paper),
        Game.make(Win, Scissors),
      )
    )
  }
}

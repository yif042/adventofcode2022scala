package solution

import munit.FunSuite
import solution.Day2._

class Day2Test extends FunSuite {

  private val testInput =
    """A Y
      |B X
      |C Z""".stripMargin.split("\n").toList


  test("first part decode") {
    assertEquals(
      Day2.decodeFirst(testInput),
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
      Day2.decodeSecond(testInput),
      List(
        Game.make(Draw, Rock),
        Game.make(Lose, Paper),
        Game.make(Win, Scissors),
      )
    )
  }
}

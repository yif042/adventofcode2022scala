package solution

import munit.FunSuite

class Day1Suite extends FunSuite with BaseTest {
  override def input: String =
    """10
      |20
      |
      |3
      |""".stripMargin

  test("elves can parse inputs correctly") {
    assertEquals(
      Day1.elves(lines),
      List(30, 3)
    )
  }
}

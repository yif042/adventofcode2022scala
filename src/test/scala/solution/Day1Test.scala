package solution

import munit.FunSuite

class Day1Test extends FunSuite {
  test("elves can parse inputs correctly") {
    assertEquals(
      Day1.elves(List("10", "20", "", "3")),
      List(30, 3)
    )
  }
}

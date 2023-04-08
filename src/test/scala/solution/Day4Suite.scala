package solution

import munit.FunSuite
import solution.Day4._

class Day4Suite extends FunSuite with BaseTest {
  override def input: String =
    """2-4,6-8
      |2-3,4-5""".stripMargin

  test("parse should return list of assignments") {
    assertEquals(
      parse(lines),
      List(
        Assignment(Range(2, 4), Range(6, 8)),
        Assignment(Range(2, 3), Range(4, 5)),
      )
    )
  }

  test("range contains that") {
    assert(Range(1, 3).contains(Range(2, 3)))
    assert(Range(1, 3).contains(Range(1, 3)))
    assert(!Range(1, 3).contains(Range(2, 4)))
  }

  test("ranges overlaps") {
    assert(Range(1, 3).overlaps(Range(2, 5)))
    assert(Range(1, 3).overlaps(Range(0, 2)))
    assert(Range(1, 3).overlaps(Range(2, 3)))
    assert(!Range(4, 5).overlaps(Range(2, 3)))
    assert(!Range(1, 3).overlaps(Range(4, 5)))
  }
}

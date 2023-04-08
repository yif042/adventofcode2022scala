package solution

import munit.FunSuite
import solution.Day3.ItemType

class Day3Suite extends FunSuite with BaseTest {
  override def input: String = ???

  test("item type return correct priority") {
    assertEquals(ItemType('a').priority(), 1)
    assertEquals(ItemType('A').priority(), 27)
    assertEquals(ItemType('z').priority(), 26)
    assertEquals(ItemType('Z').priority(), 52)
    assertEquals(ItemType('L').priority(), 38)
  }
}

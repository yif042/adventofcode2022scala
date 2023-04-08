package solution

import munit.FunSuite
import solution.Day3._

class Day3Test extends FunSuite {
  test("item type return correct priority") {
    assertEquals(ItemType('a').priority(), 1)
    assertEquals(ItemType('A').priority(), 27)
    assertEquals(ItemType('z').priority(), 26)
    assertEquals(ItemType('Z').priority(), 52)
    assertEquals(ItemType('L').priority(), 38)
  }

}

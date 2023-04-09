package solution

import munit.FunSuite
import solution.Day6._

class Day6Suite extends FunSuite with BaseTest {
  override def input: String =
    """bvwbjplbgvbhsrlpgdmjqwftvncz
      |nppdvjthqldpwncqszvftbrmjlhg
      |""".stripMargin


  test("test start-of-packet marker") {
    assertEquals(Buffer("bvwbjplbgvbhsrlpgdmjqwftvncz").startOfPacketMarker(), 5)
    assertEquals(Buffer("nppdvjthqldpwncqszvftbrmjlhg").startOfPacketMarker(), 6)
  }

  test("test start-of-message marker") {
    assertEquals(Buffer("mjqjpqmgbljsphdztnvjfqwrcgsmlb").startOfMessageMarker(), 19)
    assertEquals(Buffer("bvwbjplbgvbhsrlpgdmjqwftvncz").startOfMessageMarker(), 23)
  }

}

package solution

trait BaseTest {
  def input: String

  def lines: List[String] = input.linesIterator.toList
}

package solution

object Day1 extends Solution {
  override type Answer = Int
  override val day: Int = 1

  override def solveFirstPart(lines: List[String]): Answer = elves(lines).max

  override def solveSecondPart(lines: List[String]): Answer = elves(lines).sorted.reverse.take(3).sum

  def elves(lines: List[String]): List[Int] =
    lines.foldRight(List(0)) { (line, res) =>
      if (line.isEmpty) 0 :: res
      else (res.head + line.toInt) :: res.tail
    }
}

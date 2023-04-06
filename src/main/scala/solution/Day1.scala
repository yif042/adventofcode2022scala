package solution

object Day1 extends Solution {
  override val day: Int = 1

  override def solveFirstPart(): Int = elves.max

  override def solveSecondPart(): Int = elves.sorted.reverse.take(3).sum

  private def elves: List[Int] = {
    val res = List.newBuilder[Int]

    var acc = 0
    for (line <- this.lines) {
      if (line == "") {
        res += acc
        acc = 0
      } else {
        acc += line.toInt
      }
    }

    res.result()
  }
}

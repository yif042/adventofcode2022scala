package solution

object Day6 extends Solution {
  override val day: Int = 6
  type Answer = Int

  override def solveFirstPart(lines: List[String]): Answer =
    lines
      .map(Buffer)
      .map(_.startOfPacketMarker())
      .sum

  override def solveSecondPart(lines: List[String]): Answer =
    lines
      .map(Buffer)
      .map(_.startOfMessageMarker())
      .sum

  case class Buffer(s: String) extends AnyVal {
    def startOfPacketMarker(): Int = distinctWindowFor(4)

    def startOfMessageMarker(): Int = distinctWindowFor(14)

    private def distinctWindowFor(length: Int): Int = {
      var visited = Set.empty[Char]
      var i = 0
      for (j <- 0 until s.length) {
        while (visited contains s(j)) {
          visited -= s(i)
          i += 1
        }
        visited += s(j)
        if (visited.size == length) return j + 1
      }

      -1
    }
  }
}


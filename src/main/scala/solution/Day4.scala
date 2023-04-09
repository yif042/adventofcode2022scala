package solution

object Day4 extends Solution[Int] {
  override val day: Int = 4

  override def solveFirstPart(lines: List[String]): Int =
    parse(lines).count(_.containsAnother())

  override def solveSecondPart(lines: List[String]): Int =
    parse(lines).count(_.overlaps())

  def parse(lines: List[String]): List[Assignment] =
    lines.map { line =>
      val Array(r1, r2) = line.split(",")

      val Array(lo1, hi1) = r1.split("-")
      val Array(lo2, hi2) = r2.split("-")

      Assignment(
        Range(lo1.toInt, hi1.toInt),
        Range(lo2.toInt, hi2.toInt)
      )
    }

  case class Range(lo: Int, hi: Int) {
    def contains(that: Range): Boolean =
      lo <= that.lo && hi >= that.hi

    def overlaps(that: Range): Boolean =
      !(lo > that.hi || that.lo > hi)
  }

  case class Assignment(r1: Range, r2: Range) {
    def containsAnother(): Boolean =
      r1.contains(r2) || r2.contains(r1)

    def overlaps(): Boolean = r1.overlaps(r2)
  }
}


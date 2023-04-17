package solution

object Day8 extends Solution {
  override val day: Int = 8
  override type Answer = Int

  private val dirs = List(
    (1, 0),
    (-1, 0),
    (0, 1),
    (0, -1)
  )

  override def solveFirstPart(lines: List[String]): Answer = {
    val forrest = parse(lines)

    def canSee(i: Int, j: Int): Boolean = {
      for ((di, dj) <- dirs) {
        var ni = i + di
        var nj = j + dj
        var canSee = true
        while (ni >= 0 && ni < forrest.size
          && nj >= 0 && nj < forrest.head.size
          && canSee) {
          if (forrest(ni)(nj).h >= forrest(i)(j).h) canSee = false

          ni += di
          nj += dj
        }

        if (canSee) return canSee
      }

      false
    }

    Vector.tabulate(forrest.size, forrest.head.size)(canSee)
      .flatten
      .count(_ == true)
  }

  override def solveSecondPart(lines: List[String]): Answer = {
    val forrest = parse(lines)

    def score(i: Int, j: Int): Int = {
      var res = 1
      for ((di, dj) <- dirs) {
        var ni = di + i
        var nj = dj + j
        var canSee = 0
        var loopEnd = false
        while (
          forrest.indices.contains(ni)
            && forrest.head.indices.contains(nj)
            && !loopEnd
        ) {
          if (forrest(ni)(nj).h >= forrest(i)(j).h) loopEnd = true
          canSee += 1

          ni += di
          nj += dj
        }

        res *= canSee
      }

      res
    }

    Vector.tabulate(forrest.size, forrest.head.size)(score)
      .flatten
      .max
  }

  final case class Height(h: Int) extends AnyVal

  def parse(lines: List[String]): Vector[Vector[Height]] =
    lines
      .map(line =>
        line.sliding(1).map(x => Height(x.toInt)).toVector)
      .toVector
}


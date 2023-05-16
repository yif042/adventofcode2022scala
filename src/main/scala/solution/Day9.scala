package solution

object Day9 extends Solution {
  override val day: Int = 9

  override type Answer = Int

  private type Position = (Int, Int)

  trait Dir {
    def moveOne(pos: Position): Position =
      this match {
        case Upper => (pos._1, pos._2 + 1)
        case Down => (pos._1, pos._2 - 1)
        case Left => (pos._1 - 1, pos._2)
        case Right => (pos._1 + 1, pos._2)
      }
  }

  private case object Upper extends Dir

  private case object Down extends Dir

  private case object Left extends Dir

  private case object Right extends Dir

  object Dir {
    def fromString(s: String): Dir =
      s match {
        case "U" => Upper
        case "D" => Down
        case "L" => Left
        case "R" => Right
      }
  }

  case class Move(dir: Dir, step: Int)

  def parse(lines: List[String]): List[Move] =
    lines.map(line => {
      val splitGroup = line.split(" ")
      val dir = Dir.fromString(splitGroup(0))
      val step = splitGroup(1).toInt
      Move(dir, step)
    })


  private def dist(p1: Position, p2: Position): Double =
    Math.sqrt(Math.pow(p1._1 - p2._1, 2) + Math.pow(p1._2 - p2._2, 2))

  def follow(head: Position, tail: Position): Position = {
    val (hx, hy) = head
    val (tx, ty) = tail

    val x = if (hx == tx) tx else if (hx > tx) tx + 1 else tx - 1
    val y = if (hy == ty) ty else if (hy > ty) ty + 1 else ty - 1

    (x, y)
  }

  private def tailCount(snakeLength: Int, moves: List[Move]): Int =  {
    val snake = Array.fill(snakeLength)((0, 0))

    var tailSet = Set(snake.last)

    for (move <- moves; _ <- 0 until move.step) {
      snake(0) = move.dir.moveOne(snake(0))

      for (i <- snake.indices.tail) {
        if (dist(snake(i), snake(i - 1)) > 1.5) {
          snake(i) = follow(snake(i - 1), snake(i))
        }
        tailSet += snake.last
      }
    }

    tailSet.size
  }


  override def solveFirstPart(lines: List[String]): Answer =
    tailCount(2, parse(lines))

  override def solveSecondPart(lines: List[String]): Answer =
    tailCount(10, parse(lines))
}


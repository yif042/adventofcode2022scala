package solution

object Day2 extends Solution[Int] {
  override val day: Int = 2

  override def solveFirstPart(lines: List[String]): Int =
    decodeFirst(lines).map(_.point()).sum

  def decodeFirst(lines: List[String]): List[Game] = {
    val yourCode = Map(
      "A" -> Rock,
      "B" -> Paper,
      "C" -> Scissors
    )

    val oppoCode = Map(
      "X" -> Rock,
      "Y" -> Paper,
      "Z" -> Scissors
    )

    lines.map { line =>
      val splitGroup = line.split(" ")
      val you = yourCode(splitGroup(0))
      val oppo = oppoCode(splitGroup(1))
      Game.make(oppo, you)
    }
  }

  override def solveSecondPart(lines: List[String]): Int =
    decodeSecond(lines).map(_.point()).sum

  def decodeSecond(lines: List[String]): List[Game] = {
    val oppoCode = Map(
      "A" -> Rock,
      "B" -> Paper,
      "C" -> Scissors
    )

    val resultCode = Map(
      "X" -> Lose,
      "Y" -> Draw,
      "Z" -> Win,
    )

    lines.map { line =>
      val splitGroup = line.split(" ")
      val oppo = oppoCode(splitGroup(0))
      val result = resultCode(splitGroup(1))
      Game.make(result, oppo)
    }

  }

  sealed trait Move

  case object Rock extends Move

  case object Paper extends Move

  case object Scissors extends Move

  object Move {
    private val winningMap: Map[Move, Move] = Map(
      Rock -> Scissors,
      Paper -> Rock,
      Scissors -> Paper
    )

    private val loseMap: Map[Move, Move] = winningMap.map { case (k, v) => (v, k) }

    def win(you: Move): Move = winningMap(you)

    def lose(you: Move): Move = loseMap(you)
  }


  sealed trait Result

  case object Win extends Result

  case object Draw extends Result

  case object Lose extends Result

  case class Game private(result: Result, yourMove: Move, oppoMove: Move) {
    def point(): Int = {
      val movePoint = yourMove match {
        case Rock => 1
        case Paper => 2
        case Scissors => 3
      }

      val resultPoint = result match {
        case Win => 6
        case Draw => 3
        case Lose => 0
      }

      resultPoint + movePoint
    }
  }

  object Game {
    def make(yourMove: Move, oppoMove: Move): Game = {
      val result =
        if (yourMove == oppoMove) Draw
        else if (Move.win(yourMove) == oppoMove) Win
        else Lose

      new Game(result, yourMove, oppoMove)
    }


    def make(result: Result, oppoMove: Move): Game = {
      val yourMove = if (result == Draw) oppoMove
      else if (result == Win) Move.lose(oppoMove)
      else Move.win(oppoMove)

      new Game(result, yourMove, oppoMove)
    }
  }
}

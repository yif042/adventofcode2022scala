package solution

import cats.effect.{IO, IOApp}
import cats.implicits._
import fs2.io.file.{Files, Path}

object GenerateTestFiles extends IOApp.Simple {

  def generate(day: Int): IO[Unit] = {
    val content = {
      s"""package solution
         |
         |import munit.FunSuite
         |import Day$day._
         |
         |class Day${day}Suite extends FunSuite with BaseTest {
         |  override def input: String = ???
         |
         |  test("first part answer") {
         |    assertEquals(
         |      solveFirstPart(lines),
         |      ???
         |    )
         |  }
         |
         |  test("second part answer") {
         |    assertEquals(
         |      solveSecondPart(lines),
         |      ???
         |    )
         |  }
         |
         |}
         |""".stripMargin
    }

    val filename = s"Day${day}Suite.scala"
    val path = Path(s"src/test/scala/solution/$filename")

    // check path file exist
    Files[IO].exists(path).flatMap {
      case true => IO.unit
      case false =>
        fs2.Stream(content)
          .through(fs2.text.utf8.encode)
          .through(Files[IO].writeAll(path))
          .compile
          .drain
    }
  }

  override def run: IO[Unit] =
    Range.inclusive(1, 25)
      .map(generate)
      .toList
      .sequence_
}

import cats.effect.{IO, IOApp}
import cats.implicits._
import fs2.io.file.{Files, Path}
import fs2.text


// generate day[1-25].scala with template
object GenerateFiles extends IOApp.Simple {

  def generate(day: Int): IO[Unit] = {
    val content =
      s"""package solution
         |
         |object Day$day extends Solution {
         |  override val day: Int = $day
         |
         |  override def solveFirstPart(lines: List[String]): Answer = ???
         |
         |  override def solveSecondPart(lines: List[String]): Answer = ???
         |}
         |
         |""".stripMargin

    val filename = s"Day$day.scala"
    val path = Path(s"src/main/scala/solution/$filename")

    // check path file exist
    // if yes => do nothing
    // if no => create file with content
    Files[IO].exists(path).flatMap {
      case true => IO.unit
      case false =>
        fs2.Stream(content)
          .through(text.utf8.encode)
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

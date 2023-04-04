import cats.effect.std.Env
import cats.effect.{IO, IOApp}
import fs2.io.file.{Files, Path}
import org.http4s.{Header, Headers, Method, Request}
import org.http4s.ember.client.EmberClientBuilder
import org.http4s._
import org.typelevel.ci.CIString
import cats.implicits._


// to download inputs file
object DownloadInput extends IOApp.Simple {

  private def requestFor(n: Int, cookieValue: String) = Request[IO](
    method = Method.GET,
    uri = Uri.unsafeFromString(s"https://adventofcode.com/2022/day/$n/input"),
    headers = Headers(
      Header.Raw(CIString("cookie"), cookieValue)
    ),
  )

  private def getContent(req: Request[IO]): IO[String] = {
    EmberClientBuilder
      .default[IO]
      .build
      .use { client =>
        client.expect[String](req)
      }
  }

  private def writeToFile(content: String, path: Path) =
    fs2.Stream(content)
      .through(fs2.text.utf8.encode)
      .through(Files[IO].writeAll(path))


  private def downloadFile(n: Int) = {
    val filename = f"input$n%02d.txt"
    val path = Path(s"src/main/resources/$filename")
    for {
      cookie <- Env[IO].get("AOC_COOKIE")
      content <- getContent(requestFor(n, cookie.getOrElse("")))
      _ <- writeToFile(content, path).compile.drain
    } yield ()
  }

  override def run: IO[Unit] =
    Range.inclusive(1, 25)
      .map(downloadFile)
      .toList
      .sequence_

}

package eu

import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.{Path, Paths, Files}
import scala.collection.JavaConverters._

package object flierl {
  implicit class Ⓢⓣⓡⓘⓝⓖ(val wrapped: String) extends AnyVal {
    def path = Paths get wrapped normalize
  }

  implicit class ⓟⓐⓣⓗ(val wrapped: Path) extends AnyVal {
    def exists = Files exists wrapped
    def isAFile = Files isRegularFile wrapped
    def withExtension(ext: String) = wrapped.resolveSibling(wrapped.getFileName.toString.replaceFirst("^(?:([^.]+)|(?:(.*)\\.[^.]*))$", s"$$1$$2.$ext"))
    def delete = Files deleteIfExists wrapped
    def slurped = new String(Files readAllBytes wrapped, UTF_8)
    def willNowContain(stuff: List[String]) = { Files.write(wrapped, stuff.asJava, UTF_8); wrapped }
  }
}
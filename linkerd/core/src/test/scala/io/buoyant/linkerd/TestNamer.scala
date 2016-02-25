package io.buoyant.linkerd

import com.fasterxml.jackson.annotation.JsonIgnore
import com.twitter.finagle.{Addr, Name, NameTree, Namer, Path}
import com.twitter.util.{Activity, Var}
import io.buoyant.linkerd.config.Parser

class TestNamerInitializer extends NamerInitializer {
  val configClass = classOf[TestNamer]
}

object TestNamerInitializer extends TestNamerInitializer

class TestNamer extends NamerConfig { config =>
  @JsonIgnore
  override def defaultPrefix: Path = Path.read("/foo")

  var buh: Option[Boolean] = None

  @JsonIgnore
  override def newNamer(): Namer = new Namer {

    val buh = config.buh.getOrElse(false)

    def lookup(path: Path): Activity[NameTree[Name]] = {
      val t = path match {
        case Path.Utf8("buh", _*) if !buh => NameTree.Neg
        case path =>
          val addr = Var.value(Addr.Pending)
          NameTree.Leaf(Name.Bound(addr, prefix, path))
      }
      Activity.value(t)
    }
  }
}

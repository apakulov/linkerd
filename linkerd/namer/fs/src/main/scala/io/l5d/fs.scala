package io.l5d

import com.fasterxml.jackson.annotation.JsonIgnore
import com.twitter.finagle.Path
import io.buoyant.linkerd.config.types.Directory
import io.buoyant.linkerd.namer.fs.WatchingNamer
import io.buoyant.linkerd.{NamerConfig, NamerInitializer}

class fsInitializer extends NamerInitializer {
  val configClass = classOf[fs]
}

case class fs(rootDir: Directory) extends NamerConfig {
  @JsonIgnore
  override def defaultPrefix: Path = Path.read("/io.l5d.fs")

  /**
   * Construct a namer.
   */
  @JsonIgnore
  def newNamer() = new WatchingNamer(rootDir.path, prefix)
}


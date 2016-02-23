package io.l5d.experimental

import com.twitter.finagle.util.LoadService
import io.buoyant.linkerd.NamerInitializer
import org.scalatest.FunSuite

class ConsulTest extends FunSuite {

  test("sanity") {
    // ensure it doesn't totally blowup
    consul(None, None).newNamer()
  }

  test("service registration") {
    assert(LoadService[NamerInitializer]().exists(_.isInstanceOf[consulInitializer]))
  }
}

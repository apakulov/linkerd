package io.l5d.experimental

import com.twitter.finagle.util.LoadService
import io.buoyant.linkerd.NamerInitializer
import org.scalatest.FunSuite

class MarathonTest extends FunSuite {

  test("sanity") {
    // ensure it doesn't totally blowup
    marathon(None, None, None).newNamer()
  }

  test("service registration") {
    assert(LoadService[NamerInitializer]().exists(_.isInstanceOf[MarathonInitializer]))
  }
}

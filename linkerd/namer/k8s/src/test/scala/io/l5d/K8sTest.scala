package io.l5d.experimental

import com.twitter.finagle.util.LoadService
import io.buoyant.linkerd.NamerInitializer
import org.scalatest.FunSuite

class K8sTest extends FunSuite {

  test("sanity") {
    // ensure it doesn't totally blowup
    k8s(None, None, None, None, None).newNamer()
  }

  test("service registration") {
    assert(LoadService[NamerInitializer]().exists(_.isInstanceOf[k8sInitializer]))
  }
}

package org.emv.terminal.config

import org.emv._
import org.iso7816.AID
import org.lau.tlv.ber.BerTLV
import scodec.bits._

import scala.collection.immutable.List

/**
  * Created by lau on 11/2/16.
  */
case class DefaultPayPassConfig() extends BrandParameters(AID(hex"A00000041010")) {
  override val tlv: List[BerTLV] = List()
}

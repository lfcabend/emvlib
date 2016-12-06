package org.emv.terminal.config

import org.emv._
import org.tlv._
import org.tlv.HexUtils._
import org.tlv.HexUtils
import org.iso7816.AID

import scala.collection.immutable.List

/**
  * Created by lau on 11/2/16.
  */
class DefaultPayPassConfig() extends BrandParameters(
  AID("A00000041010".fromHex),
  List())

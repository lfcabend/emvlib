package org.emv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.iso7816.AID
import org.lau.tlv.ber._
import org.lau.visa.VisaTerminalProcessor
import scodec.bits.ByteVector
import scodec.bits._

/**
  * Created by lau on 3/8/17.
  */
object BrandParserMapping {

  val mapping: Map[AID, Parser[EMVTLVType]] = Map(
    AID(ByteVector.fromValidHex("A0000000031010")) -> org.lau.visa.Parser.parseEMVTLV
  )
}

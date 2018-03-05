package org.emv

import org.iso7816.AID

import scala.collection.parallel.immutable
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import org.lau.visa.VisaTerminalProcessor
import scodec.bits.ByteVector
import scodec.bits._

/**
  * Created by lau on 2/15/17.
  */
object BrandProcessorMap {

  val mapping: Map[AID, Processor] = Map(
    AID(hex"A0000000031010") -> VisaTerminalProcessor
  )

}
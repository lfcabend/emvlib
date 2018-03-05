package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVTLVLeaf}
import org.iso7816.AID
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 6/5/16.
  */
case class ApplicationIdentifier(val aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationIdentifier.tag

  override val value: ByteVector = aid.value

}

object ApplicationIdentifier extends EMVAIDSpec[ApplicationIdentifier] {

  val tag: BerTag = berTag"9F06"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationIdentifier] =
    parseEMVBySpec(ApplicationIdentifier, parseB(_).map(AID(_)))

}

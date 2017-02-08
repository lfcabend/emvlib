package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVTLVLeaf, EMVTLVParser}
import org.iso7816.AID
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 6/5/16.
  */
case class ApplicationDedicatedFileName(aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationDedicatedFileName.tag

  override val value: ByteVector = aid.value

}

object ApplicationDedicatedFileName extends EMVAIDSpec[ApplicationDedicatedFileName] {

  val tag: BerTag = berTag"4F"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser: Parser[ApplicationDedicatedFileName] =
    parseEMVBySpec(ApplicationDedicatedFileName, parseB(_).map(AID(_)))

}

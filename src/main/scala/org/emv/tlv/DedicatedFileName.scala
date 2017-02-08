package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVBinaryWithVarLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.iso7816.AID
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 9/1/16.
  */
case class DedicatedFileName (aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = DedicatedFileName.tag

  override val value: ByteVector = aid.value

}

object DedicatedFileName extends EMVAIDSpec[DedicatedFileName] {

  val tag: BerTag = berTag"84"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[DedicatedFileName] =
    parseEMVBySpec(DedicatedFileName, parseB(_).map(AID(_)))


}
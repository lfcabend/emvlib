package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAIDSpec, EMVTLVLeaf}
import org.iso7816.AID
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class DirectoryDefinitionFileName(aid: AID) extends EMVTLVLeaf {

  override val tag: BerTag = DataAuthenticationCode.tag

  override val value: ByteVector = aid.value

}

object DirectoryDefinitionFileName extends EMVAIDSpec[DirectoryDefinitionFileName] {

  val tag: BerTag = berTag"9D"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[DirectoryDefinitionFileName] =
    parseEMVBySpec(DirectoryDefinitionFileName, parseB(_).map(AID(_)))

}
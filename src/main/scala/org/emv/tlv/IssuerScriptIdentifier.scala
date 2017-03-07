package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/10/16.
  */
case class IssuerScriptIdentifier  (override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IssuerScriptIdentifier.tag

  override val templates = Set(IssuerScriptTemplate1.tag,
    IssuerScriptTemplate1.tag)
}

object IssuerScriptIdentifier extends EMVDefaultBinaryWithLengthSpec[IssuerScriptIdentifier] {

  val tag = berTag"9F18"

  override val length = 4

  def parser = parseEMVBySpec(IssuerScriptIdentifier, parseB(_))

}


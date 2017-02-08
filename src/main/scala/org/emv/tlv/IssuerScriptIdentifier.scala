package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/10/16.
  */
case class IssuerScriptIdentifier  (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerScriptIdentifier.tag

}

object IssuerScriptIdentifier extends EMVDefaultBinaryWithLengthSpec[IssuerScriptIdentifier] {

  val tag: BerTag = berTag"9F18"

  override val length: Int = 4

  def parseIssuerScriptIdentifier: Parser[IssuerScriptIdentifier] =
    parseEMVBySpec(IssuerScriptIdentifier, parseB(_))

}


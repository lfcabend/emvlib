package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
//TODO i think payload can be a APDUCommand
case class IssuerScriptCommand  (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerScriptCommand.tag

}

object IssuerScriptCommand extends EMVDefaultBinaryWithVarLengthSpec[IssuerScriptCommand] {

  val tag: BerTag = berTag"86"

  override val maxLength: Int = 261

  override val minLength: Int = 0

  def parser: Parser[IssuerScriptCommand] =
    parseEMVBySpec(IssuerScriptCommand, parseB(_))

}


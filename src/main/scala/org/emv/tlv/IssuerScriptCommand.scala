package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
//TODO i think payload can be a APDUCommand
case class IssuerScriptCommand  (override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IssuerScriptCommand.tag

  override val templates = Set(IssuerScriptTemplate1.tag,
    IssuerScriptTemplate1.tag)
}

object IssuerScriptCommand extends EMVDefaultBinaryWithVarLengthSpec[IssuerScriptCommand] {

  val tag: BerTag = berTag"86"

  override val maxLength = 261

  override val minLength = 0

  def parser = parseEMVBySpec(IssuerScriptCommand, parseB(_))

}


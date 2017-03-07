package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import scodec.bits.ByteVector


/**
  * Created by lau on 2/17/17.
  */
case class TransactionCertificateDataObjectList(val list: List[(BerTag, Int)])
  extends DOL with TemplateTag {

  override val tag = TransactionCertificateDataObjectList.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object TransactionCertificateDataObjectList extends EMVDOLSpec[TransactionCertificateDataObjectList] {

  val tag = berTag"97"

  override val maxLength = 252

  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(TransactionCertificateDataObjectList, parseDOL(_))

}

package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.joda.time.LocalDate
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionDate(val date: LocalDate) extends EMVTLVLeafWithDate {

  override val tag = TransactionDate.tag

}

object TransactionDate extends EMVDateSpec[TransactionDate] {

  val tag = berTag"9A"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser = parseEMVBySpec(TransactionDate, parseDate(_))

}

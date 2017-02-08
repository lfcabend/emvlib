package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDateSpec, EMVTLVLeafWithDate, EMVTLVParser, LeafToStringHelper}
import org.joda.time.LocalDate
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationExpirationDate(val date: LocalDate) extends EMVTLVLeafWithDate {

  override val tag: BerTag = ApplicationExpirationDate.tag

}

object ApplicationExpirationDate extends EMVDateSpec[ApplicationExpirationDate] {

  val tag: BerTag = berTag"5F24"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationExpirationDate] =
    parseEMVBySpec(ApplicationExpirationDate, parseDate(_))

}

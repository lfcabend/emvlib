package org.emv.tlv

  import scodec.bits._
  import org.emv.tlv.EMVTLV._
  import org.emv.tlv.EMVTLV.EMVTLVParser._
  import org.joda.time.LocalTime
  import org.lau.tlv.ber._

/**
  * Created by lau on 2/17/17.
  */
case class TransactionTime(override val time: LocalTime)
  extends EMVTLVLeafWithTime {

  override val tag = TransactionTime.tag

}

object TransactionTime extends EMVTimeSpec[TransactionTime] {

  val tag = berTag"9F21"

  def parser = parseEMVBySpec(TransactionTime,
    parseTime(_))

}
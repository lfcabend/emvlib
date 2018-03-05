package org.emv.tlv

import java.util.Currency

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationReferenceCurrency(val currencies: List[Currency])
  extends EMVTLVLeaf with TemplateTag {

  override def toString: String =
    s"""${super.toString}
       |\t${currencies.map((x: Currency) => CurrencyHelper.toString(x)).mkString("\n\t")}
     """.stripMargin

  override val tag: BerTag = ApplicationReferenceCurrency.tag

  override val value: ByteVector = currencies.map((x: Currency) => CurrencyHelper.toValue(x.getNumericCode)).
    foldRight[ByteVector](ByteVector.empty)(_ ++ _)

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object ApplicationReferenceCurrency extends EMVAlphaNumericWithVarLengthSpec[List[Currency], ApplicationReferenceCurrency] {

  val tag: BerTag = berTag"9F3B"

  override val max: Int = 16
  override val min: Int = 4
  override val maxLength: Int = 8
  override val minLength: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationReferenceCurrency] =
    parseEMVBySpec(ApplicationReferenceCurrency, x => P(parseCurrencyCode(2).rep(exactly = x / 2).map(_.toList)))

}



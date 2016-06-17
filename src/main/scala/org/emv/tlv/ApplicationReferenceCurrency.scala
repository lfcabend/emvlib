package org.emv.tlv

import java.util.Currency

import org.emv.tlv.EMVTLV.{EMVAlphaNumericWithVarLengthSpec, EMVAlphaNumericSpec, EMVTLVLeaf, LeafToStringHelper}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationReferenceCurrency(val currencies: List[Currency])
  extends EMVTLVLeaf {

  override def toString: String =
    s"""${super.toString}
       |\t${currencies.map((x: Currency) => CurrencyHelper.toString(x)).mkString("\n\t")}
     """.stripMargin

  override val tag: BerTag = ApplicationReferenceCurrency.tag

  override val value: Seq[Byte] = currencies.map((x: Currency) => CurrencyHelper.toValue(x.getNumericCode)).
    foldRight[Seq[Byte]](Nil)(_ ++ _)

}

object ApplicationReferenceCurrency extends EMVAlphaNumericWithVarLengthSpec[List[Currency], ApplicationReferenceCurrency] {

  val tag: BerTag = "9F3B"

  override val max: Int = 16
  override val min: Int = 4
  override val maxLength: Int = 8
  override val minLength: Int = 2
}



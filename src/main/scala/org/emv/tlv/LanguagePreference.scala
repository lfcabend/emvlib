package org.emv.tlv

import java.util.Currency

import com.neovisionaries.i18n.LanguageCode
import org.emv.tlv.EMVTLV.{EMVAlphaNumericWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class LanguagePreference (val languages: List[LanguageCode]) extends EMVTLVLeaf {

  override def toString: String =
    s"""${super.toString}
       |\t${languages.map((x: LanguageCode) => LanguageHelper.toString(x)).mkString("\n\t")}
     """.stripMargin

  override val tag: BerTag = LanguagePreference.tag

  override val value: Seq[Byte] = languages.map(LanguageHelper.toValue(_)).
    foldRight[Seq[Byte]](Nil)((x, y) => x ++ y)

}

object LanguagePreference extends EMVAlphaNumericWithVarLengthSpec[List[LanguageCode], LanguagePreference] {

  val tag: BerTag = "5F2D"

  override val maxLength: Int = 8

  override val minLength: Int = 2
  override val max: Int = 8
  override val min: Int = 2
}


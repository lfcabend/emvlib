package org.emv.tlv

import java.util.Currency

import com.neovisionaries.i18n.LanguageCode
import fastparse.byte.all._
import fastparse.core.Mutable.Success
import fastparse.core.Parsed
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
case class LanguagePreference(val languages: List[LanguageCode])
  extends EMVTLVLeaf with TemplateTag {

  override def toString =
    s"""${super.toString}
       |\t${languages.map((x: LanguageCode) => LanguageHelper.toString(x)).mkString("\n\t")}
     """.stripMargin

  override val tag = LanguagePreference.tag

  override val value = languages.map(LanguageHelper.toValue(_)).
    foldRight[ByteVector](ByteVector.empty)((x, y) => x ++ y)

  override val templates = Set(FileControlInformationProprietaryTemplate.tag)

}

object LanguagePreference extends EMVAlphaNumericWithVarLengthSpec[List[LanguageCode], LanguagePreference] {

  val tag = berTag"5F2D"

  override val maxLength = 8

  override val minLength = 2
  override val max = 8
  override val min = 2

  def parser = parseEMVBySpec(LanguagePreference, parseMultipleLanguages(_))

  def parseMultipleLanguages(x: Int): Parser[List[LanguageCode]] = {
    val toLanguageCode: ByteVector => LanguageCode = x =>
      LanguageHelper.getLanguageInstance(new String(x.toArray)).get

    parseAN(2, 2)(2).rep(exactly = x / 2).!.map(x => {
      x.grouped(2).toList.map(y => toLanguageCode(y))
    })
  }

  def parseALanguage: Parser[LanguageCode] = parseAN(2, 2)(2).
    map(x => LanguageHelper.getLanguageInstance(new String(x.toArray))).flatMap({
    case Some(z) => PassWith(z)
    case None => Fail.opaque("Is not a language")
  })


}


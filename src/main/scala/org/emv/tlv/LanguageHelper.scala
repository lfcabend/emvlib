package org.emv.tlv

import java.util.Currency

import com.neovisionaries.i18n.LanguageCode
import org.tlv.HexUtils

/**
  * Created by lau on 11/17/16.
  */
trait LanguageHelper {

  override def toString: String =
    s"""${super.toString}
       |\t${LanguageHelper.toString(language)}
     """.stripMargin

  val value: Seq[Byte] =  LanguageHelper.toValue(language)

  val language: LanguageCode

}

object LanguageHelper {

  def toString(language: LanguageCode): String = s"${language.getName}"

  def toValue(language: LanguageCode) = language.getName.getBytes("ASCII")

  def getLanguageInstance(languageString: String): Option[LanguageCode] =
    LanguageCode.valueOf(languageString) match {
      case null => None
      case l => Some(l)
    }

}
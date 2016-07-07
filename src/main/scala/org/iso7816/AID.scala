package org.iso7816

import java.nio.charset.Charset

import org.tlv.HexUtils

/**
  * Created by Lau on 4/25/2016.
  */
case class AID(val value: Seq[Byte]) {

  require(value != null)
  require(value.length >= 5 && value.length <= 16)

  val rid: Seq[Byte] = value.take(5)

  val pix: Seq[Byte] = value.drop(5)

  val category = value(0) match {
    case x if ((value(0) & 0xA0) == 0xA0) => AIDCategory.INTERNATIONAL
    case x if ((value(0) & 0xD0) == 0xD0) => AIDCategory.NATIONAL
    case x if ((value(0) & 0xE0) == 0xE0) => AIDCategory.STANDARD
    case _ => AIDCategory.PROPRIETARY
  }

}

object AID {

  val PPSE: AID = new AID("".getBytes(Charset.forName("ASCII")))

}

object AIDCategory extends Enumeration {
  val INTERNATIONAL, NATIONAL, STANDARD, PROPRIETARY, RESERVED = Value
}


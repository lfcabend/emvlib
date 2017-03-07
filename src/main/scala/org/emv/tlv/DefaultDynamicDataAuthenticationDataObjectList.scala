package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVDOLSpec
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/3/16.
  */
case class DefaultDynamicDataAuthenticationDataObjectList(val list: List[(BerTag, Int)])




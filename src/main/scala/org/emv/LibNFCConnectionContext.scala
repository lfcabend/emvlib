package org.emv

import nfc._

/**
  * Created by lau on 12/3/16.
  */
case class LibNFCConnectionContext(ctxPtr: SWIGTYPE_p_p_nfc_context,
                                   ctx: SWIGTYPE_p_nfc_context,
                                   reader: SWIGTYPE_p_nfc_device,
                                   mod: nfc_modulation,
                                   target: Option[nfc_target])
  extends ConnectionContext

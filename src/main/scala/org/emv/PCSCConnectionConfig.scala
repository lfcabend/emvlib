package org.emv

/**
  * Created by lcabenda on 3/23/2017.
  */
trait PCSCConnectionConfig extends ConnectionConfig {

  val preferredReader:String

  val waitForCard: Int

}

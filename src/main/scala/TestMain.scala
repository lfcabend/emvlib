import org.emv.LibNFCCard
import org.tlv.HexUtils._

import scalaz.\/-

/**
  * Created by lau on 12/6/16.
  */
object TestMain extends App {

  val p = for {
    cntx <- LibNFCCard.waitForCardOnTerminal
    r <- LibNFCCard.transmit(cntx, "00A404000E325041592E5359532E444446303100".fromHex)
  } yield(r)


  p.unsafePerformSyncAttempt match {
    case \/-(x) => println(x)
    case _ =>
  }

}

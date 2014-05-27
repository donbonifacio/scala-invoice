
package scalainvoice

import scalainvoice.DateHelper._
import scalainvoice.Rounding._

import java.security._
import java.security.spec._
import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher
import javax.xml.bind.DatatypeConverter

object DocumentSigner {

  val validForSign = (document : Document) => {
    document.date != None && document.finalDate != None
  }

  val rawSaftFields = (document : Document) => {
    Seq(
        saftDateStr(document.date.get),
        saftDateTimeStr(document.finalDate.get),
        document.number.get,
        roundPrint(document.total)
      ) mkString("", ";", ";")
  }

  val stringToSign = (document: Document) => {
    if(!validForSign(document)) {
      None
    } else {
      Some(rawSaftFields(document))
    }
  }

  def encodeBase64(bytes: Array[Byte]) = DatatypeConverter.printBase64Binary(bytes)
  def decodeBase64(str : String) = DatatypeConverter.parseBase64Binary(str)

  def loadPrivateKey(key64 : String) = {
      val clear = decodeBase64(key64);
      val keySpec = new PKCS8EncodedKeySpec(clear);
      val fact = KeyFactory.getInstance("RSA");
      val priv = fact.generatePrivate(keySpec);
      priv
  }

  val sign = (document : Document) => {
    val raw = decodeBase64(stringToSign(document).get)

    val privateKeyStr= "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMIz27gM22V7RTnggKq6OE2clu6gfMdYJzcrWO/spqTqfbbjJdEqpG3r432s0Wkpwz4djRZ71X2hYDy6Q1f5fTk5drRej9WaHfmb2wSDEZutoghYICKI0psLu9cBN4psFcl90Hs5943utoURKoLIhgdJmINiscpCUsQcr9zXCr7pAgMBAAECgYEAvwXjihabeCbY4SVDaqvh1fXBQNf75NmBOXMZ+GX/RRiir9Ke0C/xrW3qRyRGl/rMYb0Pmp4UUY766sEMnOx7jqeNvOUIFx45QBOLWLW8JtTq7Ux/kWypYj/S7zcQBaBZIsvAxPFkwauPjleFSCpmDdzjsWSPyk4zfjLxaFIkcQECQQDjPA0pF3DMW7lL/4olU3HLvEQbvoiPs4siYDq9Tthhjm6CWo/iDRgorWVJBggrw7+j3KZnGvQnrlpd4tmqI0o1AkEA2slYSDHRHP2q0Q0Ntal2ojlgIcVNjN70jZYiSyfNnaCRaoNjCv6nRPt8xnngh9yNZuMjKTSsLRPP09V7YLOYZQJAQfb/1wrv+tMxwmcnYliL81nu56LzH8w+Ord2tdf6pq9jU0IY/MVSWxSjD4TJwHKt4lK+l7XSZOaPYyvkhiiCdQJAa3qL/vNSGh7XglkaSgrU976d5hkqDrEcjE2bzTXhgbT2ms+uoSEWhQHSeyI2VifX4xpkFIMGriJUb2kpcgjoLQJAbvce2aCG/8eVmes/xTWjADxNaBTe+shnQW6h0xB6qon7YPhGA/5r0eSn6vp1Xn/Q2BYijZyBXPnN7bJuIBIIiA=="

    val privateKey = loadPrivateKey(privateKeyStr)
    val signer = Signature.getInstance("SHA1withRSA");
    signer.initSign(privateKey);
    signer.update(raw);

    document.copy(saftHash = Some(encodeBase64(signer.sign())))
  }


}

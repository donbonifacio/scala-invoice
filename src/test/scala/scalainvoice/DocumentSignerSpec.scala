
package scalainvoice.tests

import scalainvoice._
import scalainvoice.DocumentSigner._
import scalainvoice.DateHelper._
import com.github.nscala_time.time.Imports._

class DocumentSignerSpec extends UnitSpec {

  val sampleDate = new DateTime("2014-05-05T03:00:00")
  val document = Document(
    total = 1200,
    date = Some(sampleDate),
    finalDate = Some(sampleDate),
    sequenceNumber = Some(1),
    sequenceSerie = Some("serie1")
  )

  it("considers the necessary fields") {
    println(stringToSign(document))
    stringToSign(document).get should equal(
      s"${saftDateStr(document.date.get)};${saftDateTimeStr(document.finalDate.get)};FT serie1/1;1200.00;"
    )
  }

  it("generates proper crypto fields") {
    val signed = sign(document)
    //signed.saftHash should equal(Some("Ftnl4wWn48g2j+hswFgc/4m0PeEEFwHAzzPBYgDgGkcuhK8t3Hj4LIJ3joFY/4qD7xBaQipmW2W2FcuhKERLBJP8hHsVUS43wReIpscMO055ILs976LkUDtH1NSW3GbzJfxTtBtXVwjT1ezhY8sgTLzchM1fL8UqkR+hJGEWchQ="))
  }

}

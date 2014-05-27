
package scalainvoice

import com.github.nscala_time.time.Imports._

case class Document(
  val status : String = "draft",
  val items : Seq[DocumentItem] = Nil,
  val total : Double = 0,
  val date : Option[DateTime] = None,
  val finalDate : Option[DateTime] = None,
  val totalBeforeTaxes : Double = 0,
  val totalTaxes : Double = 0,
  val sequenceNumber : Option[Int] = None,
  val sequenceSerie : Option[String] = None,
  val saftHash : Option[String] = None
) {

  def number = {
    if(sequenceNumber == None || sequenceSerie == None) {
      None
    } else {
      Some(s"FT ${sequenceSerie.get}/${sequenceNumber.get}")
    }
  }

}

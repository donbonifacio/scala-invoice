
package scalainvoice

case class Document(
  val status : String = "draft",
  val items : Seq[DocumentItem] = Nil,
  val total : Double = 0,
  val totalBeforeTaxes : Double = 0,
  val totalTaxes : Double = 0
)

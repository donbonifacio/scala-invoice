
package scalainvoice

case class DocumentItem(
  val unitPrice : Double = 1,
  val quantity : Double = 1,
  val total : Double = 0,
  val totalBeforeTaxes : Double = 0
)

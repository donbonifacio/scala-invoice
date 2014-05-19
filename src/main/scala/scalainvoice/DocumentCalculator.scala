
package scalainvoice

object DocumentCalculator {

  val round = Rounding.rounder
  def itemTotalsCalculator = DocumentItemCalculator.calculateItemTotals _

  def calculateDocumentTotals(document : Document) : Document = {
    val calculatedItems = document.items map itemTotalsCalculator
    document.copy(
      items = calculatedItems,
      total = round(calculatedItems map(_.total) sum),
      totalBeforeTaxes = round(calculatedItems map(_.totalBeforeTaxes) sum),
      totalTaxes = round(calculatedItems map(_.totalTaxes) sum)
    )
  }

}

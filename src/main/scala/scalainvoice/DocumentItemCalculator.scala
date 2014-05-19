
package scalainvoice

object DocumentItemCalculator {

  val round = Rounding.rounder

  def calculateItemTotals(item : DocumentItem) : DocumentItem = {
    val beforeTaxes = round(item.unitPrice * item.quantity)
    val taxes = round(beforeTaxes * item.taxFactor)

    item.copy(
      total = round(beforeTaxes + taxes),
      totalBeforeTaxes = beforeTaxes,
      totalTaxes = taxes
    )
  }

}


package scalainvoice

object DocumentItemCalculator {

  def Rounder = Rounding

  def calculateItemTotals(item : DocumentItem) : DocumentItem = item.copy(
      total = calculateItemTotal(item),
      totalBeforeTaxes = calculateItemTotalBeforeTaxes(item),
      totalTaxes = calculateItemTax(item)
    )

  def calculateItemTotal(item : DocumentItem) : Double = {
    Rounder.round(
        calculateItemTotalBeforeTaxes(item) + calculateItemTax(item)
      )
  }

  def calculateItemTax(item : DocumentItem) : Double = {
    val raw = calculateItemTotalBeforeTaxes(item)
    Rounder.round(raw * item.taxFactor)
  }

  def calculateItemTotalBeforeTaxes(item : DocumentItem) : Double = {
    Rounder.round(item.unitPrice * item.quantity)
  }

}

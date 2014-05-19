
package scalainvoice

object DocumentItemCalculator {

  def Rounder = Rounding

  def calculateItemTotalBeforeTaxes(item : DocumentItem) : DocumentItem = {
    item.copy(
      totalBeforeTaxes = Rounder.round(item.unitPrice * item.quantity)
    )
  }

}

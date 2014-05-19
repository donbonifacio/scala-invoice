
package scalainvoice.tests

import scalainvoice.DocumentItem
import scalainvoice.DocumentItemCalculator._

class DocumentItemCalculatorSpec extends UnitSpec {

  describe("calculateItemTotalBeforeTaxes") {

    it("properly hanldes zero values") {
      val item = DocumentItem(unitPrice = 1, quantity = 0)
      val withTotal = calculateItemTotalBeforeTaxes(item)
      withTotal.totalBeforeTaxes should equal(0.0)
    }

    it("properly handles integer values") {
      val item = DocumentItem(unitPrice = 15, quantity = 2)
      val withTotal = calculateItemTotalBeforeTaxes(item)
      withTotal.totalBeforeTaxes should equal(30.0)
    }

    it("properly handles decimal values") {
      val item = DocumentItem(unitPrice = 1.5, quantity = 2.5)
      val withTotal = calculateItemTotalBeforeTaxes(item)
      withTotal.totalBeforeTaxes should equal(3.75)
    }

    it("properly rounds values") {
      val item = DocumentItem(unitPrice = 1.111111, quantity = 2.111111)
      val withTotal = calculateItemTotalBeforeTaxes(item)
      withTotal.totalBeforeTaxes should equal(2.3457)
    }

  }

}

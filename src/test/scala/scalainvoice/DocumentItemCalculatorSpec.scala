
package scalainvoice.tests

import scalainvoice.DocumentItem
import scalainvoice.DocumentItemCalculator._

class DocumentItemCalculatorSpec extends UnitSpec {

  describe("calculateItemTotals") {

    it("calculates item totals") {
      val item = DocumentItem(unitPrice = 1, quantity = 10, taxFactor = 0.20)
      val withTotals = calculateItemTotals(item)
      withTotals.total should equal(12.0)
      withTotals.totalTaxes should equal(2.0)
      withTotals.totalBeforeTaxes should equal(10.0)
    }

  }

  describe("calculateItemTax") {

    it("handles zero values") {
      val item = DocumentItem(unitPrice = 1, quantity = 10, taxFactor = 0.0)
      calculateItemTax(item) should equal(0.0)
    }

    it("handles basic percentage") {
      val item = DocumentItem(unitPrice = 1, quantity = 10, taxFactor = 0.20)
      calculateItemTax(item) should equal(2.0)
    }

  }

  describe("calculateItemTotalBeforeTaxes") {

    it("properly hanldes zero values") {
      val item = DocumentItem(unitPrice = 1, quantity = 0)
      calculateItemTotalBeforeTaxes(item) should equal(0.0)
    }

    it("properly handles integer values") {
      val item = DocumentItem(unitPrice = 15, quantity = 2)
      calculateItemTotalBeforeTaxes(item) should equal(30.0)
    }

    it("properly handles decimal values") {
      val item = DocumentItem(unitPrice = 1.5, quantity = 2.5)
      calculateItemTotalBeforeTaxes(item) should equal(3.75)
    }

    it("properly rounds values") {
      val item = DocumentItem(unitPrice = 1.111111, quantity = 2.111111)
      calculateItemTotalBeforeTaxes(item) should equal(2.3457)
    }

  }

}

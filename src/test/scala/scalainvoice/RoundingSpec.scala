
package scalainvoice.tests

import scalainvoice.Rounding._

class RoundingSpec extends UnitSpec {

  describe("round") {

    it("rounds integers") {
      round(1) should equal(1.0)
    }

    it("rounds simple doubles") {
      round(1.0) should equal(1.0)
    }

    it("keeps 1 place") {
      round(1.5) should equal(1.5)
    }

    it("keeps 4 places") {
      round(1.511111) should equal(1.5111)
    }

  }

  describe("roundPrint") {

    it("formats integer") {
      roundPrint(1) should equal("1.00")
    }

    it("formats simple double") {
      roundPrint(1.0) should equal("1.00")
    }

    it("keeps 1 place") {
      roundPrint(1.2) should equal("1.20")
    }

    it("keeps 2 places") {
      roundPrint(1.23) should equal("1.23")
    }

    it("keeps 2 places for 3 places") {
      roundPrint(1.234) should equal("1.23")
    }

  }

}

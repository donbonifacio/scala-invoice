
package scalainvoice.tests

import scalainvoice.{Document, DocumentItem}
import scalainvoice.DocumentCalculator._

class DocumentCalculatorSpec extends UnitSpec {

  it("calculates empty document") {
    val document = Document()
    val calculated = calculateDocumentTotals(document)
    calculated.total should equal(0.0)
  }

  it("calculates one item documents") {
    val document = Document(items = List(
        DocumentItem(unitPrice = 10, quantity = 1)
      ))

    val calculated = calculateDocumentTotals(document)

    calculated.items.head.total should equal(10.0)
    calculated.total should equal(10.0)
  }

  it("calculates several item documents") {
    val document = Document(items = List(
        DocumentItem(unitPrice = 10, quantity = 1),
        DocumentItem(unitPrice = 10, quantity = 1),
        DocumentItem(unitPrice = 10, quantity = 1)
      ))

    val calculated = calculateDocumentTotals(document)
    calculated.total should equal(30.0)
  }
}

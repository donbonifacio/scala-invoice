
package scalainvoice.tests

import scalainvoice.{Document, DocumentItem}
import scalainvoice.FinalizeDocument._

class FinalizeDocumentSpec extends UnitSpec {

  it("finalizes a default document") {
    val document = Document(items = List(
        DocumentItem(unitPrice = 10, quantity = 1)
      ))
    val finalized = finalizeDocument(document)
    finalized.status should equal("sent")
    finalized.total should equal(10.0)
  }

}

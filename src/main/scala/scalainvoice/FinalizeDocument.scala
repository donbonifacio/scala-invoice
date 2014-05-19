
package scalainvoice

object FinalizeDocument {

  val finalizer =
    setFinalizedState _ andThen
    DocumentCalculator.calculateDocumentTotals _

  def finalizeDocument(document : Document) = finalizer(document)

  def setFinalizedState(document : Document) = document.copy(status = "sent")

}

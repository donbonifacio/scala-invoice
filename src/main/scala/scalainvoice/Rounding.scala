
package scalainvoice

object Rounding {

  val NumericPrecision = 4
  val PrintingPrecision = 2

  def roundWithPrecision(precision : Int)(x : Double) : Double = {
    BigDecimal(x).setScale(precision, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def defaultPrecision = roundWithPrecision(NumericPrecision) _
  def printingPrecision = roundWithPrecision(PrintingPrecision) _

  def round( x : Double ) : Double = defaultPrecision(x)
  val rounder = round _
  val mirror = (x:Double) => x

  def roundPrint(x : Double) : String = "%.2f".format(printingPrecision(x))

}

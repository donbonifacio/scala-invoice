
package scalainvoice

import com.github.nscala_time.time.Imports._
import org.joda.time.format
import org.joda.time.format.DateTimeFormat

object DateHelper {

  val saftDateFormatter = DateTimeFormat.forPattern("YYYY-MM-dd");
  val saftDateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd'T'HH:mm:ss");

  val saftDateTimeStr = (date : DateTime) => {
    saftDateTimeFormatter.print(date)
  }

  val saftDateStr = (date : DateTime) => {
    saftDateFormatter.print(date)
  }

}

package myCode

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import myUtil.GetFiles
import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements


class GetLandsatSenceNASA {

  def getModis(year:String,day:String) : Unit = {

    var url: String = s"https://ladsweb.modaps.eosdis.nasa.gov/archive/allData/61/MYD021KM/${year}/${day}/"

    var d: Document = null

    d = Jsoup.connect(url).get()
    val es: Elements = d.getElementsByTag("a")
    var ar : Array[AnyRef] = es.toArray()

   for(a : AnyRef <- ar){
     var href : String = a.toString
     if(href.contains(".hdf")){
       val newUrl = "http://ladsweb.modaps.eosdis.nasa.gov"+href.substring(9,91)
       val dir = "/home/css/Desktop/mydir/nasa/"+year+"/"+day+"/"
       val f = new File(dir)
       f.mkdir()
       val gf = new GetFiles()
       gf.getFile(newUrl,dir)
     }
   }
  }

  def parseDate(s: String)(dateTimeFormatter: DateTimeFormatter) = LocalDateTime.parse(s"$s 00:00:00", dateTimeFormatter)


  def estimateTime(date: String) = {
    def df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val Data = parseDate(date)(df)
    val year = Data.getYear
    val month = Data.getMonth
    val day = Data.getDayOfYear
    (year,month,day)
  }
}

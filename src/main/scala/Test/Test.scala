package Test

import java.nio.charset.Charset

import com.csvreader.CsvReader
import myCode.{GetGDELTData, GetLandsatSenceNASA}
import myUtil.{ParseCSV, Unzip}

object Test {

  def main(args: Array[String]): Unit = {

    /*
    test pareCSV
     */
//    val fileUrl = "/home/css/Desktop/365.csv"
//    val p = new ParseCSV
//    p.readCSV(fileUrl)

    /*
    test for unzip
     */
//    val uz = new Unzip
//    val s = "/home/css/Desktop/20150219020000.gkg.csv.zip"
//    val a = "/home/css/Desktop/20150219020000"
//    uz.unzip(s,a)

    /*
    test for parse file from text
     */
    val s = "/home/css/Desktop/masterfilelist.txt"
    val get = new GetGDELTData
    val a = get.getFileMap(s)

    val gl = new GetLandsatSenceNASA
  }
}

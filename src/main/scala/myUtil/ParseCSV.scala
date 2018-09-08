package myUtil

import java.nio.charset.Charset

import com.csvreader.CsvReader

import scala.collection.mutable.ArrayBuffer

class ParseCSV {

  def readCSV(fileUrl:String) ={
    try{
      var list = new ArrayBuffer[String]
      val reader = new CsvReader(fileUrl,',',Charset.forName("UTF-8"))
      reader.readHeaders();
      while(reader.readRecord()){
        list ++= reader.getValues
      }
      list
      reader.close()
    }
  }

}

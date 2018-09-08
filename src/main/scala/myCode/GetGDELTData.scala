package myCode

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/*
parse .txt file and get the map of url
 */

class GetGDELTData {

  def getFileMap(urlFile:String)={
    val fileContext = Source.fromFile(urlFile)
    val it = fileContext.getLines()
    var exportList = new ArrayBuffer[String]
    var mentionList = new ArrayBuffer[String]
    var gkgList = new ArrayBuffer[String]

    while(it.hasNext){
      var context = it.next()
      if(context.contains("export")){
        exportList += context.substring(context.length-66,context.length)
      }
      else if(context.contains("mentions"))
      {
        mentionList += context.substring(context.length-68,context.length)
      }
      else if(context.contains("gkg"))
      {
        gkgList += context.substring(context.length-63,context.length)
      }
    }
    var map : Map[String,ArrayBuffer[String]] = Map()
    print(exportList.length)
    print(mentionList.length)
    print(gkgList.length)
    map += ("export" -> exportList)
    map += ("mentions" -> mentionList)
    map += ("gkg" -> gkgList)
    map
  }


}

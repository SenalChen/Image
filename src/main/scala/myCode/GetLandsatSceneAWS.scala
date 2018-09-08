package myCode

import java.io.{File, InputStream}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager.Limit
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.ConfigFile
import com.typesafe.config.ConfigFactory

import scala.io.Source
import myUtil.MyJsonProtocol._
import myUtil.GetFiles
import spray.json._
import sun.misc.ObjectInputFilter.Config

case class LandsatImage(
     sceneId:String,
     productId:String,
     path:Int,
     row:Int,
     baseURL:String,
     configFile:String,
     limit:Int,
     local:String,
     direction:String


                       )extends {
  def baseS3Path = f"L8/$path%03d/$row%03d/$sceneId"
  def rootUri = s"s3://landsat-pds/$baseS3Path"
  def bandUri(band: Int) = s"s3://landsat-pds.s3.amazonaws.com/$baseS3Path/${sceneId}_B${band}.TIF"
  def googleUrl = s"http://storage.googleapis.com/earthengine-public/landsat/${baseS3Path}.tar.bz"

  def parseJsonFromURL(url:String)  = Source.fromURL(url).mkString.parseJson.asJsObject.fields

  def getLandsatDownloader()

}

//class GetLandsatSceneAWS {
//
//  def getConfig(fileurl:String): Unit ={
//    val config = ConfigFactory.load(fileurl)
//    print(config.getString("akka.local"))
//
//  }
//
//  def parseJsonNew(url: String, limit: Int) = {
//
//    val configFile = "/src/main/resource/App.config"
//    val baseUrl : String = "http://landsat-pds.s3.amazonaws.com/L8/"
//    var json = Source.fromURL(url).mkString.parseJson.asJsObject.fields
//
//    for ((x, y) <- json) {
//      if (x == "results") {
//        val result = arrayFormat.read(y)
//        try{
//        for (s <- result) {
//          val map = s.toJson.asJsObject.getFields("scene_id", "product_id")
//          val scene_id = map(0).toString()
//          val product_id = map(1).toString().substring(1, 41)
//
//          var trajectoryId: StringBuffer = new StringBuffer(scene_id.toString().substring(4, 10))
//          trajectoryId.insert(3, ",")
//          var idArray = trajectoryId.toString.split(",")
//          var path = idArray(0)
//          var row = idArray(1)
//
//          val dir = "/home/css/Desktop/mydir/" + product_id
//          val f = new File(dir)
//          f.mkdir()
//
//          for (i <- 0 to 12) {
//            if (i != 0 && i != 12) {
//              var downloadUrl = s"$baseUrl/${path}%03d/${row}03d/${product_id}/${product_id}_B${i}.TIF"
//              val file = new GetFiles()
//              file.getFile(downloadUrl, dir)
//              println(downloadUrl)
//            }
//            if (i == 0) {
//              var downloadUrl = s"$baseUrl/${path}03d/${row}03d/${product_id}/${product_id}_MTL.txt"
//              println(downloadUrl)
//              val file = new GetFiles()
//              file.getFile(downloadUrl, dir)
//            }
//            if (i == 12) {
//              var downloadUrl = s"$baseUrl/${path}03d/${row}03d/${product_id}/${product_id}_BQA.TIF"
//              println(downloadUrl)
//              val file = new GetFiles()
//              file.getFile(downloadUrl, dir)
//            }
//          }
//        }
//        }catch {
//          case ex: Exception => parseJsonOld(url,limit)
//        }
//      }
//    }
//  }
//
//  def parseJsonOld(url: String, limit: Int) = {
//    val baseUrl = "http://landsat-pds.s3.amazonaws.com/L8/"
//    var json = Source.fromURL(url).mkString.parseJson.asJsObject.fields
//
//    for ((x, y) <- json) {
//      if (x == "results") {
//        val result = arrayFormat.read(y)
//        for (s <- result) {
//          val map = s.toJson.asJsObject.getFields("scene_id", "product_id")
//          val scene_id = map(0).toString().substring(1,22)
//          val product_id = map(1).toString().substring(1, 41)
//
//          var trajectoryId: StringBuffer = new StringBuffer(scene_id.toString().substring(3, 9))
//          trajectoryId.insert(3, ",")
//          var idArray = trajectoryId.toString.split(",")
//          var path = idArray(0)
//          var row = idArray(1)
//
//          val dir = "/home/css/Desktop/mydir/" + scene_id
//          val f = new File(dir)
//          f.mkdir()
//
//          for (i <- 0 to 12) {
//            if (i != 0 && i != 12) {
//              var downloadUrl = s"$baseUrl/${path}%03d/${row}03d/${product_id}/${product_id}_B${i}.TIF"
//              val file = new GetFiles()
//              file.getFile(downloadUrl, dir)
//              println(downloadUrl)
//            }
//            if (i == 0) {
//              var downloadUrl = s"$baseUrl/${path}03d/${row}03d/${product_id}/${product_id}_MTL.txt"
//              println(downloadUrl)
//              val file = new GetFiles()
//              file.getFile(downloadUrl, dir)
//            }
//            if (i == 12) {
//              var downloadUrl = s"$baseUrl/${path}03d/${row}03d/${product_id}/${product_id}_BQA.TIF"
//              println(downloadUrl)
//              val file = new GetFiles()
//              file.getFile(downloadUrl, dir)
//            }
//          }
//        }
//      }
//    }
//  }
//
//  def parseDate(s: String)(dateTimeFormatter: DateTimeFormatter) = LocalDateTime.parse(s"$s 00:00:00", dateTimeFormatter)
//
//  def getSeachUrl(beginDate: String, endDate: String, limit: Int) = {
//
//    val downloadUrl ="https://api.developmentseed.org/satellites/landsat?search=acquisitionDate:"
//    val url = s"$downloadUrl[ $beginDate TO $endDate ]&limit=" + limit
//    url
//  }
//
//}

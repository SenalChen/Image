package myUtil

import java.io.{File, FileNotFoundException, FileOutputStream, IOException}
import java.net.{HttpURLConnection, URL}
import java.text.NumberFormat

import org.apache.http.{Header, HttpEntity}
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.HttpClients

class GetFiles {

  val timeOut : Int = 100000
  var config : RequestConfig = null

  def resetTimeout(timeOut : Int): Unit ={
    config = RequestConfig.custom()
      .setConnectionRequestTimeout(timeOut)
      .setSocketTimeout(timeOut)
      .setConnectTimeout(timeOut).build()
  }

  def setTimeout(): Unit ={
    config = RequestConfig.custom()
      .setConnectionRequestTimeout(timeOut)
      .setSocketTimeout(timeOut)
      .setConnectionRequestTimeout(timeOut).build()
  }

  def getFile(url:String,dir:String): Boolean ={

    val result = true     //完成后返回结果为true
//    val uri = new URL(url)
//    val conn = uri.openConnection().asInstanceOf[HttpURLConnection]   //获取连接
//    conn.setConnectTimeout(100000)     //设置连接时间
//    conn.setReadTimeout(100000)
//    var status = conn.getResponseCode
    val client = HttpClients.createDefault()
    val httpGet = new HttpGet(url)
    httpGet.setConfig(config)

    var response : CloseableHttpResponse = null

    var entity : HttpEntity = null

    try {
      response = client.execute(httpGet);
      entity = response.getEntity();
      var contextType = entity.getContentType;
      var fileLength = entity.getContentLength //获取文件总长度

//      println(fileLength)
      var fileLoading = 0; //获取文件下载量，用于计算文件的百分比
      var numberFormat = NumberFormat.getPercentInstance()
      numberFormat.setMaximumFractionDigits(2) //设置小数点后位数
      val stream = entity.getContent
      val buf = Array.fill[Byte](1024 * 1024 * 3)(0) //建立一个固定填充数组，设置下载缓存大小
      var len = stream.read(buf)
      var fileName = getFileNameByUrl(url, contextType)
      val f = new File(dir, fileName) //找到文件夹并生成文件
      val out = new FileOutputStream(f)
      while (len != -1) {
        out.write(buf, 0, len)
        fileLoading += len
        len = stream.read(buf)
        var e = getPercent[Double, Double](fileLoading, fileLength, _ / _)
        println("Downloading:" + numberFormat.format(e)) //显示百分数并且保留两位小数
      }
      out.flush()
      result
    } catch {
        case ex: FileNotFoundException => {
          false
        }
        case ex: IOException => {
          false
        }
    }
  }

  def getPercent[A,B](a:A,b:A,f:(A,A) => B) = f(a,b)

  def getFileNameByUrl(url:String,contentType:Header): String ={
    var strUrl = url.substring(102)
    var newUrl = ""
    if(contentType.toString.indexOf("html") != -1) {
      newUrl = strUrl.replaceAll("[\\?/:*|<>\"]", "_")+".html"
      newUrl
    }
    else newUrl = strUrl.replaceAll("[\\?/:*|<>\"]", "_")
//    println(newUrl) //print filename
    newUrl
  }

}

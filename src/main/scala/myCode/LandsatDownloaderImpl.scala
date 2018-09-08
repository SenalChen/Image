package myCode

import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager.Limit

class LandsatDownloaderImpl extends LandsatDownloader {
  def GetLandsatSceneAWS(startDate:String,endDate:String,limit: Int) = {
        var getAWS = new GetLandsatSceneAWS
        getAWS.getSeachUrl(startDate,endDate,limit)
  }

  override def GetLandsatSceneGoogle(): Unit = ???

  override def GetLandsatSceneUSGS(startDate:String,endDate:String): Unit = {
    var getNASA = new GetLandsatSenceNASA
//    getNASA.getModis()
  }
}

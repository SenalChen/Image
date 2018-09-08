package myCode

class LandsatDownloader {

  val baseURL:String
  val supplementaryURL:String

  def queryFiles(par:String):Unit={}
  def downloadFiles(par:String):Unit={}

}


class AWSLandsat extends LandsatDownloader {
  val uuuu:Int = 2

}

def GetLandsatSceneAWS(startDate:String,endDate:String,limit: Int);

def GetLandsatSceneGoogle();

def GetLandsatSceneUSGS(startDate:String,endDate:String);
package myCode

import myUtil.GetFiles

object GetLandsatSceneGoogle {

  def main(args: Array[String]): Unit = {
    var url = "https://storage.googleapis.com/gcp-public-data-landsat/index.csv.gz"

    val file = new GetFiles()
    val dir = "/home/css/Desktop/mydir/csv/google"
    file.getFile(url,dir)
  }
}

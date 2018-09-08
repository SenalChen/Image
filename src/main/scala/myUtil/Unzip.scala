package myUtil

import java.io.File

import net.lingala.zip4j.core.ZipFile

class Unzip {

  def unzip(zipFile:String,target:String) ={

    val zfile = new ZipFile(zipFile)
    val file = new File(target)
    if(file.isDirectory()&& !file.exists()){
      file.mkdir()
    }
    zfile.extractAll(target)
  }
}
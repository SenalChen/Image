package myUtil

import java.io.{File, FileInputStream, InputStream, OutputStream}
import java.net.{InetSocketAddress, SocketAddress}

import sun.net.ftp.FtpClient

class GetFtpFiles {

  def getFtp(url:String,localFile:String,ftpFile:String): Unit ={

    var ftp:FtpClient = null
    var os : OutputStream = null
    var fileInputStream : InputStream = null

//    try{
      ftp = FtpClient.create();
      val addr:SocketAddress = new InetSocketAddress(url,80);

      os = ftp.putFileStream(ftpFile)
      var file:File = new File(localFile)

      fileInputStream = new FileInputStream(file)
      val byte = Array.fill[Byte](1024 * 1024 * 3)(0)
      var c : Int = 0
      var len = fileInputStream.read(byte)
      while(len != -1){
        os.write(byte,0,len)
      }
      os.flush()
    }
//  }
}

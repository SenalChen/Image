name := "imageDownload"

version := "0.1"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-json" % "1.3.2",
  "org.apache.commons" % "commons-csv" % "1.5",
  "org.jsoup" % "jsoup" % "1.10.3",
  "org.apache.httpcomponents" % "httpclient" % "4.5.2",
  "com.typesafe.akka" %% "akka-actor" % "2.5.4",
  "com.typesafe.akka" %% "akka-stream" % "2.5.4",
  "com.typesafe.akka" %% "akka-http" % "10.0.10",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10",
  "commons-net" % "commons-net" % "3.6",
  "net.lingala.zip4j" % "zip4j" % "1.3.2",
  "net.sourceforge.javacsv" % "javacsv" % "2.0"
)
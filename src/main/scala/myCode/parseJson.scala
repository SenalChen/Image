package myCode

import spray.json._

import scala.collection.mutable.ArrayBuffer

object parseJson {

  def getJsvalueFromMap(map:Map[String,JsValue]): Unit ={
    val list:ArrayBuffer[LandsatImage] = null
    for((s,js) <- map){
      if(s=="result"){
        val result = arrayFormat.read(js)
        for (sss <- result) {
          val getJsonMap = sss.toJson.asJsObject.getFields("scene_id", "product_id")
        }
      }
    }

  }

  implicit def arrayFormat[Result: JsonFormat : ClassManifest] = new RootJsonFormat[Array[Result]] {
    def write(array: Array[Result]) = JsArray(array.map(_.toJson).toVector)

    def read(value: JsValue) = value match {
      case JsArray(elements) => elements.map(_.convertTo[Result]).toArray[Result]
      case x => deserializationError("Expected Array as JsArray, but got " + x)
    }
  }

  def read(json: JsValue): LandsatImage = {
    val fields = json.asJsObject.fields

    def getString(field: String): String =
      fields.get(field) match {
        case Some(jv) =>
          jv match {
            case JsString(s) => s
            case _ =>
              throw DeserializationException(s"Expected field $field to be a string value.")
          }
        case None =>
          throw DeserializationException(s"Expected field $field in image data.")
      }
  }
}

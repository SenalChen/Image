package myUtil

import spray.json._

case class Result(
                  scene_id: String,
                  product_id:String

                )

object MyJsonProtocol extends DefaultJsonProtocol {

  implicit val colorFormat = jsonFormat2(Result)

}

trait CollectionFormats {

  implicit def arrayFormat[Result: JsonFormat : ClassManifest] = new RootJsonFormat[Array[Result]] {
    def write(array: Array[Result]) = JsArray(array.map(_.toJson).toVector)

    def read(value: JsValue) = value match {
      case JsArray(elements) => elements.map(_.convertTo[Result]).toArray[Result]
      case x => deserializationError("Expected Array as JsArray, but got " + x)
    }
  }

  implicit object StringJsonFormat extends JsonFormat[String] {
    def write(x: String) = {
      require(x ne null)
      JsString(x)
    }
    def read(value: JsValue) = value match {
      case JsString(x) => x
      case x => deserializationError("Expected String as JsString, but got " + x)
    }
  }
}
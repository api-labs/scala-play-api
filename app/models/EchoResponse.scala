package models

import play.api.libs.json.{Json, OFormat}

case class EchoResponse (
  id: Long,
  payload: String,
  timeSent: Long,
  sleepIntv: Int,
  timeRecv: Long
)

object EchoResponse {
  implicit val jsonFormatter: OFormat[EchoResponse] = Json.format[EchoResponse]
}
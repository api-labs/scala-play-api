package controllers

import java.time.{Instant, ZonedDateTime}

import javax.inject._
import models.EchoResponse
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class EchoController @Inject()(
  val controllerComponents: ControllerComponents,
)(implicit ec: ExecutionContext
) extends BaseController {

  def echo(id: Long, payload: String, timeSent: Long, sleepIntv: Int): Action[AnyContent] = Action.async { implicit request =>
    Future {
      if (sleepIntv > 0)
        Thread.sleep(sleepIntv * 1000)

      val response = EchoResponse(id, payload, timeSent, sleepIntv, timeInMillis())
      Results.Ok(Json.toJson(response))
    }
  }

  private def timeInMillis(): Long = Instant.now().toEpochMilli
}

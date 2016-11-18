package controllers

import actors.{ExampleActor, PerRequestHandler}
import akka.actor.ActorSystem
import messages.{ExampleMessage, Message}
import play.api.libs.json.JsValue
import play.api.mvc.{Action, AnyContent}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApiGateway(actorSystem: ActorSystem) extends ActorController(actorSystem = actorSystem) with PerRequestHandler {

  def get(): Action[AnyContent] = Action.async {
    handleGet() map {
      _ => Ok("a response was received")
    } recover {
      case e: Exception => {
        InternalServerError(e.getMessage)
      }
    }
  }

  private def handleGet(): Future[Message] = {
    perRequest(ExampleActor.props, new ExampleMessage)
  }


  def post: Action[JsValue] = Action.async(
    parse.json(maxLength = 1024 * 1024 * 10)
  ) {
    request =>
      handlePost() map {
        _ => Ok("a response was received")
      } recover {
        case e: Exception => {
          InternalServerError(e.getMessage)
        }
      }
  }

  private def handlePost(): Future[Message] = {
    perRequest(ExampleActor.props, new ExampleMessage)
  }

}

package controllers

import akka.actor.ActorSystem
import play.api.mvc.Controller

class ActorController(val actorSystem: ActorSystem) extends Controller {

}

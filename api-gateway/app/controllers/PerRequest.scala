package actors

import actors.PerRequest.WorkerProps
import akka.actor.SupervisorStrategy.Stop
import akka.actor.{Actor, ActorRef, OneForOneStrategy, Props}
import controllers.ActorController
import messages.Message

import scala.concurrent.{Future, Promise}


trait PerRequest extends Actor {

  def worker: ActorRef
  def promise: Promise[Message]
  def message: Message

  worker ! message

  override def receive: Receive = {
    case result: Message =>
      promise.success(result)
      context.stop(self)
    case error: Throwable =>
      promise.failure(error)
      context.stop(self)
  }

  override val supervisorStrategy =
    OneForOneStrategy() {
      case e =>
        promise.failure(e)
        context.stop(self)
        Stop
    }

}

object PerRequest {
  case class WorkerProps(workerProps: Props, message: Message, promise: Promise[Message]) extends PerRequest {
    lazy val worker = context.actorOf(workerProps)
  }
}


trait PerRequestHandler {
  this: ActorController =>
  def perRequest(workerProps: Props, message:Message): Future[Message] = {
    val promise = Promise[Message]
    actorSystem.actorOf(Props(WorkerProps(workerProps, message, promise)))
    promise.future
  }
}
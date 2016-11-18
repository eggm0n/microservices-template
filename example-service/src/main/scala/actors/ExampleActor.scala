package actors

import akka.actor.{Actor, Props}
import akka.actor.Actor.Receive
import services.{ExampleService, ExampleServiceImpl}

import scala.concurrent.Future
import akka.pattern.pipe

class ExampleActor extends Actor {
  self: ExampleActorComponent =>
  import context.dispatcher
  override def receive: Receive = {
    case _ => service.getServiceMessage().pipeTo(sender())
  }
}

object ExampleActor {
  def props: Props = Props(classOf[DefaultExampleActor])
}

trait ExampleActorComponent {
  val service: ExampleService
}

class DefaultExampleActor extends ExampleActor with DefaultExampleActorComponent

trait DefaultExampleActorComponent extends ExampleActorComponent {
  val service:ExampleService = new ExampleServiceImpl
}

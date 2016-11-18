package services

import messages.{ExampleMessage, Message}

import scala.concurrent.{ExecutionContext, Future}

trait ExampleService {
  def getServiceMessage()(implicit executionContext: ExecutionContext): Future[Message]
}

class ExampleServiceImpl extends ExampleService {
  override def getServiceMessage()(implicit executionContext: ExecutionContext): Future[Message] = Future {
    ExampleMessage()
  }
}

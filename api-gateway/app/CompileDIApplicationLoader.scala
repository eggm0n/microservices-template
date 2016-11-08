import play.api._
import play.api.ApplicationLoader.Context
import play.api.routing.Router

class CompileDIApplicationLoader extends ApplicationLoader {
  def load(context: Context) = {
    new Components(context).application
  }
}

class Components(context: Context) extends BuiltInComponentsFromContext(context) {
  lazy val router = Router.empty
}
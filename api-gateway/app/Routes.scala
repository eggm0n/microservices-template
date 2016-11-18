import play.api.BuiltInComponents
import play.api.routing.Router
import play.api.routing.sird._

trait Routes extends BuiltInComponents { self: Components =>

  import Controllers._

  override lazy val router: Router = Router.from {
    case GET(p"/") => apiGateway.get
  }
}

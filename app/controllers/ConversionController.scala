package controllers

import javax.inject.Inject

import models.UnitModel
import models.UnitModel._
import play.api.libs.json.Json
import play.api.mvc._



class ConversionController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import ConversionForm._
  import javax.script.ScriptEngine
  import javax.script.ScriptEngineManager






  def convertUnits = Action { implicit request =>
    form.bindFromRequest.fold(
    formWithErrors => {
      val json = Json.obj(
      "status" -> "KO",
      "msg" -> formWithErrors.errors.map(_.message).mkString(", ")
      )
      render {
          case Accepts.Json() => BadRequest(json)
          case Accepts.JavaScript() => BadRequest(s"${formWithErrors("callback").value.getOrElse("callback")}(${json.toString});").as(JAVASCRIPT)
        }
    },
    query => {

      val replacedFullNameWithNumbers = UnitModel.extractName(query.units, 0)
      val fullReplaceWithNumbers = UnitModel.extractSymbols(replacedFullNameWithNumbers, 0)

      val fullReplaceWithUnit = UnitModel.extractUnit(fullReplaceWithNumbers, 0)

      val manager = new ScriptEngineManager
      val engine = manager.getEngineByName("js")
      val conversion = engine.eval(fullReplaceWithNumbers)

      val json = Json.obj(
        "unit_name" -> fullReplaceWithUnit,
        "multiplication_factor" -> BigDecimal.apply(conversion.toString)
      )

        Ok(json)
      }
    )
  }

}

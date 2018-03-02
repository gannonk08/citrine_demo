package controllers
import play.api.data.Form
import play.api.data.Forms._
import models.UnitModel._

object ConversionForm {

  case class Data(units: String)

  lazy val validSymbol = validOperators ++ validUnits.map(_.name) ++ validUnits.map(_.symbol)

  val form = Form(
    mapping(
      "units" -> nonEmptyText.verifying("error.invalid", v => {
        true
      })
    )(Data.apply)(Data.unapply)
  )
}
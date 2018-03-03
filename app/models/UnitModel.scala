package models

import scala.collection.mutable

case class UnitModel(
  name: String,
  symbol: String,
  types: String,
  multiplicationFactor: BigDecimal,
  unit: String
)
object UnitModel {

  val validOperators = Seq("(",")","/","*","+","-")

  val minute = UnitModel("minute","min", "time", 60,"s")

  val degree = UnitModel("degree","°", "Plane angle", Math.PI / 180, "rad")

  val minAngle = UnitModel("'", "'","Plane angle", Math.PI / 10800, "rad")

  val second = UnitModel("second","\"", "Plane angle", Math.PI / 648000, "rad")

  val hectare = UnitModel("hectare","ha", "area", 10000, "m2")

  val hour = UnitModel("hour","h", "time", 3600,"s")

  val day = UnitModel("day","d", "time", 86400,"s")

  val litre = UnitModel("litre","L", "volume", 0.001, "m3")

  val tonne = UnitModel("tonne","t", "mass", 1000, "kg")

  val validUnits = mutable.Seq(hectare, hour, minute, day, degree, minAngle, second, litre, tonne)

  def extractName(queryString: String, i: Int): String = {
    val unitModel = validUnits(i)
    val strWithName = queryString.replace(s"${unitModel.name}",s"${unitModel.multiplicationFactor}")
    if(validUnits.length-1 != i){
      val newI = i + 1
      extractName(strWithName,newI)
    } else {
      strWithName
    }
  }

  def extractSymbols(queryString: String, i: Int): String = {
    val unitModel = validUnits(i)
    val strWithSymbol = queryString.replace(s"${unitModel.symbol}",s"${unitModel.multiplicationFactor}")
    if(validUnits.length-1 != i){
      val newI = i + 1
      extractSymbols(strWithSymbol,newI)
    } else {
      strWithSymbol
    }
  }

  def extractUnit(queryString: String, i: Int): String = {
    val unitModel = validUnits(i)
    val strWithSymbol = queryString.replace(s"${unitModel.multiplicationFactor}",s"${unitModel.unit}")
    if(validUnits.length-1 != i){
      val newI = i + 1
      extractUnit(strWithSymbol,newI)
    } else {
      strWithSymbol
    }
  }
}

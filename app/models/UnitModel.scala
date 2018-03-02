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

  val minAngle = UnitModel("‘", "‘","Plane angle", Math.PI / 10800, "rad")

  val second = UnitModel("second","“", "Plane angle", Math.PI / 648000, "rad")

  val hectare = UnitModel("hectare","ha", "area", 10000, "m2")

  val hour = UnitModel("hour","h", "time", 3600,"s")

  val day = UnitModel("day","d", "time", 86400,"s")

  val litre = UnitModel("litre","L", "volume", 0.001, "m3")

  val tonne = UnitModel("tonne","t", "mass", 1000, "kg")

  val validUnits = mutable.Seq(minute, hour, day, degree, minAngle, second, hectare, litre, tonne)

  val replMulitplications = Map(
    minute.name -> minute.multiplicationFactor,
    hour.name -> hour.multiplicationFactor,
    day.name -> day.multiplicationFactor,
    degree.name -> degree.multiplicationFactor,
    minAngle.name -> minAngle.multiplicationFactor,
    second.name -> second.multiplicationFactor,
    hectare.name -> hectare.multiplicationFactor,
    litre.name -> litre.multiplicationFactor,
    tonne.name -> tonne.multiplicationFactor,
    minute.symbol -> minute.multiplicationFactor,
    hour.symbol -> hour.multiplicationFactor,
    day.symbol -> day.multiplicationFactor,
    degree.symbol -> degree.multiplicationFactor,
    minAngle.symbol -> minAngle.multiplicationFactor,
    second.symbol -> second.multiplicationFactor,
    hectare.symbol -> hectare.multiplicationFactor,
    litre.symbol -> litre.multiplicationFactor,
    tonne.symbol -> tonne.multiplicationFactor
  )

  val replUnits = Map(
    minute.name -> minute.unit,
    hour.name -> hour.unit,
    day.name -> day.unit,
    degree.name -> degree.unit,
    minAngle.name -> minAngle.unit,
    second.name -> second.unit,
    hectare.name -> hectare.unit,
    litre.name -> litre.unit,
    tonne.name -> tonne.unit,
    minute.symbol -> minute.unit,
    hour.symbol -> hour.unit,
    day.symbol -> day.unit,
    degree.symbol -> degree.unit,
    minAngle.symbol -> minAngle.unit,
    second.symbol -> second.unit,
    hectare.symbol -> hectare.unit,
    litre.symbol -> litre.unit,
    tonne.symbol -> tonne.unit
  )
//  val regex = validUnits.map(c => s"${c.name}|${c.symbol}").mkString("|").r
//  regex.replaceAllIn("123 Main bar", "x")

//  degree/minute

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

//  def extractUnit(queryString: String, i: Int): String = {
//    val unitModel = validUnits(i)
//    val strWithName = queryString.replace(s"${unitModel.name}",s"${unitModel.unit}")
//    if(validUnits.length-1 != i){
//      val newI = i + 1
//      extractUnit(strWithName,newI)
//    } else {
//      strWithName
//    }
//  }

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

package main.scala.weekly_runners

import main.scala.models.House

object HouseParser {
  def houseParser(line: String) = {

    val dataLine = line.split(",")

    House(
      dataLine.head,
      dataLine.drop(1).head,
      dataLine.drop(2).head.toDouble,
      dataLine.drop(3).head.toDouble,
      dataLine.drop(4).head.toDouble,
      dataLine.drop(5).head.toDouble,
      dataLine.drop(6).head.toInt,
      dataLine.drop(7).head,
      dataLine.drop(8).head.toInt,
      dataLine.drop(9).head.toInt,
      dataLine.drop(10).head.toInt,
      dataLine.drop(11).head.toInt,
      dataLine.drop(12).head.toInt,
      dataLine.drop(13).head.toInt,
      dataLine.drop(14).head.toInt,
      dataLine.drop(15).head.toInt,
      dataLine.drop(16).head,
      dataLine.drop(17).head.toDouble,
      dataLine.drop(18).head.toDouble,
      dataLine.drop(19).head.toDouble,
      dataLine.drop(20).head.toDouble
    )
  }
}

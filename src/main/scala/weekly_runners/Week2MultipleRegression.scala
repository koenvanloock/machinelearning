package main.scala.weekly_runners

import main.scala.helpers.CsvParser
import main.scala.ml_functions.MultipleRegression
import main.scala.models.House

object Week2MultipleRegression {

  def ones(length: Int): Vector[Int] = (0 until length).map(_ => 1).toVector

  def main(args: Array[String]) {


    val fileName = "main/resources/kc_house_train_data.csv"
    val testFile = "main/resources/kc_house_test_data.csv"
    val trainHouses: List[House] = CsvParser.parse(fileName, HouseParser.houseParser)
    val testHouses: List[House] = CsvParser.parse(testFile, HouseParser.houseParser)
    val input = trainHouses.map(x => Vector(x.sqft_living, 1.0)).toVector
    val output = trainHouses.map(_.price).toVector

    val input2 = trainHouses.toVector.map(x => Vector(x.sqft_living, x.sqft_living15, 1.0))

    //val param1 = MultipleRegression.calculateGradientDescent(Vector(input), output, 2.5e7, 7e-12, Vector(80, 1.0))
    val params = MultipleRegression.calculateGradientDescent(input2, output, 1e9, 4e-12, Vector(263, 276.0, 6000.0))
    println("params: " + params.mkString(","))
  }
}

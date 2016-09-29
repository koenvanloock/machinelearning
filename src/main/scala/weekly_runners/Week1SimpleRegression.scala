package weekly_runners

import helpers.CsvParser
import ml_functions.SimpleRegression
import week1models.House


object Week1SimpleRegression {




  def main(args: Array[String]) {

    val fileName = "kc_house_train_data.csv"
    val testFile = "kc_house_test_data.csv"
    val trainHouses: List[House] = CsvParser.parse(fileName, houseParser)
    val testHouses: List[House] =  CsvParser.parse(testFile, houseParser)
    val input = trainHouses.map(_.sqft_living)
    val output = trainHouses.map(_.price)

    val slopeIntercept = SimpleRegression.simpleLinReg(input, output)
    println(s"The calculated slope is ${slopeIntercept.slope} and the calculated intercept is ${slopeIntercept.intercept}.")
    val result = SimpleRegression.predictResult(slopeIntercept.slope, slopeIntercept.intercept)(2650)
    println(s"The price of a house with 2650 sqft is predicted to be $result $$")

    val result2 = SimpleRegression.calculateRss(input, output, slopeIntercept.slope, slopeIntercept.intercept)
    println(s"the RSS of this linear regression is $result2")

    val result3 = SimpleRegression.predictOriginOfResult(slopeIntercept.slope, slopeIntercept.intercept)(800000)
    println(s"The sqft of a house which costs 800 000 $$ is $result3 sqft.")

    val newInput = trainHouses.map(_.bedrooms)
    val secondSlopeIntercept = SimpleRegression.simpleLinReg(newInput, output)

    val testInputSqft = testHouses.map(_.sqft_living)
    val testInputBedrooms = testHouses.map(_.bedrooms)
    val testOutput = testHouses.map(_.price)
    val firstRss = SimpleRegression.calculateRss(testInputSqft, testOutput, slopeIntercept.slope, slopeIntercept.intercept)
    val secondRss = SimpleRegression.calculateRss(testInputBedrooms, testOutput, secondSlopeIntercept.slope, secondSlopeIntercept.intercept)
    println(s"the RSS of the first model is $firstRss")
    println(s"the RSS of the second model is $secondRss")
    val best = if(firstRss > secondRss) "the second" else "the first"
    println(s"The best model is $best.")
  }

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

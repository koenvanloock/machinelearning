package main.scala.weekly_runners

import main.scala.helpers.CsvParser
import main.scala.ml_functions.SimpleRegression
import main.scala.models.House


object Week1SimpleRegression {
  def main(args: Array[String]) {

    val fileName = "main/resources/kc_house_train_data.csv"
    val testFile = "main/resources/kc_house_test_data.csv"
    val trainHouses: List[House] = CsvParser.parse(fileName, HouseParser.houseParser)
    val testHouses: List[House] =  CsvParser.parse(testFile, HouseParser.houseParser)
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


}

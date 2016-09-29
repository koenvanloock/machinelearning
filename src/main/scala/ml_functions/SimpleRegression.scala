package ml_functions

import week1models.SlopeAndIntercept

object SimpleRegression {


  def sumAllignedProducts(column: List[Double], output: List[Double], currentSum: Double = 0): Double = {
    if(column.nonEmpty){
      val headProd = column.head * output.head
      sumAllignedProducts(column.tail, output.tail, currentSum + headProd)
    }else{
      currentSum
    }
  }


  def simpleLinReg(column: List[Double], output: List[Double]): SlopeAndIntercept ={
    val ySum = output.sum
    val xSum = column.sum
    val prodSum = sumAllignedProducts(column, output)
    val xSquared = column.map(x => x*x).sum
    val numberOfElements = column.length

    val slope = (prodSum - (xSum * ySum) / numberOfElements) / (xSquared - xSum * xSum / numberOfElements)
    val intercept = ySum / numberOfElements - slope * xSum / numberOfElements

    SlopeAndIntercept(slope, intercept)
  }

  def getPredictedOutput(column: List[Double], slope: Double, intercept: Double): List[Double] = column.map(predictResult(slope, intercept))

  def predictResult(slope: Double, intercept: Double)(x: Double): Double = slope * x + intercept

  def predictOriginOfResult(slope: Double, intercept: Double)(y: Double): Double = (y - intercept) / slope


  def calculateRss(column: List[Double], output: List[Double], slope: Double, intercept: Double, currentRss: Double=0): Double ={
    if(column.nonEmpty) {
      val dataRss = (output.head - predictResult(slope, intercept)(column.head)) * (output.head - predictResult(slope, intercept)(column.head))
      calculateRss(column.tail, output.tail, slope, intercept, currentRss + dataRss)
    }else{
      currentRss
    }
  }

}

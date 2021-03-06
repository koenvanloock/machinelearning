package ml_functions

import models.WeightAndPartial

object MultipleRegression {


  def calculateGradientDescent(input: Vector[Vector[Double]], results: Vector[Double], tolerance: Double, stepsize:Double, magnitude: Double = Double.MaxValue, currentWeights: Vector[Double], iteration: Int=0): Vector[Double] = {
    if (magnitude < tolerance) {
      currentWeights
    } else {
      val resultWeightAndPartials = input.transpose
           .zip(currentWeights)
           .map{ case(featureRow, currentWeight) => updateSingleFeature(results,featureRow, currentWeight,stepsize)}
           val newWeights = resultWeightAndPartials.map(_.weight)
          val newMagnitude = Math.sqrt(resultWeightAndPartials.map(x => x.partial * x.partial).sum)
      println(s"iteration $iteration magnitude at: $newMagnitude weights: (${newWeights.mkString(",")})")
      calculateGradientDescent(input, results, tolerance, stepsize,newMagnitude, newWeights, iteration+1)
    }
  }


  def predictOutcome(matrix: Vector[Vector[Double]], weights: Vector[Double]): Vector[Double] = {
     matrix.map( row => row.zip(weights)
                           .map{ case( matrixElem, weightElem) => matrixElem * weightElem}
                           .sum
     )
  }

  def featureDerivative(errors: Vector[Double], feature: Vector[Double]): Double = {
    2 * feature.zip(errors)
               .map{ case(error,featureVal) => error * featureVal}
               .sum
  }

  def updateSingleFeature(results: Vector[Double], featureValues: Vector[Double], currentWeight: Double, stepsize: Double): WeightAndPartial = {
    val partial = -2 * featureValues
                .zip(results)
                .map{ case(featureValue, result) => featureValue *(result - (featureValue * currentWeight))}
                .sum
    val newWeight = currentWeight - stepsize * partial
    WeightAndPartial(newWeight, partial)
  }

  def innerProduct(vectorA: Vector[Double], vectorB: Vector[Double]): Double =
    vectorA
    .zip(vectorB)
    .map{case (a,b) => a * b}
    .sum
}

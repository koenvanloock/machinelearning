package ml_functions

object MultipleRegression {



  def regressionGradientDescent(featureMatrix: Vector[Vector[Double]], output: Vector[Double], currentWeights: Vector[Double], stepSize: Double, tolerance: Double, magnitude: Double= Double.MaxValue): Vector[Double] ={
    if(magnitude < tolerance){
      currentWeights
    } else{

      val outcomes = predictOutcome(featureMatrix, currentWeights)
      val errors = outcomes.zip(output).map{ case( outcomeVal, outputVal) => outputVal-outcomeVal}
      // calculate new weights and magnitude
      val weightsAndRss = currentWeights.map{ weight =>
        featureDerivative(errors,feature)
      }
      val newMagnitude = Math.sqrt(rss)

      regressionGradientDescent(featureMatrix, output, newWeights, stepSize, tolerance, newMagnitude)
    }

  }


  def predictOutcome(matrix: Vector[Vector[Double]], weights: Vector[Double]): Vector[Double] = {
     matrix.map( row => row.zip(weights)
                           .map{ case( matrixElem, weightElem) => matrixElem * weightElem}
                           .sum
     )
  }

  def featureDerivative(errors: Vector[Double], feature: Vector[Double]) = {
    2 * feature.zip(errors)
               .map{ case(error,featureVal) => error * featureVal}
               .sum
  }
}

package models

case class House(
                  id: String,
                  date: String,
                  price: Double,
                  bedrooms: Double,
                  bathrooms: Double,
                  sqft_living: Double,
                  sqft_lot: Int,
                  floors: String,
                  waterfront: Int,
                  view: Int,
                  condition: Int,
                  grade: Int,
                  sqft_above: Int,
                  sqft_basement: Int,
                  yr_built: Int,
                  yr_renovated: Int,
                  zipcode: String,
                  lat: Double,
                  long: Double,
                  sqft_living15: Double,
                  sqft_lot15: Double
                )



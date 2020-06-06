
import org.apache.spark.sql.SparkSession
//
// Create Datasets of primitive type and tuple type ands show simple operations.
//
object Basic {
  def main(args: Array[String]) {
    val spark =
      SparkSession
        .builder()
        .master("local[2]")
        .appName("Dataset-Basic")
        .getOrCreate()
    import spark.implicits._
    // Create a Dataset of integers
    val s = Seq(2, 7, 15, 24, 45, 85)
    val ds = s.toDS()
    println("*** only one column, and it always has the same name")
    ds.columns.foreach(println(_))
    println("*** column types")
    ds.dtypes.foreach(println(_))
    println("*** schema as if it was a DataFrame")
    ds.printSchema()
    println("*** values > 12")
    ds.where($"value" > 12).show()
    val s2 = Seq.range(1, 100)
    println("*** size of the range")
    println(s2.size)
    val tuples = Seq((1, "one", "un"), (2, "two", "deux"), (3, "three", "trois"))
    val tupleDS = tuples.toDS()
    println("*** Tuple Dataset types")
    tupleDS.dtypes.foreach(println(_))
    println("*** filter by one column and fetch another")
    tupleDS.where($"_1" > 2).select($"_2", $"_3").show()
  }
}
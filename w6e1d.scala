
// $example on:init_session$
import org.apache.spark.sql.SparkSession
// $example off:init_session$
// One method for defining the schema of an RDD is to make a case class with the desired column
// names and types.
case class Record(key: Int, value: String)
object RDDRelation {
  def main(args: Array[String]): Unit = {
    // $example on:init_session$
    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("Spark Examples")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    // Importing the SparkSession gives access to all the SQL functions and implicit conversions.
    import spark.implicits._
    // $example off:init_session$
    val df = spark.createDataFrame((1 to 100).map(i => Record(i, s"val_$i")))
    // Any RDD containing case classes can be used to create a temporary view.  The schema of the
    // view is automatically inferred using scala reflection.
    df.createOrReplaceTempView("records")
    println("Result of SELECT *:")
    spark.sql("SELECT * FROM records").collect().foreach(println)
    val count = spark.sql("SELECT COUNT(*) FROM records").collect().head.getLong(0)
    println(s"COUNT(*): $count")
    val rddFromSql = spark.sql("SELECT key, value FROM records WHERE key < 10")
    println("Result of RDD.map:")
    rddFromSql.rdd.map(row => s"Key: ${row(0)}, Value: ${row(1)}").collect().foreach(println)
    df.where($"key" === 1).orderBy($"value".asc).select($"key").collect().foreach(println)
    spark.stop()
  }
}
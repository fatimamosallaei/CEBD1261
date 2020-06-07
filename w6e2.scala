


import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.log4j._
 import org.apache.spark.util.SizeEstimator






    
object w6e2 {
  
  case class Person(age:Int,	sex:String,	bmi:Double,	children:String,	smoker:String,	region:String,	charges:Double)

     

  
  
  def mapper(line:String): Person = {
    val fields = line.split(',')  
    
    val customer:Person = Person(fields(0).toInt, fields(1), fields(2).toDouble, fields(3), fields(4), fields(5), fields(6).toDouble)
    return customer
  }
  

  def main(args: Array[String]) {
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("w6e2")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "file:///C:/temp") // Necessary to work around a Windows bug in Spark 2.0.0; omit if you're not on Windows.
      .getOrCreate()
    
    // Convert our csv file to a DataSet, using our Person case
    // class to infer the schema.
    import spark.implicits._
    
   // 1. Read insurance.csv file 
    val lines = spark.sparkContext.textFile("C://SparkScala//insurance.csv")
    val head = lines.first()
    
    
    println(" the amount of memory that should be allocated is : "+SizeEstimator.estimate(lines))

    val data = lines.filter(x => x != head) // removes the header
    
    
    val customer = data.map(mapper).toDS().cache()
   
     
    
    //2. Print the size
    println("The datset has  "+customer.count+" rows")
    

    

   // 3. Print sex and count of sex (use group by in sql)
 
 
    customer.groupBy("sex").count().show()
    
    //4. Filter smoker=yes and print again the sex,count of sex
 
    println("Filter out  smokers:")
    customer.filter(customer("smoker").contains("yes")).groupBy("sex").count().show()
    
    
    
    //5. Group by region and sum the charges (in each region), then print rows bydescending order (with respect to sum)

    customer.groupBy("region").sum("charges").sort( $"sum(charges)".desc).show()
    
    
 
    
    spark.stop()
  }
}
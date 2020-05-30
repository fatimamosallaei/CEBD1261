object w5ex1a {
  
   def frame (x:String, f : String => String) : String ={
   f(x)
   
 }                                                //> frame: (x: String, f: String => String)String
 def greet1 (x:String) : String ={
   
   
   var greet = "Hello "+x+", How are you doing?"
   return greet
   
 }                                                //> greet1: (x: String)String
 
 def greet2 (x:String) : String ={
   
   
   var greet = "Hello "+x+", How U Doing?"
   return greet
   
 }                                                //> greet2: (x: String)String
  println(frame("Fatima",greet1))                 //> Hello Fatima, How are you doing?
  println(frame("Fatima",greet2))                 //> Hello Fatima, How U Doing?
}





object w5ex1b {

  var x = List.range(1,10)                        //> x  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val x_odd = x.filter( (x: Int) => x%2 != 0)     //> x_odd  : List[Int] = List(1, 3, 5, 7, 9)
  
  
  def factorial (n: Int): Int ={
   if(n==0) return 1
   else  return n*factorial(n-1)
  }                                               //> factorial: (n: Int)Int
 
 val mapedx_odd = x_odd.map( (x_od: Int) => {factorial(x_od)})
                                                  //> mapedx_odd  : List[Int] = List(1, 6, 120, 5040, 362880)
  
}




object week5ex1b {
  var nums = List.range(1,10)                     //> nums  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  val oddNums = nums.filter( (x: Int) => x%2 != 0)//> oddNums  : List[Int] = List(1, 3, 5, 7, 9)
  
  
  def fac (n: Int): Int ={
   if(n==0) return 1
   else  return n*fac(n-1)
  }                                               //> fac: (n: Int)Int
  fac(5)                                          //> res0: Int = 120
 
 val mapedlist = oddNums.map( (oddNum: Int) => {fac(oddNum)})
                                                  //> mapedlist  : List[Int] = List(1, 6, 120, 5040, 362880)
  
}




object w5e2 {
  //list from 1 to 45
var n = List.range(1,46)                          //> n  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 
                                                  //| 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 3
                                                  //| 6, 37, 38, 39, 40, 41, 42, 43, 44, 45)
//generate the Sum of the numbers divisible by 4
var D_B_4 = n.filter( (x: Int) => x%4 == 0)       //> D_B_4  : List[Int] = List(4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44)
var sum = D_B_4.reduce( (x1: Int, x2: Int) => x1 +x2)
                                                  //> sum  : Int = 264

//Sum of the squares of the numbers divisible by 3 and less than 20
var D_B_3_l_20 = n.filter( (x: Int) => (x%3 == 0)&&(x<20))
                                                  //> D_B_3_l_20  : List[Int] = List(3, 6, 9, 12, 15, 18)
var s_square = 0                                  //> s_square  : Int = 0

 for (m<-D_B_3_l_20 ){s_square = s_square+m*m}
 
 println(s_square)                                //> 819
}




object w5e3 {

  
 
 def get_max (x1:Double, x2:Double, f : (Double, Double) => Double) : Double ={
   f(x1,x2)
   
 }                                                //> get_max: (x1: Double, x2: Double, f: (Double, Double) => Double)Double
 def maximum (x1:Double, x2:Double) :Double ={
   
   if(x1>x2)
       return x1
   else
       return x2
   
 }                                                //> maximum: (x1: Double, x2: Double)Double
 
  println(get_max (505,10,maximum))               //> 505.0
  

}




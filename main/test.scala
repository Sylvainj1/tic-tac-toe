import Array._
import util.control.Breaks._
import scala.util.Random


object MyProjectMain {
  def main(args: Array[String]): Unit = {
   
    for(i <- 0 to 10){
      val A = List(1, 2, 3)
      println(A(Random.nextInt(A.size)))
    }
    
  }

}
 
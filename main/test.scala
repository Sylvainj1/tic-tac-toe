import Array._
import util.control.Breaks._

object MyProjectMain {
  def main(args: Array[String]): Unit = {
   
    println("Joueur X : entrer la ligne")

    try{
      val x=scala.io.StdIn.readInt()
      if(x.isInstanceOf[Int]==true){
        println(x)
      }else{
        println("error 1")
      }
    }catch{
      case e: Throwable => print("error 2")
    }
         
  }

}
 
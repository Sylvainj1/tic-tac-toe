import Array._
import scala.util.control._
import util.control.Breaks._
import scala.util.Random

object  myTable{
    var table= ofDim[String](3,3)

    def initTable():Unit={
        for (i <- 0 to 2) {
            for ( j <- 0 to 2) {
                table(i)(j) = " ";
            }
        }
    }
      
      def printTable():Unit={
         print("-------")
         print("\n")
         print("|" + table(0)(0) + "|" + table(0)(1) + "|" + table(0)(2) + "|")
         print("\n")
         print("-------")
         print("\n")
         print("|" + table(1)(0) + "|" + table(1)(1) + "|" + table(1)(2) + "|")
         print("\n") 
        print("-------")
         print("\n")
         print("|" + table(2)(0) + "|" + table(2)(1) + "|" + table(2)(2) + "|")
         print("\n")
         print("-------")
         print("\n")

         /* for (i <- 0 to 2) {
            for ( j <- 0 to 2) {
               print(" " + table(i)(j));
            }
         println();
         } */
      }
      
      def xPlayer1():Int={
         println("Joueur X : entrer la ligne")
        try{
            return scala.io.StdIn.readInt()
         }catch{
            case e: Throwable => xPlayer1()
         }
      }

      def yPlayer1():Int={
         println("Joueur X : entrer la colonne")
         try{
            return scala.io.StdIn.readInt()
         }catch{
            case e: Throwable => yPlayer1()
         }
         
      }
      
      def xPlayer2():Int={
         println("Joueur O : entrer la ligne")
         try {
            return scala.io.StdIn.readInt()
         }catch{
            case e: Throwable => xPlayer2()
         }
      }

      def yPlayer2():Int={
         println("Joueur O : entrer la colonne")
         try{
            return scala.io.StdIn.readInt()
         }catch{
            case e:Throwable => yPlayer2()
         }
      }

      def addCross():Unit={
         val x=xPlayer1()
         val y=yPlayer1()

         if(table(x)(y)==" "){
            table(x)(y)="X"
         }else{
            println("Case déjà prise, recommencez")
            printTable()
            println("\n")
            addCross()
         }
      }

      def addCircle():Unit={
         val x=xPlayer2()
         val y=xPlayer2()

         if(table(x)(y)==" "){
            table(x)(y)="O"
         }else{
            println("Case déjà prise, recommencez")
            printTable()
            println("\n")
            addCircle()
        }
      }

      def iaAddRandomCircle():Unit={
        val A = List(1, 2, 3)
        val x=A(Random.nextInt(A.size))
        val y=A(Random.nextInt(A.size))

        if(table(x)(y)==" "){
            table(x)(y)="O"
        }else{
            addFirstCircle()
        }
      }



      def iaAddCircle(i:Int):Unit={
        if(i==1){
            iaAddRandomCircle()
        }else{
            iaAddRandomCircle()
        }
      }

      def winConditions(i:Int):Unit={
         if(i>3){
            if(table(0)(0)==table(0)(1) && table(0)(0)==table(0)(2) && table(0)(1)==table(0)(2)){
               println("joueur "+ table(0)(0) +" a gagné")
               println("cas 1")
               break
            }
            else if(table(1)(0)==table(1)(1) && table(1)(0)==table(1)(2) && table(1)(1)==table(1)(2)){
               println("joueur "+table(1)(0)+" a gagné")
               println("cas 2")
               break
            }
            else if(table(2)(0)==table(2)(1) && table(2)(0)==table(2)(2) && table(2)(1)==table(2)(2)){
               println("joueur "+table(2)(0)+" a gagné")
               println("cas 3")
               break
            }
            else if(table(0)(0)==table(1)(0) && table(0)(0)==table(2)(0) && table(1)(0)==table(2)(0)){
               println("joueur "+table(0)(0)+" a gagné")
               println("cas 4")
               break
            }
            else if(table(0)(1)==table(1)(1) && table(0)(1)==table(2)(1) && table(1)(1)==table(2)(1)){
               println("joueur "+table(0)(1)+" a gagné")
               println("cas 5")
               break
            }
            else if(table(0)(2)==table(1)(2) && table(0)(2)==table(2)(2) && table(1)(2)==table(2)(2)){
               println("joueur "+table(0)(2)+" a gagné")
               println("cas 6")
               break
            }
            else if(table(0)(0)==table(1)(1) && table(0)(0)==table(2)(2) && table(1)(1)==table(2)(2)){
               println("joueur "+table(0)(0)+" a gagné")
               println("cas 7")
               break
            }
            else if(table(0)(2)==table(1)(1) && table(0)(2)==table(2)(0) && table(1)(1)==table(2)(0)){
               println("joueur "+table(0)(0)+" a gagné")
               println("cas 8")
               break
            }
         }
      }

      
   }


object  tictactoe{
   def main(args: Array[String]):Unit= {
      import myTable._

      initTable()
      printTable()

      breakable{
         for(i <- 0 to 8){
            if(i%2==0){
               addCross()
            }else{
               iaAddCircle(i)
            }
         
         printTable()
         println("\n")
         
         winConditions(i)

         if(i==8){
            println("Fin du jeu")
         }
      }
   }
}
}
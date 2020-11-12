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
    }
      

    def xPlayer():Int={
        println("Joueur X : entrer la ligne")
        try{
            return scala.io.StdIn.readInt()
        }catch{
            case e: Throwable => xPlayer()
        }
    }

    def yPlayer():Int={
        println("Joueur X : entrer la colonne")
        try{
            return scala.io.StdIn.readInt()
        }catch{
            case e: Throwable => yPlayer()
        }
    }

    def addCross(x:Int,y:Int):Unit={
        if(table(x)(y)==" "){
            table(x)(y)="X"
        }else{
            println("Case déjà prise, recommencez")
            printTable()
            println("\n")
            addCross(x,y)
        }
    }


    def addCircleByIa(x:Int,y:Int):Unit={
        if(table(x)(y)==" "){
            table(x)(y)="O"
        }else{
            addCircleByIa(x,y)
        }
    }

    def iaAddRandomCircle():Unit={
        val A = List(0,1, 2)
        val x = A(Random.nextInt(A.size))
        val y = A(Random.nextInt(A.size))

        if(table(x)(y)==" "){
            table(x)(y)="O"
        }else{
            iaAddRandomCircle()
        }
    }

    def checkWin(joueur:String):(Int,Int)={
        var value=(0,0)
        
        //colonne
        for(i<-0 to 2){
            if(table(0)(i)==joueur && table(1)(i)==joueur && table(2)(i)==" "){
                value=(2,i)
            }
            else if(table(0)(i)==joueur && table(2)(i)==joueur && table(1)(i)==" "){
                value=(1,i)
            }
            else if(table(1)(i)==joueur && table(2)(i)==joueur && table(0)(i)==" "){
                value=(0,i)
            }
        }
        
        //ligne
        for(i<-0 to 2){
            if(table(i)(0)==joueur && table(i)(1)==joueur && table(i)(2)==" "){
                value=(i,2)
            }
            else if(table(i)(0)==joueur && table(i)(2)==joueur && table(i)(1)==" "){
                value=(i,1)
            }
            else if(table(i)(1)==joueur && table(i)(2)==joueur && table(i)(0)==" "){
                value=(i,0)
            }
        }

        //diagonal
        if(table(0)(0)==joueur && table(1)(1)==joueur && table(2)(2)==" "){
            value=(2,2)
        }
        else if(table(0)(0)==joueur && table(2)(2)==joueur && table(1)(1)==" "){
            value=(1,1)
        }
        else if(table(1)(1)==joueur && table(2)(2)==joueur && table(0)(0)==" "){
            value=(0,0)
        }

        if(table(0)(2)==joueur && table(1)(1)==joueur && table(2)(0)==" "){
            value=(2,0)
        }
        else if(table(0)(2)==joueur && table(2)(0)==joueur && table(1)(1)==" "){
            value=(1,1)
        }
        else if(table(1)(1)==joueur && table(2)(0)==joueur && table(0)(2)==" "){
            value=(2,0)
        }

        return value

    }

    def iaCheck():Unit={
        if(table(checkWin("O")._1)(checkWin("O")._2)==" "){
            addCircleByIa(checkWin("O")._1,checkWin("O")._2)
        }
        else if(table(checkWin("X")._1)(checkWin("X")._2)==" "){
            addCircleByIa(checkWin("X")._1,checkWin("X")._2)
        }else{
            iaAddRandomCircle()
        }
    }

    def iaAddCircle(i:Int):Unit={
        if(i==1){
            iaAddRandomCircle()
        }else{
            iaCheck()
        }
    }

    def winConditions(e:Int):Unit={
        if(e>3){
            //row
            for(i <- 0 to 2){
               if(table(i)(0)==table(i)(1) && table(i)(0)==table(i)(2) && table(i)(1)==table(i)(2)){
               println("joueur "+ table(i)(0) +" a gagné")
               break
               }
            }

            //colonne
            for(i<- 0 to 2){
               if(table(0)(i)==table(1)(i) && table(0)(i)==table(2)(i) && table(1)(i)==table(2)(i)){
                  println("joueur "+table(0)(i)+" a gagné")
                  break
               }
            }
            
            //diagonale
            if(table(0)(0)==table(1)(1) && table(0)(0)==table(2)(2) && table(1)(1)==table(2)(2)){
               println("joueur "+table(0)(0)+" a gagné")
               break
            }
            else if(table(0)(2)==table(1)(1) && table(0)(2)==table(2)(0) && table(1)(1)==table(2)(0)){
               println("joueur "+table(0)(0)+" a gagné")
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
                    addCross(xPlayer(),yPlayer())
                }else{
                    println("Au tour du joueur O")
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
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

    def checkColumn(joueur:String):(Int,Int)={
        var value=(0,0)

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
        return value
    }

    def checkRow(joueur:String):(Int,Int)={
        var value=(0,0)

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
        return value
    }

    def checkDiagonale(joueur:String):(Int,Int)={
        var value = (0,0)
        //diago 1
        if(table(0)(0)==joueur && table(1)(1)==joueur && table(2)(2)==" "){
            value=(2,2)
        }
        else if(table(0)(0)==joueur && table(2)(2)==joueur && table(0)(0)==" "){
            value=(1,1)
        }
        else if(table(1)(1)==joueur && table(2)(2)==joueur && table(1)(1)==" "){
            value=(0,0)
        }

        //diago 2
        if(table(0)(2)==joueur && table(1)(1)==joueur && table(2)(0)==" "){
            value=(2,0)
        }
        else if(table(0)(2)==joueur && table(2)(0)==joueur && table(1)(1)==" "){
            value=(1,1)
        }
        else if(table(1)(1)==joueur && table(2)(2)==joueur && table(2)(0)==" "){
            value=(2,0)
        }

        return value
    }

    def iaCheck():Unit={
        var blockWinConditions:(Int,Int)=(0,0)
        if(table(checkColumn("X")._1)(checkColumn("X")._2)==" "){
            blockWinConditions=checkColumn("X")
        }
        else if(table(checkRow("X")._1)(checkRow("X")._2)==" "){
            blockWinConditions=checkRow("X")
        }
        else if(table(checkDiagonale("X")._1)(checkDiagonale("X")._2)==" "){
            blockWinConditions=checkDiagonale("X")
        }
        

        if(table(blockWinConditions._1)(blockWinConditions._2)==" "){
            addCircleByIa(blockWinConditions._1,blockWinConditions._2)
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
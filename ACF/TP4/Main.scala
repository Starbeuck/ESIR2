package tp4

// A compléter

object Main {
  def main(args: Array[String]): Unit = {
    val exp = BinExpression("+", VariableRef("y"), BinExpression("-", IntegerValue(1), IntegerValue(2)))
    val prog = Seq(Assignement("x", IntegerValue(0)),
      Seq(Assignement("y", IntegerValue(1)),
        Seq(Read("z"),
          Seq(While(BinExpression("<", VariableRef("x"), VariableRef("z")),
            Seq(Assignement("x", BinExpression("+", VariableRef("x"), IntegerValue(1))),
              Seq(Assignement("y", BinExpression("*", VariableRef("y"), VariableRef("x"))),
                Print(VariableRef("x"))))),
            Print(VariableRef("y"))))))
            
            println("{ \n" + PrettyPrinter.stringOf(prog) + "}")
  }
  
  object PrettyPrinter {
    def stringOf(e: Expression): String = {
      e match {
        case IntegerValue(i)           => i.toString
        case VariableRef(v)            => v
        case BinExpression(op, e1, e2) => "(" + stringOf(e1) + " " + op + " " + stringOf(e2) + ")"
      }
    }
    
    def stringOf(p: Statement): String = {
      p match {
        case Assignement(str, e) => str + ":= " + stringOf(e) + "\n"
        case Print(e)            => "print (" +stringOf(e) +") \n"
        case While(e, s)         => "while (" + stringOf(e) + ") do \n { \n" + stringOf(s) +"} \n"
        case Seq(s1, s2)         => stringOf(s1) + " " + stringOf(s2)
        case If(e, s1, s2)       => stringOf(e) + " " + stringOf(s1) + " " + stringOf(s2) + "\n"
        case Read(str)           => "read("+str+")" + "\n"
      }
    }

  }
  object Interpret {
    def eval(p:Statement, inList:List[Int],blop:Map[String, Int],listesortie:List[Int]):(List[Int],Map[String,Int],List[Int])= {

      p match{
        case Assignement(str, e) => { (inList, blop + (str-> 42), listesortie)} 
        case Read(a)=> (inList.tail,blop, (inList.head)::listesortie)
        case Print (i) => (inList, -(blop(PrettyPrinter.stringOf(i))),blop(PrettyPrinter.stringOf(i))::listesortie)
        case Seq(s1, s2)=> {eval(s1, inList, blop,listesortie)
          eval(s2, inList, blop, listesortie)
        }
        
      }
    }
  }
}


object Permutation extends App{

  def permutation(str:List[String], answer:List[String], list:List[List[String]], n: Int): List[List[String]] = {
    if(str.isEmpty){
      answer :: list
    }else{
      str.zipWithIndex.flatMap{
        case (ch,i) =>
          val left = str.slice(0,i)
          val right = str.slice(i+1, str.length)
          val rest = left ::: right
          if(n !=0)
          permutation(rest, ch+""::answer, list, n-1)
          else
            list
      }
    }
  }

  def flatMapSublists[A,B](ls: List[A])(f: (List[A]) => List[B]): List[B] =
    ls match {
      case Nil => Nil
      case sublist@(_ :: tail) => f(sublist) ::: flatMapSublists(tail)(f)
    }

  def combinations[A](n: Int, ls: List[A]): List[List[A]] =
    if (n == 0) List(Nil)
    else flatMapSublists(ls) { sl =>
      combinations(n - 1, sl.tail) map {sl.head :: _}
    }

  val res = permutation(List("0", "2", "5", "6"), List.empty, List.empty, 5)
  println(s"res  ${res.size} \n${res.mkString("\n")}")

  //println(combinations(2,List(1,2,3)))
}

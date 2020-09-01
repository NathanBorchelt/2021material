public class cakeRunner
{
  public static void main(String[] args)
  {
   // Cake();//monotier cake
   // new Cake(2);//bitier cake
   // new Cake(10);//decitier cake
   // new Cake(5,18);//quintier cake that also says happy birthday

    // object instantiation
    Cake myCake = new Cake();
    Cake myCake2 = new Cake();
    Cake myCake3 = new Cake(2);
    Cake myCake4 = new Cake(3);

    System.out.println(myCake4.tiers);


  }
}
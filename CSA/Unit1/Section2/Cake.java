/*
* Activity 1.2.2
*
*  A Cake class
*/
public class Cake
{    
  int tiers; //property is a global variable

  public Cake(){
    System.out.println("   ,,,\n   |||\n(~~~~~~~)\n(       )\n(~~~~~~~)\n");
  }
  public Cake(int t) {
    tiers = t;
  
    System.out.println("      ,,,\n      |||\n  (**********)\n  (*        *)\n  (*        *)");
    System.out.println("(**************)\n(*            *)\n(*            *)\n(**************)\n");

    for(int i = tiers;i>0;i--){System.out.print("(**************)\n(*            *)\n(*            *)\n(**************)\n");};
  }
  public Cake(int t, int a){
    tiers = t;
    int age = a;

    System.out.println("      ,,,\n      |||\n  (**********)\n  (*        *)\n  (*        *)");
    System.out.println("(**************)\n(*            *)\n(*            *)\n(**************)\n");

    for(int i = tiers;i>0;i--){System.out.print("(**************)\n(*            *)\n(*            *)\n(**************)\n");};
    System.out.println("Happy " + age + " Birthday");
  }
}
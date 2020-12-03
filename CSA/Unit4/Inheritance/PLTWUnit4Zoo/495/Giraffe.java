/*
 * Activity 4.9.2
 */
public class Giraffe extends Hooved
{
  public Giraffe(){}
  public Giraffe(String food, boolean nocturnal, double aveLifeSpan){
    super(food,nocturnal,aveLifeSpan);
    System.out.println("A Giraffe was born.");
  }
  public void hum()
  {
    System.out.println("The giraffe hums.");
  }
  public void speak(){
    hum();
  }
}
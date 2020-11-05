 /*
 * Activity 3.6.3
 */
public class Pet
{
  public static final int CAT = 0;
  public static final int DOG = 1;
  public static final int FOX = 2;
  
  private String name;
  private int type;
  private String owner;
  
  private int energy;
  private int happy;
  
  /*---------- constructors ----------*/
  public Pet(String name, int type)
  {
    this.name = name;
    this.type = type;

    energy = 25;
    happy = 20;
  }
  
  /*---------- accessors ----------*/
  public String getName()
  {
    return name;
  }
  
  public int getType()
  {
    return type;
  }
  
  public String getOwner()
  {
    return owner;
  }
  
  public String toString()
  {
    String state = name + ", " + owner + "'s ";
    if (type == CAT)
      state += "cat: ";
    else if (type == DOG)
      state += "dog: ";
    else if (type == FOX)
      state += "fox: ";
    state += "Happiness is " + happy + " and energy is " + energy;
    return state;
  }
  
  /*---------- mutators ----------*/
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setOwner(String owner)
  {
    this.owner = owner;
  }
  /*---------- value changing methods: mutators ----------*/
  /*----------    that change happy or energy   ----------*/
  public void feed()
  {
    energy += 10;
    happy += 5;
    System.out.println(name + " says: Mealtime!");
  }
  public void makeNoise()
  {
    energy--;
    happy -= 5;
    if (type == CAT)
      System.out.println(name + " says: Meow!");
    else if (type == DOG)
      System.out.println(name + " says: Arf arf!");
    else if (type == FOX)
      System.out.println(name += " says: Gering-ding-ding-ding-dingeringeding")

  }
  public void walk()
  {
    if (type == DOG) 
    {
      happy += 10;
      energy -= 15;
      System.out.println(name + " says: Hooray! A walk!");
    }
    else if (type == FOX){
      happy += 7;
      energy -= 20;
      System.out.println(name + " says: Hooray! A walk!");
    }
  }
  public void giveTreat(){
    energy += 3;
    happy += 5;
    System.out.println(name + " says: Yum, a treat!");
    }
  public void groom(){
    energy -= 2;
    if (type == CAT){
        happy += 5;
        System.out.println(name + " says: Purrrrrrrrrrrr!");
        }
    else if (type == DOG){
        happy -= 2;
        }
    }
  public void play(){
    energy -= 5;
    happy += 5;
    System.out.println(name + " says: Oh boy! I get to play!");
    }
  public void sleep(){
    energy += 5;
    System.out.println(name + " says: zzzzzzzzzzzzzzzzzzz");
    }
}

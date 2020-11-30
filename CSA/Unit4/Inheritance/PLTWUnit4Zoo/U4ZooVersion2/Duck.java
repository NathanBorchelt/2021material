public class Duck extends Bird
 //implements Swimming, Walking, Flying
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Duck
     */
    public Duck()
    {
       super("Reginald", "a duck");
    }

    public String makeNoise(){return "kwaq";}
    public String eat(){return "The decck eats the bread you threw at it";}
    public String swim(){return "The duck swimms the 200, freestyle, in his gold pool";}
    public String walk(){return "waddles viciously";}
    public String fly(){return "It lets go as it flys overyour car";}
}

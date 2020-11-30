
/**
 * Write a description of class Unicorn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Unicorn extends Horse implements Flying, Walking
{
    

    /**
     * Constructor for objects of class Unicorn
     */
    public Unicorn()
    {
        super("beautiful and magical horses","Play in the rainbow and eat skittles all day");
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String makeNoise(){return "Fun fact, unicor pops rainbows and can both fly and walk";}
    public String walk(){return "jogs freeeeely over the clouds";}
    public String fly(){return "drops mounds of Skittles";}
}

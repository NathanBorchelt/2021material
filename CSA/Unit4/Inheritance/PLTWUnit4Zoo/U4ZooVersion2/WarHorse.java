
/**
 * Write a description of class Warhorse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WarHorse extends Horse implements Walking
{
    

    /**
     * Constructor for objects of class WarHorse
     */
    public WarHorse()
    {
        super("Bob","Rhe firey scary beast from the deapths of the Earth");
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String makeNoise(){return "Bomb explosion";}
    public String walk(){return "leaves fire and rage in its wake";}
}
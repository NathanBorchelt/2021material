
/**
 * Write a description of class Wolf here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Wolf extends Animal implements Walking
{

    /**
     * Constructor for objects of class Wolf
     */
    public Wolf()
    {
        super("Balto", "the sled wolf");
    }
    
    public String makeNoise(){return "Woof";}
    public String eat(){return "the wolf eats a deak baby elk";}
    public String walk(){return "Runs in front of the pack";}
}

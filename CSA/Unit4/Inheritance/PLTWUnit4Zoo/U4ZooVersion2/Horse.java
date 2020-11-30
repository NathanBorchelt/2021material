
/**
 * Abstract class Horse - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Horse extends Animal implements Walking
{
   public Horse(){
       super("horse", "A big strong dog");
   }
   public Horse(String name, String desc){
       super(name,desc);
   }
   public String makeNoise(){return "neh...neh...poop";}
   public String walk() {return "gallop by default";}
}


/**
 * Abstract class Bird - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Bird extends Animal implements Walking, Flying, Swimming
{
    public Bird(String name, String desc){
        super(name,desc);}
    public String makeNoise(){
        return "Tweet ... tweet ......";}
    public String walk(){
        return "they kind of waddle ish, not sure.";}
    public String fly(){
        return "government drone goes brrrrrr....";}
    public String eat(){
        return "rains green turds from the sky";}
    public String swim(){
        return "floats on the water like wood";
    }
    public abstract String poopOnCars();
        
}

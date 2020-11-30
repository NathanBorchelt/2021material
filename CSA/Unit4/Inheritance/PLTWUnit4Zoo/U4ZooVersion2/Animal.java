
/**
 * Abstract class Animal - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Animal
{
    // instance variables - replace the example below with your own
    private String name;
    private String desc;
    //since it is abstract,  there cannot be 
    /*No-args constructor
    public Animal(){}*/
    public Animal(String name, String desc){
        this.name = name;
        this.desc = desc;
    }
    public String getName(){return name;}
    public String getDesc(){return desc;}
}

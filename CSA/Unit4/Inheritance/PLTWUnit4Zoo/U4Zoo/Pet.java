
/**
 * Activity 4.9.1
 * 
 * Write a description of class Pet here.
 *
 * @author Borchelt
 * @version 20201105
 */
public class Pet
{
    // instance variables - replace the example below with your own
    private String name;
    private int energy;
    private int happy;

    public void setName(String n){
        this.name=n;
    }
    public String getName(){
        return name;
    }
    public void setEnergy(int e){
        this.energy = e;
    }
    public int getEnergy(){
        return energy;
    }
    public void eat(){
        energy+=10;
        happy+=5;
        System.out.println("MEALTIME!!!");
    }
    public void sleep(){
        energy+=5;
        System.out.println("zzzzzzzzzzzzzzzz");
    }
}

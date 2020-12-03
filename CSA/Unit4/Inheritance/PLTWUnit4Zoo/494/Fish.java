/*
 * Activity 4.9.3
 */
public class Fish extends LakeObject
{
    public Fish(){
        super();
        super.setCost(2);
    }

    @Override
    public String say(){
        return("You got a fish");
    }
    @Override
    public int getCost(){
        return super.getWeight()*2;
    }
}
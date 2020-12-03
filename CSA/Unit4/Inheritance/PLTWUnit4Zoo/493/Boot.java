/*
 * Activity 4.9.3
 */
public class Boot extends LakeObject
{
    @Override
    public String say(){
        return("You got a boot");
    }
    @Override
    public boolean wasCaught(Hook h){
        return true;
    }
}

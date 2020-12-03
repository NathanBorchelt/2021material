/*
 * Activity 4.9.3
 */
public class Boot extends LakeObject
{
    public Boot(){
        super()
        super.setCost(0);
    }
    @Override
    public String say(){
        return("You got a boot");
    }
    @Override
    public boolean wasCaught(Hook h){
        return true;
    }
}

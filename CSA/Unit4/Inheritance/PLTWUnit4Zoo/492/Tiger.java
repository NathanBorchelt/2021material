public class Tiger extends Feline {
    
    public Tiger(){}
    public Tiger(String food, boolean nocturnal, double aveLifeSpan){
        super(food, nocturnal, aveLifeSpan);
    }
    public void swim(){
        System.out.println("Ypu cannot bounce well in the water\nBUT my tail can be a propeller!");
    }
    public void huntAlone(){
        System.out.println("I am going to find Pohh some Huney");
    }

}

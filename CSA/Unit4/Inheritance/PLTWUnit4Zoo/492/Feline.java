public class Feline extends Animal {
    public Feline(){}
    public Feline(String food, boolean nocturnal, double aveLifeSpan){
        super(food, nocturnal, aveLifeSpan);
    }
    public void speak(){
        System.out.println("The feline purrs");
    }
}

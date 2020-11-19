public class Bird extends Animal{
    public Bird(){}
    public Bird(String food, boolean nocturnal, double aveLifeSpan){
        super(food,nocturnal,aveLifeSpan);
    }
    public void speak(){
        System.out.println("The bird chirps");
    }
}

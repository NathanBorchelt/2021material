public class Owl extends Bird{
    public Owl(){}
    public Owl(String food, boolean nocturnal, double aveLifeSpan){
        super(food, nocturnal, aveLifeSpan);
    }
    public void eat(){
        System.out.println("The owl eats in the dark of night");
    }
}

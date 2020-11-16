public class Penguin extends Bird{
    public Penguin(){}
    public Penguin(String food, boolean nocturnal, double aveLifeSpan){
        super(food,nocturnal,aveLifeSpan);
    }
    public void fish(){
        System.out.println("The penguin fishes for food");
    }
}

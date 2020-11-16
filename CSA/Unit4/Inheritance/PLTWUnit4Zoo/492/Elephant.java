public class Elephant extends Animal{

    public Elephant(){System.out.println("A baby ewiphant was born......");}

    public Elephant(String food, boolean nocturnal, double aveLifeSpan){
        //looks to super class and passes variables to the constructor
        super(food,nocturnal,aveLifeSpan);
        //super has to bee the first line of constructor
        System.out.println("An Elephant was born......");
      }

    public void trumpet() {
        System.out.println("The Elephant plays its trumpet");
    }
    public void forage() {
        System.out.println("The ELEPHANT forages for food.");
    }
}

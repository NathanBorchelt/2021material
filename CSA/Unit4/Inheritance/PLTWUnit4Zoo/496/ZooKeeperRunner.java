import java.util.ArrayList;
/*
 * Activity 4.9.2
 */
public class ZooKeeperRunner
{
  public static void main(String[] args){
     ArrayList<Animal> zooAnimals = new ArrayList<Animal>();
     zooAnimals.add(new Deer());
     zooAnimals.add(new Elephant("leaves, grasses, roots", false, 60.));
     zooAnimals.add(new Giraffe("leaves",false,25.));
     zooAnimals.add(new Gorilla());
     zooAnimals.add(new Hippo());
     zooAnimals.add(new Lion());
     zooAnimals.add(new Monkey());
     zooAnimals.add(new Owl());
     zooAnimals.add(new Penguin());
     zooAnimals.add(new Tiger("meat", true, 17.));

     for(Animal animal : zooAnimals){
       //hearTheAnimal(animal);;
     }

     System.out.println("Polymorph 496 below here:\n");
     Animal a = new Elephant("leaves, grasses, roots", false, 60.);
     a.isNocturnal();
     //isNocturnal works because it is part of the animal class, 
     //but trumpet does not work because it is not part of the animal.
     //basically, animal is not an elepahnt, so it can not use its functions
     
     //a.trumpet();
     Animal a1 = new Animal();
     a1.eat();
     //the following code does nto work because Objects cannot eat.
     //Object a2 = new Animal();
     //a2.eat();

     Object o = new Object();
     System.out.println(o.toString());
     Elephant e = new Elephant();
     System.out.println(e.toString());
    }
  public static void hearTheAnimal(Animal animal){
    animal.speak();
  }
}
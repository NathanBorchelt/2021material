import java.util.ArrayList;
/*
 * Activity 4.9.2
 */
public class ZooKeeperRunner
{
  public static void main(String[] args){
     ArrayList<Animal> zooAnimals = new ArrayList<Animal>();
     zooAnimals.add(new Deer());
     zooAnimals.add(new Elephant());
     zooAnimals.add(new Giraffe());
     zooAnimals.add(new Gorilla());
     zooAnimals.add(new Hippo());
     zooAnimals.add(new Lion());
     zooAnimals.add(new Monkey());
     zooAnimals.add(new Owl());
     zooAnimals.add(new Penguin());
     zooAnimals.add(new Tiger());

     for(Animal animal : zooAnimals){
       hearTheAnimal(animal);;
     }
  }
  public static void hearTheAnimal(Animal animal){
    animal.speak();
  }
}
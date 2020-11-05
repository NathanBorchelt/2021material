
/**
 * running code
 *
 * @author Borchelt)
 * @version 20201105
 */
public class InheritedPet
{
    public static void main(String[] args){
        System.out.println("My cats");
        Cat myCat = new Cat();
        myCat.setName("Romeo");
        System.out.println(myCat.getEnergy());
        myCat.purr();
        myCat.eat();
        myCat.sleep();
        System.out.println(myCat.getEnergy());
        
        Dog myDog = new Dog();
        myDog.setName("Ruger");
        System.out.println(myDog.getEnergy());
        myDog.walk();
        myDog.eat();
        myDog.sleep();
        System.out.println(myDog.getEnergy());
        
        Fox myFox = new Fox();
        myFox.setName("Ruby");
        System.out.println(myFox.getEnergy());
        myFox.yip();
        myFox.eat();
        myFox.sleep();
        System.out.println(myFox.getEnergy());
    }
}

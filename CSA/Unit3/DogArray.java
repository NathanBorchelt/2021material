import java.util.Scanner;


public class DogArray {
    public static void main(String[] args){
    Dog[] myDogs = {new Dog("Zeke"), new Dog("Kodi"), new Dog("Kate")};
    Dog[] neighborsDogs = new Dog[2];
    
    printDoggies(myDogs);
    System.out.println(myDogs[0].getName());
    myDogs[0].setName("Bobyoda");
    System.out.println(myDogs[0].getName());

    neighborsDogs[0].setName("Yappie");
    neighborsDogs[1].setName("Annoying");

    Dog[] thePound = new Dog[10];
    Scanner in = new Scanner(System.in);
    for (int i = 0;i<thePound.length;i++){
        thePound[i] = new Dog(in.nextLine());
    }
    for (int i = 0;i<thePound.length;i++){
        thePound[i].eat("Cheeseburger");
    }
    in.close();
    }

    public static void printDoggies(Dog[] doggies){
        for (int i=0;i<doggies.length;i++){
            System.out.print(doggies[i]);   //toString meathod prints the names
        } 
    }

}

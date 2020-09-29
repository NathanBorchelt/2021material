import java.util.Scanner;
import java.util.ArrayList;
public class PetShopArrayList {
    public static void main(String[] args) {
        String inputString = "";
        String inAnimal;
        int animalIndex;
        Scanner in = new Scanner(System.in);
        ArrayList <String> animalList = new ArrayList<String>();
        animalList.add("Dog");
        animalList.add("Cat");
        animalList.add("Rabbit");
        while (!inputString.equals("q")){
            System.out.print("\nCould you like to (a)dd, (i)nsert, (r)emove, re(p)lace, or (q)uit:  ");
            inputString = in.next();
            if (inputString.equals("a")){
                System.out.print("\nEnter an animal:  ");
                animalList.add(inAnimal = in.next());
            }
            else if (inputString.equals("i")){
                System.out.print("\nEnter an animal:  ");
                inAnimal = in.next();
                System.out.print("\nWhat Position:  ");
                animalIndex = in.nextInt();
                if((animalIndex > 0) && (animalIndex<animalList.size())){
                    animalList.add(animalIndex-1,inAnimal);
                }
                else{
                    System.out.println("Bad Position");
                }
            }
            else if (inputString.equals("r")){
                System.out.print("\nWhat Position:  ");
                animalIndex = in.nextInt();
                if((animalIndex > 0) && (animalIndex<animalList.size())){
                    animalList.remove(animalIndex);
                }
                else{
                    System.out.println("Bad Position");
                }
            }
            else if (inputString.equals("p")){
                System.out.print("\nEnter an animal:  ");
                inAnimal = in.next();
                System.out.print("\nWhat Position:  ");
                animalIndex = in.nextInt();
                if((animalIndex > 0) && (animalIndex<animalList.size())){
                    animalList.remove(animalIndex-1);
                    animalList.add(animalIndex-1,inAnimal);
                }
                else{
                    System.out.println("Bad Position");
                }
            }
        }
    in.close();
    } 
}

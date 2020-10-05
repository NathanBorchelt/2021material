import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.NumberFormat;
public class SearchingNotes {
    public static void main(String[] args) {
        boolean isItPrime;
        Scanner in = Scanner(System.in);
        ArrayList<Integer> primeBelow50 = new ArrayList<Integer>();
        for(int i = 2; i <=50;i++){
            isItPrime=primeChecker(i);
            if(isItPrime){
                primeBelow50.add(i);
            }
        }
        System.out.println(primeBelow50);

        //two types of searches
        //linear and binary
        //cannot create a binary version due to not being able to sort

        //linear search for a number in primeUnder50
        long numToFind = in.nextLong();
        for(int i = 0; i<primeBelow50.size();i++){
            if (primeBelow50.get(i)==numToFind){
                System.out.println(numToFind + " was found at index " + i);
            }
        }


        in.close();
    }

    public static boolean primeChecker(long prime){
        boolean isPrime = false;
        for(long i = 2;i<=prime/2;i++){
            if(prime % i == 0){
                isPrime = true;
                break;
            }
        }
        return !isPrime;
    }
}

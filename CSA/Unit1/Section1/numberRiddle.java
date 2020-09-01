import java.util.Scanner;
public class numberRiddle {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in); //allow inputs
        System.out.println("Input an intiger:  "); //gesture to user to input an intiger
        int inputNumber = in.nextInt(); //the actual input
        int stepNum; // a variable to allow final subtraction
        System.out.println(inputNumber + " * 2 = " + (stepNum = inputNumber*2)); // step 1 times 2 the input
        System.out.println(stepNum + " + 6 = " + (stepNum+=6)); // step 2 add 6
        System.out.println(stepNum + " / 2 = " + (stepNum/=2)); //step 3 devide  by 2
        System.out.println(stepNum + " - " + inputNumber +" = " + (stepNum-=inputNumber)); // use the original input to subtract from mathed number
        System.out.println("It will always be 3."); // Always has been
    }
}
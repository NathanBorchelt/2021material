import java.util.Random;
import java.util.Scanner;
public class TheLottery {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        int credits = 100;
        int turns = 5;
        String input;
        int inputBet;
        int inputNum;
        int guessingNum;
        boolean correctAnswer = false;
        String instructionLine = "Enter an amount to gamble or type \"quit\" to quit:  ";
        String inputInstruction = "Enter a number between 1 & 100:  ";
        System.out.print(instructionLine); input = in.nextLine();
        while ((!input.equals("Quit") || !input.equals("quit")) && credits > 0){
            inputBet = Integer.valueOf(input);
            guessingNum = rand.nextInt(100)+1;
            System.out.println(inputInstruction); inputNum = in.nextInt();
            while ( !correctAnswer && turns != 0){
                if (inputNum == guessingNum){
                    correctAnswer = true;
                    credits += inputBet+(turns*inputBet*.1);
                }
                else{
                    turns--;
                    if (turns == 0){
                        credits-=inputBet;
                    }
                }
                if (inputNum > guessingNum){
                    System.out.println("The number is lower");
                }
                else if(inputNum < guessingNum){
                    System.out.println("The number is higher");
                }
                System.out.println(inputInstruction); inputNum = in.nextInt();
            }
            System.out.print(instructionLine); 
            input = in.nextLine();
            turns = 5;
            correctAnswer = false;
        }
        in.close();
    }
}

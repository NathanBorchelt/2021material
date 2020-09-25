import java.util.Random;
import java.util.Scanner;
public class TheLottery {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        int credits = 100;
        int turns;
        int initTurns;

        String input;
        String checkString = "";

        int inputBet;
        int inputNum;
        int guessingNum;
        boolean correctAnswer = false;
        String instructionLine = "Enter an amount to gamble or type \"quit\" to quit:  ";
        String inputTurnsLines = "\nHow many turns do you think it will take you:   ";
        String inputInstruction = "Enter a number between 1 & 100:  ";
        System.out.print(instructionLine); 
        input = in.nextLine();
        System.out.print(inputTurnsLines); 
        turns = in.nextInt();
        initTurns = turns;
        while ((!input.equals("Quit") || !input.equals("quit")) && credits > 0){
            inputBet = Integer.valueOf(input);
            credits -= inputBet;
            guessingNum = rand.nextInt(100)+1;
            System.out.println(guessingNum);
            System.out.println(inputInstruction + "Guesses Left:  " + turns); inputNum = in.nextInt();
            while ( !correctAnswer && turns > 0){
                checkString += (String.valueOf(inputNum) + " ");
                if (inputNum == guessingNum){
                    correctAnswer = true;
                    credits += inputBet+((turns/initTurns)*inputBet*.1);
                    break;
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
                while (checkString.indexOf(String.valueOf(inputNum)) != -1){
                    System.out.println(inputInstruction + "Guesses Left:  " + turns); inputNum = in.nextInt();
                }
            }
            System.out.print(instructionLine); 
            input = in.next();
            checkString = "";
            System.out.print(inputTurnsLines); 
            turns = in.nextInt();
            initTurns = turns;
            correctAnswer = false;
            System.out.println("Number of credits:  " + credits);
        }
        in.close();
    }
}

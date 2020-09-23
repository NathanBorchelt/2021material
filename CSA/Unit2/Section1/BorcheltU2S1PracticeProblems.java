import java.util.Scanner;
import java.util.Random;
public class BorcheltU2S1PracticeProblems {
    public static void main(String[] args) {
        passwordGenerator();
    }
    public static void passwordGenerator(){
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        int charType;
        int capLets;
        int lowLets;
        int specChars;
        int numChars;
        int addedCap = 0;
        int addedLow = 0;
        int addedSpc = 0;
        int addedNum = 0;
        int i;

        boolean doneCap = false;
        boolean doneLow = false;
        boolean doneSpc = false;
        boolean doneNum = false;
        boolean allDone = false;
        boolean anotherLoop = false;

        char tempChar;

        String specCharsString = "!@#$%&()_-+=?";
        String passwordOut;
        String checkAgain;
        while(!anotherLoop){
            passwordOut = "";

            System.out.print("ENTER THE AMOUNT OF CAPITALIZED LETTERS:  ");
            capLets = in.nextInt();
            System.out.print("\nenter the amount of lowercase letters:  ");
            lowLets = in.nextInt();
            System.out.print("\nEnter the number of Special Characters:  ");
            specChars = in.nextInt();
            System.out.print("\nEnter the amount of Numbers:   ");
            numChars = in.nextInt();

            while(!allDone){
                charType = rand.nextInt(4);
                if (charType == 0 && addedCap != capLets){
                    tempChar = (char)(rand.nextInt(26)+65);
                    passwordOut += tempChar; addedCap++;
                    if (addedCap == capLets){
                        doneCap = true;
                    }
                }
                else if (charType == 1 && addedLow != lowLets){
                    tempChar = (char)(rand.nextInt(26)+97);
                    passwordOut += tempChar; addedLow++;
                    if (addedLow == lowChars){
                        doneLow = true;
                    }
                }
                else if (charType == 2 && addedSpc != specLets){
                    tempChar = specCharsString.substring(i = rand.nextInt(specCharsString.length()),i+1);
                    passwordOut += tempChar; addedSpc++;
                    if (addedSpc == spcChars){
                        doneSpc = true;
                    }
                }
                else if (charType == 3 && addedNum != numChars){
                    tempChar = String.valueOf(rand.nextInt(10));
                    passwordOut += tempChar; addedNum++;
                    if (addedNum == numChars){
                        doneNum = true;
                    } 
                }
                if(doneCap && doneLow && doneNum && doneSpc && true){
                    allDone = true;
                }
            }
            System.out.println("Password:  " + passwordOut);
            System.out.println("\n\nWould you like to generate another password:  (Y/N)");
            checkAgain = in.nextLine();
            if (!checkAgain.equals("N")){
                anotherLoop = true;
            }

        }
    }
}

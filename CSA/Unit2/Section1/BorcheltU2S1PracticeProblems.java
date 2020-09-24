import java.util.Scanner;
import java.util.Random;
public class BorcheltU2S1PracticeProblems {
    public static void main(String[] args) {
        cToFChart();
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

        String tempChar;
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
                    tempChar = String.valueOf((char)(rand.nextInt(26)+65));
                    passwordOut += tempChar; addedCap++;
                    if (addedCap == capLets){
                        doneCap = true;
                    }
                }
                else if (charType == 1 && addedLow != lowLets){
                    tempChar = String.valueOf((char)(rand.nextInt(26)+97));
                    passwordOut += tempChar; addedLow++;
                    if (addedLow == lowLets){
                        doneLow = true;
                    }
                }
                else if (charType == 2 && addedSpc != specChars){
                    tempChar = specCharsString.substring(i = rand.nextInt(specCharsString.length()),i+1);
                    passwordOut += tempChar; addedSpc++;
                    if (addedSpc == specChars){
                        doneSpc = true;
                    }
                }
                else if (charType == 3 && addedNum != numChars){
                    tempChar = String.valueOf((char)(rand.nextInt(10)+48));
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
    public static void cToFChart(){
        int f = 20;
        double fahrenheitOut;
        double celsiusOut;
        System.out.printf("%10s%10s%5s%10s%10s","Celsius","Fahrenheit","|     ","Fahrenheit","Celsius");
        for (int c =0;c < 100;c+=2){
            fahrenheitOut = c*1.8+32;
            celsiusOut = (f-32)/1.8;
            System.out.printf("%10d%10d.3%5s%10d%10d.3",c,fahrenheitOut,"|     ",f,celsiusOut);
            f+=5;


        }
    }
}


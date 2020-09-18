//https://stackoverflow.com/questions/19599880/if-statement-inside-the-print-statement
import java.util.Scanner; import java.util.Random;
public class BorcheltU1S3PracticeProblems {
    public static void main(String[] args) {
        sortThreeInts();
    }
    public static void findFutureDates(){
        Scanner in = new Scanner(System.in);
        String sDayName;
        String eDayName;
        System.out.print("Enter today's date:  ");int sDay = in.nextInt();
        System.out.print("Enter the number of days that have passed since today:  ");int eDay = ((in.nextInt() + sDay)%7);
        if(sDay == 0){sDayName = "Sunday";}
        else if (sDay == 1){sDayName = "Monday";}
        else if (sDay == 1){sDayName = "Tuesday";}
        else if (sDay == 1){sDayName = "Wednesday";}
        else if (sDay == 1){sDayName = "Thursday";}
        else if (sDay == 1){sDayName = "Friday";}
        else{sDayName = "Saturday";}
        
        if(eDay == 0){eDayName = "Sunday";}
        else if (eDay == 1){eDayName = "Monday";}
        else if (eDay == 1){eDayName = "TuesDay";}
        else if (eDay == 1){eDayName = "Wednesday";}
        else if (eDay == 1){eDayName = "Thursday";}
        else if (eDay == 1){eDayName = "Friday";}
        else{eDayName = "Saturday";}
        System.out.println("Today is " + sDayName + " the future dat is " + eDayName);
        in.close();
    }
    public static void sortThreeInts(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a three digit number:  "); String numIn = in.nextLine();
        int first = Integer.valueOf(numIn.substring(0,1));
        int middle = Integer.valueOf(numIn.substring(1, 2));
        int last = Integer.valueOf(numIn.substring(2));

        String output = String.valueOf(first);

        if (first >= middle){
            output  = String.valueOf(middle) + output;
            if (first >= last){
                output  = String.valueOf(last) + output;
            }
            else{
                output += String.valueOf(last);
            }
        }
        else{
            output += String.valueOf(middle);
            if (middle >= last){
                output  = String.valueOf(last) + output;
            }
            else{
                output += String.valueOf(last);
            }

        }

        System.out.println(numIn+" reorganized is " + output);
        in.close();
    }
    public static void palindromeInteger(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a three digit number:   ");String inNum = in.nextLine();
        if (inNum.substring(0,1).equals(inNum.substring(2))){
            System.out.println(inNum + " is a palindrone");
        }
        else{System.out.println(inNum + " is not a palindrone");}
        in.close();
    }
    public static void randomCard(){
        Random rand = new Random();
        String cardNum = "A23456789TJQK";
        String suit = "CDHS";
        int randFace = rand.nextInt(cardNum.length());
        int randSuit = rand.nextInt(suit.length());
        String printFace = String.valueOf(cardNum.substring(randFace,randFace+1));
        String printSuit = String.valueOf(suit.substring(randSuit,randSuit+1));
        if (printFace == "A"){
            printFace = "Ace";
        }
        else if (printFace.equals("T")){
            printFace = "10";
        }
        else if (printFace.equals("J")){
            printFace = "Jack";
        }
        else if (printFace.equals("Q")){
            printFace = "Queen";
        }
        else if(printFace.equals("K")){
            printFace = "King";
        }

        if (printSuit.equals("C")){
            printSuit = "Clubs";
        }
        else if(printSuit.equals("D")){
            printSuit = "Diamonds";
        }
        else if(printSuit.equals("H")){
            printSuit = "Hearts";
        }
        else if(printSuit.equals("S")){
            printSuit = "Suits";
        }

        System.out.println("The Card You Picked is the " + printFace + " of " + printSuit);
        in.close()
    } 
    public static void rpsls(){
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int playerScore = 0;
        int compScore = 0;
        int timesPlayed = 0;
        double winAvg = 0;

        String compOptions = "rkprsrldsk";
        String compOut;
        int compChoice;

        System.out.println("Enter:\n(rk) Rock\n(pr) Paper\n(sr) Scissors\n(ld)Lizard\n(sk) Spock");
        String playerIn = in.nextLine();

        while(!playerIn.equals("quit") && (compOptions.indexOf(playerIn) != -1)){
            compChoice = rand.nextInt(compOptions.length()/2)
            compOut = compOut.substring(compChoice,compChoice+2);
            //player chooses rock
            if (playerIn.equals("rk")){
                if (compOut.equals("ld") || compOut.equals("sr")){
                    System.out.println("The computer is" + ((compOut.equals("ld")) ? "Lizard" : "Scissors")+", You picked Rock, You Win!");
                    playerScore ++;
                }
                else if(compOut.equals("ld") || compOut.equals("sr")){
                    System.out.println("The computer is" + ((compOut.equals("sk")) ? "Spock" : "Paper")+", You picked Rock, You Lose");
                    compScore ++;
                }
                else{
                    System.out.println("You both were Rock, it is a tie");
                }
                timesPlayed++;
            }
            //player chooses lizard
            if (playerIn.equals("ld")){
                if (compOut.equals("sk") || compOut.equals("pr")){
                    System.out.println("The computer is" + ((compOut.equals("sk")) ? "Spock" : "Paper")+", You picked Lizard, You Win!");
                    playerScore ++;
                }
                else if(compOut.equals("rk") || compOut.equals("sr")){
                    System.out.println("The computer is" + ((compOut.equals("rk")) ? "Rock" : "Scissors")+", You picked Lizard, You Lose");
                    compScore ++;
                }
                else{
                    System.out.println("You both were Lizzard, it is a tie");
                }
                timesPlayed++;
            }
            //player chooses Spock
            if (playerIn.equals("sk")){
                if (compOut.equals("rk") || compOut.equals("sr")){
                    System.out.println("The computer is" + ((compOut.equals("rk")) ? "Rock" : "Scissors")+", You picked Spock, You Win!");
                    playerScore ++;
                }
                else if(compOut.equals("ld") || compOut.equals("pr")){
                    System.out.println("The computer is" + ((compOut.equals("ld")) ? "Lizzard" : "Paper")+", You picked Spock, You Lose");
                    compScore ++;
                }
                else{
                    System.out.println("You both were Spock, it is a tie");
                }
                timesPlayed++;
            }
            //player chooses Scissors
            if (playerIn.equals("sr")){
                if (compOut.equals("ld") || compOut.equals("pr")){
                    System.out.println("The computer is" + ((compOut.equals("ld")) ? "Lizzard" : "Paper")+", You picked Scissors, You Win!");
                    playerScore ++;
                }
                else if(compOut.equals("sk") || compOut.equals("rk")){
                    System.out.println("The computer is" + ((compOut.equals("sk")) ? "Spock" : "Rock")+", You picked Scissors, You Lose");
                    compScore ++;
                }
                else{
                    System.out.println("You both were Scissors, it is a tie.");
                }
                timesPlayed++;
            }
            //player chooses Paper
            if (playerIn.equals("pr")){
                if (compOut.equals("sk") || compOut.equals("rk")){
                    System.out.println("The computer is" + ((compOut.equals("sk")) ? "Spock" : "Rock")+", You picked Paper, You Win!");
                    playerScore ++;
                }
                else if(compOut.equals("ld") || compOut.equals("sr")){
                    System.out.println("The computer is" + ((compOut.equals("ld")) ? "Lizzard" : "Scissors")+", You picked Paper, You Lose");
                    compScore ++;
                }
                else{
                    System.out.println("You both were Paper, it is a tie.");
                }
                timesPlayed++;
            } 
        }
        winAvg = playerScore/timesPlayed;
        System.out.println("Final Score:\nWins: "+playerScore+"\nLosses: "+compScore+"Win/Lose Ratio: "+(winAvg));
    }
}
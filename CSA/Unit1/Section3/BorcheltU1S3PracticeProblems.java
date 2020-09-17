import java.util.Scanner; import java.util.Random;
public class BorcheltU1S3PracticeProblems {
    public static void main(String[] args) {

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
        String output = numIn.substring(0,1);
        if (Integer.valueOf(numIn.substring(0,1)) < Integer.valueOf(numIn.substring(2))){
            output += numIn.substring(0,1);
            
            if(Integer.valueOf(numIn.substring(1,2)) < Integer.valueOf(numIn.substring(2))){
                output = numIn.substring(1,3);
            }
            else{
                output += numIn.substring(2) + numIn.substring(1,2);
            }
        }
        else{
            output += numIn.substring(2);
            if(Integer.valueOf(numIn.substring(0,1)) < Integer.valueOf(numIn.substring(1,2))){
                output += numIn.substring(0,1) + numIn.substring(1,2);
            }
            else{
                output += numIn.substring(1,2) + numIn.substring(0,1);
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
        String cardNum = "A123456789TJQK";
        String suit = "CDHS"
        int randFace = rand.nextInt(cardNum.length());
        int randSuit = rand.nextInt(suit.lenght());
        String printFace = String.valueOf(cardNum.substring(randFace,randFace+1));
        String printSuit = String.valueOf(suit.substring(randSuit,randSuit+1));
        if (randFace == 1){

        }
        
    }
}
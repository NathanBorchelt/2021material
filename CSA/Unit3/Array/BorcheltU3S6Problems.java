import java.util.Scanner;

import jdk.dynalink.StandardOperation;

import java.util.Random;
import java.lang.Math;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
public class BorcheltU3S6Problems{
    public static void main(String[] args) throws IOException {
        //System.out.format("The LCM is:   %d",lcm());
        bean();
    }

    public static int findMax(int[] numbers){
        //https://beginnersbook.com/2014/07/java-finding-minimum-and-maximum-values-in-an-array/
        int maxVal = numbers[0];
        for(int n : numbers){
            if (n > maxVal){
                maxVal = n;
            }
        }
        return maxVal;
    }

    public static void gradeAssigner(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of scores:  ");
        int numScores = in.nextInt();
        System.out.println("Enter each of the scores sperated with a comma: ");
        String scores = in.next();
        System.out.println(scores);
        int[] arrScores = new int[numScores];
        String[] stringArrScores = scores.split(",");
        System.out.println(java.util.Arrays.toString(stringArrScores));
        for(int i = 0; i < stringArrScores.length;i++){
            arrScores[i] = Integer.valueOf(stringArrScores[i]);
        }
        int maxScore = findMax(arrScores);
        int i = 0;
        for(int graded : arrScores){
            char grade = ' ';
            if (graded >= maxScore-5){
                grade = 'A';
            }
            else if (graded >= maxScore-10 && graded < maxScore-5){
                    grade = 'B';
            }
            else if (graded >= maxScore-15 && graded < maxScore-10){
                grade = 'C';
            }
            else if (graded >= maxScore-20 && graded < maxScore-15){
                grade = 'D';
            }
            else{
                grade = 'F';
            }
            System.out.format("Student %d scored %d and got a %c.\n", i,graded,grade);
            i++;
        }
    in.close();
    }

    public static void evenOddChecker(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter numbers:  ");
        String numbers = in.nextLine();
        String[] numbersSplit = numbers.split(" ");
        int[] numbersInts = new int[numbersSplit.length];
        short oddNums  = 0;
        short evenNums = 0;
        for(int n : numbersInts){
            if(n%2==0){
                evenNums++;
            }
            else{
                oddNums++;
            }
        }
        System.out.format("Number of even numbers:  %d\nNumber of odd numbers:  %d",evenNums,oddNums);
        in.close();
    }
    public static int lcm(){
        int baseLCM = 0;
        boolean lcmE0 = false;
        short multVal = 1;
        Scanner in = new Scanner(System.in);
        String numsString = in.nextLine();
        String[] numsArrayString = numsString.split(" ");
        int[] numsInts = new int[numsArrayString.length];
        for(short i = 0;i<numsArrayString.length;i++){
            numsInts[i]=Integer.valueOf(numsArrayString[i]);

        }
        for(int n : numsInts){
            if(n==0){
                System.out.println("The LCM is 0");
                lcmE0 = true;
                in.close();
                return baseLCM;
            }
        }
        if(!lcmE0){
            baseLCM = findMax(numsInts);
            int[] lcmDiffs = new int[numsInts.length];
            boolean all0 = false;
            while(!all0){
                for(short i = 0;i<numsInts.length;i++){
                    lcmDiffs[i] = ((baseLCM * multVal ) % numsInts[i]);
                }
                for(int n : lcmDiffs){
                    if(n==0){
                        all0=true;
                    }
                    else{
                        all0=false;
                        multVal++;
                        break;
                    }
                }
            }
        }
    in.close();
    return (baseLCM*multVal);
    }

    public static boolean isSorted(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter List");
        String list = in.nextLine();
        String[] arrList = list.split(" ");
        int[] arrIntList = new int[Integer.valueOf(arrList[0])];
        for(short i = 1;i<arrIntList.length;i++){
            arrIntList[i] = Integer.valueOf(arrList[i]);
        }
        for(short i = 1;i<arrIntList.length;i++){
            if(arrIntList[i]>arrIntList[i-1]){
                in.close();
                return false;
            }
        }
        in.close();
        return true;
    }
    public static void bean() throws IOException {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter the number of balls to drop:  ");
        int ballsDropped = in.nextInt();
        System.out.println("Enter the number of slots:   ");
        int pegs = in.nextInt();
        int[] slots = new int[pegs];
        for(short i = 0; i<slots.length;i++){
            slots[i]=0;
        }
        //R=++
        //L=--
        for(short ballIndex = 0; ballIndex<ballsDropped;ballIndex++){
            int endSlot = Math.round(pegs/2);
           // String bouncePattern = "";
            boolean bounceDirection;
            for(int pegBounce = 0;pegBounce<(pegs-1);pegBounce++){
                bounceDirection = rand.nextBoolean();
                if(bounceDirection){
                 //   bouncePattern+="R";
                    if (endSlot < pegs-1){
                        endSlot++;
                    }
                }
                else{
                   // bouncePattern+="L";
                    if(endSlot > 0){
                        endSlot--;
                    }
                }
            }
           // System.out.println(bouncePattern);
            slots[endSlot]++;
        }
        System.out.println();
        //System.out.println(java.util.Arrays.toString(slots));
        int mostInSlot = findMax(slots);
        //System.out.println(mostInSlot);
        for(int i = mostInSlot; i > 0; i--){
            //System.out.println(i);
            for(int j = 0; j<slots.length;j++){
               // System.out.println(slots[j] + "  "+ j);
                if(slots[j]>0 && slots[j]>=i){
                    Files.write(Paths.get("./BillionByThousand.txt"),"0".getBytes(StandardCharsets.UTF_8));
                }
                else{
                    Files.write(Paths.get("./BillionByThousand.txt"),"-".getBytes(StandardCharsets.UTF_8));
                }
            }
            Files.write(Paths.get("./BillionByThousand.txt"),"\n".getBytes(StandardCharsets.UTF_8));
        }
        Files.write(Paths.get("./BillionByThousand.txt"),java.util.Arrays.toString(slots).getBytes(StandardCharsets.UTF_8));
        in.close();
    }
}
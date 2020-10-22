import java.util.Scanner;

import java.util.Random;
import java.lang.Math;

import java.util.ArrayList;
public class BorcheltU3S7Problems{
    public static void main(String[] args){
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

    public static int findMax(ArrayList<Integer> numbers){
        int maxVal = numbers.get(0);
        for(int i = 0; i < numbers.size();i++){
            if (numbers.get(i) > maxVal){
                maxVal = numbers.get(i);
            }
        }
        return maxVal;
        }

    public static void gradeAssigner(){
        ArrayList<Integer> gradeList = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter each of the scores sperated with a comma: ");
        String scores = in.next();
        String[] stringArrScores = scores.split(",");
        //System.out.println(java.util.Arrays.toString(stringArrScores));
        for(int i = 0; i < stringArrScores.length;i++){
            gradeList.add(Integer.valueOf(stringArrScores[i]));
        }
        int maxScore = findMax(gradeList);
        int i = 0;
        for(int graded : gradeList){
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
        ArrayList<Integer> numbersInts = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter numbers:  ");
        String numbers = in.nextLine();
        String[] numbersSplit = numbers.split(" ");
        for(String n : numbersSplit){
            numbersInts.add(Integer.valueOf(n));
        }
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
        ArrayList<Integer> numsInts = new ArrayList<Integer>();
        int baseLCM = 0;
        boolean lcmE0 = false;
        short multVal = 1;
        System.out.println("Enter the numbers to find a lcm seperated by a space");
        Scanner in = new Scanner(System.in);
        String numsString = in.nextLine();
        String[] numsArrayString = numsString.split(" ");
        for(String n : numsArrayString){
            numsInts.add(Integer.valueOf(n));
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
            ArrayList<Integer> lcmDiffs = new ArrayList<Integer>();
            while(lcmDiffs.size() < numsInts.size()){lcmDiffs.add(0);}
            baseLCM = findMax(numsInts);
            boolean all0 = false;
            while(!all0){
                for(short i = 0;i<numsInts.size();i++){
                    lcmDiffs.set(i,((baseLCM * multVal ) % numsInts.get(i)));
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
        ArrayList<Integer> intList = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter List");
        String list = in.nextLine();
        String[] arrList = list.split(" ");
        for (String n : arrList){
            intList.add(Integer.valueOf(n));
        }

        for(short i = 1;i<intList.size();i++){
            if(intList.get(i)>intList.get(i-1)){
                in.close();
                return false;
            }
        }
        in.close();
        return true;
    }
    public static void bean(){
        ArrayList<Integer> slots = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Enter the number of balls to drop:  ");
        int ballsDropped = in.nextInt();
        System.out.println("Enter the number of slots:   ");
        int pegs = in.nextInt();
        for(int i = 0; i < pegs; i++){
            slots.add(0);
        }
        //R=++
        //L=--
        for(short ballIndex = 0; ballIndex<ballsDropped;ballIndex++){
            int endSlot = Math.round(pegs/2);
            String bouncePattern = "";
            boolean bounceDirection;
            for(int pegBounce = 0;pegBounce<(pegs-1);pegBounce++){
                bounceDirection = rand.nextBoolean();
                if(bounceDirection){
                    bouncePattern+="R";
                    if (endSlot < pegs-1){
                        endSlot++;
                    }
                }
                else{
                    bouncePattern+="L";
                    if(endSlot > 0){
                        endSlot--;
                    }
                }
            }
            System.out.println(bouncePattern);
            slots.set(endSlot,endSlot++);
        }
        System.out.println();
        //System.out.println(java.util.Arrays.toString(slots));
        int mostInSlot = findMax(slots);
        //System.out.println(mostInSlot);
        for(int i = mostInSlot; i > 0; i--){
            //System.out.println(i);
            for(int j = 0; j<slots.size();j++){
               // System.out.println(slots[j] + "  "+ j);
                if(slots.get(j)>0 && slots.get(j)>=i){
                    System.out.print("0");
                }
                else{
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        in.close();
    }
}
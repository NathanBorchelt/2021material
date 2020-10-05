import java.util.ArrayList;
import java.util.Scanner;
public class BorcheltBellringer20201005 {
    static ArrayList<Integer> inNums = new ArrayList<Integer>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String output = "Input a number or type \"q\" to quit.";
        System.out.println(output);
        String input = in.next();
        while (!input.equals("q")){

            inNums.add(Integer.valueOf(input));
            System.out.println(output);
            input = in.next();
        }
        in.close();
        min();max();average();total();mode();
    }

    public static void min(){
        int min=Integer.MAX_VALUE;
        for (int i:inNums){
            if (i<min){
                min = i;
            }
        }
        System.out.format("\nThe minimum value is %d",min);
    }

    public static void max(){
        int max=Integer.MIN_VALUE;
        for (int i:inNums){
            if (i>max){
                max = i;
            }
        }
        System.out.format("\nThe maximum value is %d",max);
    }

    public static void average(){
        double avg = 0;
        for (int i: inNums){
            avg += i;
        }
        System.out.format("\nThe average is %2.3f",avg/inNums.size());
    }

    public static void total(){
        int total = 0;
        for (int i : inNums){
            total+=i;
        }
        System.out.format("\nThe total amount is %d",total);
    }

    public static void mode() {
        int mode = inNums.get(0);
        int maxCount = 0;
        for (int i = 0; i < inNums.size(); i++) {
            int value = inNums.get(i);
            int count = 0;
            for (int j = 0; j < inNums.size(); j++) {
                if (inNums.get(j) == value) count++;
                if (count > maxCount) {
                    mode = value;
                    maxCount = count;
                    }
                }
        }
        if (maxCount > 1) {
            System.out.println("\nThe mode is"+mode);
        }
        else{
            System.out.println("\nThere is no mode.");
        }
    }
}


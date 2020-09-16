import java.util.Scanner;
public class loopPractice{
    public static void main(String[] args) {
        //Ctrl + C = stop inf loop
/*        int i = 0;
        while(i < 10){
            i++;
            System.out.println(i);
        while(i > 0){
            System.out.println(i);
            i--;
        }
        while (i < 20){
            i += 2;
            System.out.println(i);
        }
        for(int i = 1; i <=10;i++;){
            System.out.println(i);
        }
        for(int i = 10; i > 0;i--;){
            System.out.println(i);
        }
        for(int i = 2; i <= 20;i += 2;){
            System.out.println(i);
        }
        */
    
    public static void basicStats(){
        Scanner in = new Scanner(System.in);
        int total = 0;
        int i = 0;
        System.out.println("Kepp entering number, but type \"q\" to stop.");
        String input = in.nextLine();
        int convertInput = Integer.valueOf(input);
        int min = convertInput;
        int max = convertInput;
        while(!input.equals("q")){
            if (convertInput < min){
                min = convertInput;
                }
            else if (convertInput > max){
                max = convertInput;
                }
            i++;
            total += Integer.valueOf(input);
            input = in.nextLine();
            convertInput = Integer.valueOf(input);
            }
        System.out.println("Total:  " + total);
        System.out.println("Average:  " + (total/i));
        System.out.println("Maximum:  " + max);
        System.out.println("Minimum:  " + min);
        }
    }
}
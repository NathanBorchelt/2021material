import java.util.Scanner;
public class BorcheltU3S6Problems{
    public static void main(String[] args) {
        gradeAssigner();
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
        

        in.close();
    }
    
}
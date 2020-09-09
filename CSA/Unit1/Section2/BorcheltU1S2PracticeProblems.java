import java.lang.Math; import java.util.*;
public class BorcheltU1S2PracticeProblems {
   public static void main(String[] args) {
       triangleArea();
   } 
   public static void triangleArea(){
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the coordinates of the three points separated by spaces\n like x1 y1 x2 y2 x3 y3:  ");
    String inputNumbers = in.nextLine();
    String numbers[] = inputNumbers.split(" ",0);
    List<Double> fNumbers = new LinkedList<>();
    for (String n: numbers){
        fNumbers.add(Double.valueOf(n));
        }
    double side1 = Math.sqrt(Math.pow((fNumbers.get(0) - fNumbers.get(2)),2)+Math.pow((fNumbers.get(1) - fNumbers.get(3)),2));
    double side2 = Math.sqrt(Math.pow((fNumbers.get(1) - fNumbers.get(3)),2)+Math.pow((fNumbers.get(2) - fNumbers.get(4)),2));
    double side3 = Math.sqrt(Math.pow((fNumbers.get(4) - fNumbers.get(0)),2)+Math.pow((fNumbers.get(5) - fNumbers.get(1)),2));

    double s = (side1 + side2 + side3)/2;
    double area = Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
    System.out.printf("The Area of the triangle is %1f",area);
    }


}

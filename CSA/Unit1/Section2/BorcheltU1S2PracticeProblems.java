import java.lang.Math; import java.util.*;
public class BorcheltU1S2PracticeProblems {
   public static void main(String[] args) {
       midPointTable();
   } 
   public static void triangleArea(){
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the coordinates of the three points separated by spaces\n like x1 y1 x2 y2 x3 y3:  ");
    String inputNumbers = in.nextLine();
    String numbers[] = inputNumbers.split(" ",0);
    List<Double> fNumbers = new LinkedList<>();
    for (String n: numbers){fNumbers.add(Double.valueOf(n));}
    double side1 = Math.sqrt(Math.pow((fNumbers.get(0) - fNumbers.get(2)),2)+Math.pow((fNumbers.get(1) - fNumbers.get(3)),2));
    double side2 = Math.sqrt(Math.pow((fNumbers.get(1) - fNumbers.get(3)),2)+Math.pow((fNumbers.get(2) - fNumbers.get(4)),2));
    double side3 = Math.sqrt(Math.pow((fNumbers.get(4) - fNumbers.get(0)),2)+Math.pow((fNumbers.get(5) - fNumbers.get(1)),2));

    double s = (side1 + side2 + side3)/2;
    double area = Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
    System.out.printf("The Area of the triangle is %1f",area);
    in.close();
    }
    public static void midPointTable(){
        //Scanner in = new Scanner(System.in);
        //Midpoint = (p1+p2)/2  , do for both x any y
        double[][] coords = {{0,0},{2,1},{1,4},{4,2},{2,7},{6,3},{3,9},{10,5},{4,11},{12,7}};
        int step = 0;
        System.out.printf("%20s%20s%20s\n","A","B","Middle Point");
        while(step < coords.length){//https://stackoverflow.com/questions/5613891/extract-data-from-2d-array-java
            //https://www.programmingsimplified.com/java/tutorial/java-while-loop#:~:text=Java%20while%20loop%201%20If%20the%20condition%20%28s%29,loop%20can%20contain%20more%20than%20one%20statement.%20
            double xCord = midPoint(coords[step][0],coords[step+1][0]);
            double yCord = midPoint(coords[step][1],coords[step+1][1]);
            System.out.printf("%20s%20s%20s%n","("+coords[step][0]+","+coords[step][1]+")","("+coords[step+1][0]+","+coords[step+1][1]+")","("+xCord+","+yCord+")");
            step += 2;
        }

    }

    public static double midPoint(double point1, double point2){
        double rtnMidpoint = ((point1 + point2)/2);
        return rtnMidpoint;
    }

}

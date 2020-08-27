import java.util.Scanner;
import java.lang.Math;

public class distanceFormula {
    public static void main(String[] args){
    Scanner in = new Scanner(System.in);

    final int Z1 = 10;
    final int Z2 = 5;

    final double GRAVITY = 9.81;
    final int SPEED_OF_LIGHT = 299792458;

    System.out.println("X1?");
    double x1 = in.nextInt();

    System.out.println("Y1?");
    double y1 = in.nextInt();

    System.out.println("X2?");
    double x2 = in.nextInt();

    System.out.println("Y2?");
    double y2 = in.nextInt();

    double dist = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)+Math.pow(z2-z1,2));

    System.out.println(dist);
    in.close();
    }
}
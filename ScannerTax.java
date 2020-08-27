import java.util.Scanner;

public class ScannerTax {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("What is the bill? ");
        
        double bill = in.nextInt();
        System.out.println("What is the tax? ");
        
        double tax = in.nextDouble();
        System.out.println(tax(bill,tax));

        in.close();

    }

    public static double tax(double bill, double tax){
        double t = tax/100;
        double tamount = bill*t;
        double total = tamount+bill;
        return total;
    }
}
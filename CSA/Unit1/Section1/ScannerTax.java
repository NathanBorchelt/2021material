import java.util.Scanner;



public class ScannerTax {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("What is the bill? ");
        //anytime we have a new variable. you must declare the type
        double bill = in.nextInt();
        System.out.println("What is the tax? ");
        
        double tax = in.nextDouble();

        System.out.println("Discount?");
        double discount = in.nextInt();
        System.out.println(tax(bill,tax,discount));

        in.close();

    }
        /* 
            int can converto to double
            double cannot be int, because of removal of information
        */
    /*
        public static double tax(int bill, int tax){
        double t = tax/100;
        double tamount = bill*t;
        double total = tamount+bill;
        return total;
    }
    */
    public static double tax(double bill, double tax, double discount){
        double t = tax/100;
        bill = (1-discount/100)*bill;
        double tamount = bill*t;
        double total = tamount+bill;
        return total;
    }
}
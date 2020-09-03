import java.util.ArrayList;
import java.util.Scanner; import static java.lang.Math.pow;
public class BorcheltU1S1Problems{ 
    public static void main(String[] args){
        drivingCost();
    }
    public static void converMiletoKm(){ 
        Scanner in = new Scanner(System.in);  
        System.out.println("Enter How Many Miles:  ");
        double inputMiles = in.nextDouble();
        System.out.printf(inputMiles + " miles is equal to %.1f kilometers.",(1.6 * inputMiles));
        in.close();
    }
    public static void calculateEnergy(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the amount of water in kilograms:  ");
        double kiloWater = in.nextDouble();
        System.out.println("Enter the inital water temperature in *C:  ");
        double initialTemp = in.nextDouble();
        System.out.println("Enter the final water temperature in *C:  ");
        double finalTemp = in.nextDouble();
        System.out.printf("You need .1f%n" + (kiloWater*(finalTemp - initialTemp) * 4184) + "joules of energy.");
        in.close();
    }
    public static void futureInvestment(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the initial investment:  ");
        double intInvestment = in.nextDouble();
        System.out.println("Ener the APR:   ");
        double annualPercentageRate = in.nextDouble();
        System.out.println("Enter the number of years the invesstment will sit;  ");
        double yearsSitting = in.nextDouble();
        System.out.printf("$%.2f", (intInvestment * (pow(1+((annualPercentageRate/100)/12),(yearsSitting*12)))));
        in.close();
    }
    public static void drivingCost(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Miles Driven:  ");
        double milageDriven = in.nextDouble();
        System.out.println("Enter MPG:   ");
        double milesPerGallon = in.nextDouble();
        System.out.println("Enter Price Per Gallon:   ");
        double pricePerGallon = in.nextDouble();
        System.out.printf("To drive " + milageDriven + " miles, it would cost $%.2f.",((milageDriven/milesPerGallon)*pricePerGallon));
    }
}
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
public class ConditionalNotes {
    public static void main(String[] args) {
        /*
        boolean a = true;
        boolean b;
        b=false;
        //cannot add or concatenate bools
        System.out.println(a);
        System.out.println(b);
        boolean a = true; //print = true
        boolean b = !a; // print = false
        boolean c = (a==b); // a==b is false
        boolean d = !(a==b); // the inverse of thel ine above
        
        int a  = 6;
        int b  = 2;
        int c = 2;
        if (b!=0){System.out.println((a/b));
            //nested if statment
            if ((a/c)>b){System.out.println("a/c is larger than b");}
            //have as many else ifs as you want
            else if (a/c==b){System.out.println("a/c is equal to b");}
            //so conditions on the else, and only one per 
            else{System.out.println("a/c is smaller than b");}
    }*/
    truthTable();
    }
    // && is and || or
    private static void gradeChecker(int g){
        if      (g >= 90){System.out.println("A!!!");}
        else if (g >= 80){System.out.println("B!");}
        else if (g >= 70){System.out.println("C.");}
        else if (g >= 60){System.out.println("D...");}
        else             {System.out.println("F......");}

    }

    private static void truthTable(){
        int a = 1;
        int b = 2;
        int c = 3;

        System.out.println(a>b);              //fasle
        System.out.println(b>c);              //false
        System.out.println(a>b && b>c);       //false
        System.out.println(a>b || b>c);       //false
        System.out.println(!((a>b) && (b>c)));//true
    }
    private static void evenOddChecker(int n){
        if(n%2==0){System.out.printf("%d is an even number.",n);}
        else      {System.out.printf("%d is an odd number.",n); }
    }
    private static void passwordChecker(){
        Scanner in - new Scanner(System.in);
        System.out.print("What is the password?...");
        String p = in.nextLine();
        String password = "knock knock";

        while(!p.equals(password)){
            System.out.print("No, try again.");
            p = in.nextLine();
        }
        System.out.println("Okay, come on in.");
        in.close()
    }
    private static void what(){
        Scanner in = new Scanner(System.in);
        System.out.print("Hey, Guess What?");
        String i = in.nextLine();
        while(!i.equals("what"){
            System.out.print("\nHey Guess What");
            i = in.nextLine();
            }
        System.out.println("Chicken Butt");
        in.close();
        }
    private static void inTriPoint(){
        Scanner in = new Scanner(System.in);
        double x = in.nextDouble();
        double y = in.nextDouble();
        if (y<=100 && y>=0 && y<=((1/2)*-x+100)){
            if (x>0 && x<200){
                System.out.println("The point is in the triangle.");
            }
        }
        else{
            System.out.println("The point is not in the triangle.");
        }
    }
    }
    }

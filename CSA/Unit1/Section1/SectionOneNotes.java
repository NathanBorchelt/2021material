public class SectionOneNotes{
    /*
        public means any file can access this
        class name needs to be named the same as
            the file name
    */
    public static void main(String[] args){

        //public means any file can access this
        //static means ran without object created
        //void means no return
        //main is the function name

        //println is the same as print with a \n
        System.out.println("Hi");
        //print adds on to the right of the current line
        System.out.print("Howdy");
        System.out.print("Hello\n");   // \n is the new line escape character
        System.out.print("Hallo\t");    //  \t is a tab
        System.out.print("bonjour");    //; represent end of a statement
        
        System.out.println(tax(10,7));
        //printf formats the output
        System.out.printf("%5.2f%n",5.1473);
        //statements below are the same
        System.out.println("Pat"+",Rob"+",Hunter" +" how is your day?");
        System.out.printf("%s,%s,%s how is your day?","Pat","Rob","Hunt");

    }

    public static double tax(double bill, double tax){
        double t = tax/100;
        double tamount = bill*t;
        double total = tamount+bill;
        return total;
    }

}
public class CSA111{
    //public means we can access it all the files in the directory
    //static means can run without an object created
    //void means it doesn't return anything
    /*
        primative data types
            https://static.javatpoint.com/images/java-data-types.png
    */
    public static void main(String[] args){
        //System is a class
        /*
        //println creates a new line below
        System.out.println("Hello");
        //print adds new info horizontally
        System.out.print("Howdy\n");
        // the \n provides a new line
        System.out.print("Olaf\n");
        System.out.println("HI    Greetings");
        //\t allows you to place a tab
        System.out.println("HI\tGreetings");
        
        //printf means formatting
        System.out.println(5.1473);
        System.out.printf("%5.2f%n",5.1473);
        */

        double cost = 10.00;
        int discount = 15;
        int tax = 7;
        double totalCost;

        totalCost = (cost*(1-discount/100.0))*(1+tax/100.0);
        System.out.println(totalCost);



    }
}

//single line comment
/*
    anything inside of here is a comment
    javac is ran first to create our class or compile the file
    java is ran second because you are running a class
*/
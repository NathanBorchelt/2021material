import java.util.Scanner; import java.util.Random;

public class sciFiName {
    public static void main(String[] args){
        Random random = new Random();
        Scanner in = new Scanner(System.in);
        //F3,L3,F5,L4,F2,L4
        System.out.print("What is your first name:  "); String fName = in.nextLine();
        System.out.print("\nWhat is your last name:  "); String lName = in.nextLine();
        System.out.print("\nWhat is your city's name:  "); String city = in.nextLine();
        System.out.print("\nWhat is your school's name:  "); String school = in.nextLine();
        System.out.print("\nWhat is your brother's name:  "); String brother = in.nextLine();
        System.out.print("\nWhat is your sister's name:  "); String sister = in.nextLine();
        String sciFName = fName.substring(0,((int) (random.nextDouble()*random.nextInt(fName.length())/2))) + lName.substring(lName.length()-(int) (random.nextDouble()*random.nextInt(fName.length())/2));
        String sciLName = city.substring(0,(int) (random.nextDouble()*random.nextInt(fName.length())/2)) + school.substring(school.length()-(int) (random.nextDouble()*random.nextInt(fName.length())/2));
        String sciPName = brother.substring(0,(int) (random.nextDouble()*random.nextInt(fName.length())/2)) + sister.substring(sister.length()-(int) (random.nextDouble()*random.nextInt(fName.length())/2));
        System.out.println("I am " + sciFName + " " + sciLName + " of " + sciPName);
         
    }

}
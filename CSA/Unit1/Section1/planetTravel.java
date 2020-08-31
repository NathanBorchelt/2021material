public class planetTravel {
    /*public static void main(String[] args) {
        System.out.println("Max Intiger value: " + Integer.MAX_VALUE);
        System.out.println("Min Intiger value: " + Integer.MIN_VALUE);
        //Double and Intiger are a class, MAX/MIN are properties of the classes
        System.out.println("Max Double value: " + Double.MAX_VALUE);
        System.out.println("Min Double value: " + Double.MIN_VALUE);
        System.out.println("Max float value: " + Float.MAX_VALUE);
        System.out.println("Min float value: " + Float.MIN_VALUE);

        int i = Integer.MAX_VALUE;
        int amountOfBits = 0;
        while(i>0){
            // /= devide its self
            System.out.println(i/=2);
            // ++ add 1 to its self
            amountOfBits++;
        }
        System.out.println(amountOfBits);

        System.out.println((2+Integer.MAX_VALUE)/100);
        int k = 10;
        while(k>0){
            System.out.println(k*Integer.MAX_VALUE);
            k--;
        }
        
    }*/
    /* 
 * Activity 1.1.5
 */
  public static void main(String[] args){
    // the planet's average distance from earth 
    int mars = 48678219;
    // fastest spacecraft in mph
    double fastest = 24791;
    double lightSpeed = 670616629.;
    lightSpeed /= 10;

    System.out.println("Travel time to Mars: " + mars/lightSpeed + " hours.");
  }
}

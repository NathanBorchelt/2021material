public class fivePlanetTravel {
    /* 
 * Activity 1.1.5
 */
  public static void main(String[] args)
  {
    // theplanets.org average distance from earth to the planets
    int mercury = 56974146;
    int venus = 25724767;
    int mars = 48678219;
    int jupiter = 390674710;
    int saturn = 792248270;

    // number of planets to visit
    int numPlanets = 5;

    // speed of light and our speed
    double lightSpeed =  24791;

    // total travel time
    //double total = 0;

    /* your code here */
    System.out.printf("Total Time: %5.3f%n", mercury/lightSpeed); System.out.print(".  Planets left: " + numPlanets-- + "\n");
    System.out.printf("Total Time: %5.3f%n", venus/lightSpeed); System.out.print(".  Planets left: " + numPlanets-- + "\n");
    System.out.printf("Total Time: %5.3f%n", mars/lightSpeed); System.out.print(".  Planets left: " + numPlanets-- + "\n");
    System.out.printf("Total Time: %5.3f%n", jupiter/lightSpeed); System.out.print(".  Planets left: " + numPlanets-- + "\n");
    System.out.printf("Total Time: %5.3f%n", saturn/lightSpeed); System.out.print(".  Planets left: " + numPlanets-- + "\n");
    System.out.printf("Delta Time: %5.3f%n",  ((saturn/lightSpeed)-(mercury/lightSpeed)));
    
    System.out.println(.3+.5);
    System.out.println((int)(.3+.5));
    System.out.println(.6+.5);
    System.out.println((int)(.6+.5));
    System.out.println(3.4+.5);
    System.out.println((int)(3.4+.5));
    System.out.println(3.8+.5);
    System.out.println((int)(3.8+.5));
    System.out.println(15.0+.5);
    System.out.println((int)(15.0+.5));
    System.out.println(21.5+.5);
    System.out.println((int)(21.5+.5));
    System.out.println(99.9+.5);
    System.out.println((int)(99.9+.5));
  }
}

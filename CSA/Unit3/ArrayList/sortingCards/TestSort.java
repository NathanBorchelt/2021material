/**
 * Activity 3.7.6
 */
import java.util.ArrayList;

public class TestSort
{
  public static void main(String[] args)
  { 
    ArrayList<Integer> intList = new ArrayList<Integer>();
    intList.add(16);
    intList.add(22);
    intList.add(19);
    intList.add(5);
    intList.add(20);
    intList.add(7);
    intList.add(4);
    intList.add(-9);
    intList.add(100);
    intList.add(57);

    ArrayList<Double> doubleList = new ArrayList<Double>();
    doubleList.add(-4.5);
    doubleList.add(-9.0);
    doubleList.add(0.0);
    doubleList.add(6.45);
    doubleList.add(7.4);
    doubleList.add(6.4);
    doubleList.add(10.1);
    doubleList.add(9.9);

    ArrayList<Integer> testSelectionInt;
    ArrayList<Double> testSelectionDouble;
    ArrayList<Integer> testInsertionInt;
    ArrayList<Double> testInsertionDouble;

    ArrayList<String> stringList = new ArrayList<String>();
    stringList.add("nate");
    stringList.add("quaid");
    stringList.add("tommy");
    stringList.add("harry");
    stringList.add("frank");
    stringList.add("violet");
    stringList.add("grant");
    stringList.add("bobby");
    stringList.add("will");
    stringList.add("jones");
    stringList.add("jess");
    stringList.add("kelly");
    stringList.add("rich");
    stringList.add("sandy");
    stringList.add("bryan");
    stringList.add("duke");
    stringList.add("xena");
    stringList.add("zoe");
    stringList.add("george");
    stringList.add("oscar");
    stringList.add("wanda");
    stringList.add("lindsay");
    stringList.add("david");
    stringList.add("lauren");
    stringList.add("patty");
    stringList.add("quin");
    stringList.add("mandy");
    stringList.add("cory");
    stringList.add("earl");
    stringList.add("taylor");
    stringList.add("megan");
    stringList.add("izzie");
    stringList.add("xavier");
    stringList.add("nancy");
    stringList.add("verquindon");
    stringList.add("chance");
    stringList.add("kelsey");
    stringList.add("yusuf");
    stringList.add("adam");
    stringList.add("freddy");
    stringList.add("yuri");
    stringList.add("oliver");
    stringList.add("umar");
    stringList.add("illyad");
    stringList.add("randy");
    stringList.add("uriel");
    stringList.add("zion");
    stringList.add("elizabeth");
    stringList.add("patunia");
    stringList.add("alex");
    stringList.add("sam");
    stringList.add("henry");


    ArrayList<String> outStringList = SortingAlgorithms.insertionSortStrings(stringList);
    for (short i = 0;i < outStringList.size();i++){
      System.out.format("%s\t\t%s\n",outStringList.get(i),stringList.get(i));
    }

    // Test the integer selection sort
    /*
    testSelectionInt = SortingAlgorithms.selectionSortInt(intList);
    System.out.println(testSelectionInt);
    */

    // Test the double selection sort
    /*
    testSelectionDouble = SortingAlgorithms.selectionSortDouble(doubleList);
    System.out.println(testSelectionDouble);
    */

    // Test the integer insertion sort
    /*
    testInsertionInt = SortingAlgorithms.insertionSortInt(intList);
    System.out.println(testInsertionInt);
    */

    // Test the double insertion sort
    /*
    testInsertionDouble = SortingAlgorithms.insertionSortDouble(doubleList);
    System.out.println(testInsertionDouble);
    */
  }
}
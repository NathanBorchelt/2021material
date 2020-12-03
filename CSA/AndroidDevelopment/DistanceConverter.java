import java.util.Scanner;
public class DistanceConverter{
    public static void main(String[] args) {
        String[] inputUnits = {"mm","cm","m","km"};
        double[] convertFactor = {.001,.01,1,1000};
        String[] inputSplit = new String[3];
        Scanner in = new Scanner(System.in);
        String qLine = "Enter the conversion you want (distance , Unit 1, Unit 2)";
        System.out.println(qLine);
        String input = in.nextLine();
        while (!input.equalsIgnoreCase("")){
            input = input.replaceAll(" ", "");
            inputSplit = input.split(",");
            int startUnit = findIndex(inputUnits, inputSplit[1]);
            int endUnit = findIndex(inputUnits, inputSplit[2]);
            double output = ((Double.valueOf(inputSplit[0])*convertFactor[startUnit])/convertFactor[endUnit]);
            System.out.println(String.valueOf(output) + " "+ inputUnits[endUnit]);
            System.out.println(qLine);
            input = in.nextLine();
        }
        in.close();
    }
    //https://www.geeksforgeeks.org/find-the-index-of-an-array-element-in-java/
    public static int findIndex(String[] array, String finding){
        int length = array.length;
        int i = 0;
        while(i<length){
            if(array[i].equals(finding)){
                return i;
            }
            else{
                i++;
            }
        }
        return -1;
    }
}
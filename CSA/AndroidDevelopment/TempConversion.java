import java.util.Scanner;
public class TempConversion{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    
        //(K − 273.15) × 9/5 + 32 = °F
        //(°F − 32) × 5/9 + 273.15 = K
        //K − 273.15 = C
        //c+ 273.15 = k
        //TK * (9/5) = Rankine (R)
        //tr*5/9 = K
        //(373.15-Tk)*(3/2) = delisle (D)
        //373.15-(Td * 2/3) = K

        String[] unitSymbols = {"F","C","K","R","D"};
        String[] userInput = new String[3];
        String qLine = "Enter the conversion you want (Temp , Unit 1, Unit 2)";
        System.out.println(qLine);
        String input = in.nextLine();
        double kelvinMiddleMan=0;
        double outputNum=0;
        while (!input.equalsIgnoreCase("")){
            input = input.replaceAll(" ", "");
            userInput = input.split(",");
            
            if(userInput[1].equalsIgnoreCase("F")) kelvinMiddleMan = ((Double.valueOf(userInput[0])-32)*5/9+273.15);
            else if(userInput[1].equalsIgnoreCase("C")) kelvinMiddleMan = (Double.valueOf(userInput[0])+273.15);
            else if(userInput[1].equalsIgnoreCase("K")) kelvinMiddleMan = Double.valueOf(userInput[0]);
            else if(userInput[1].equalsIgnoreCase("R")) kelvinMiddleMan = (Double.valueOf(userInput[0])*5/9);
            else if(userInput[1].equalsIgnoreCase("D")) kelvinMiddleMan = (373.15-(Double.valueOf(userInput[0])*2/3));

            //(K − 273.15) × 9/5 + 32 = °F
            //(°F − 32) × 5/9 + 273.15 = K
            //K − 273.15 = C
            //c+ 273.15 = k
            //TK * (9/5) = Rankine (R)
            //tr*5/9 = K
            //(373.15-Tk)*(3/2) = delisle (D)
            //373.15-(Td * 2/3) = K

            if(userInput[2].equalsIgnoreCase("F")) outputNum = (kelvinMiddleMan-273.15)*9/5+32;
            else if(userInput[2].equalsIgnoreCase("C")) outputNum = kelvinMiddleMan-273.15;
            else if(userInput[2].equalsIgnoreCase("K")) outputNum = kelvinMiddleMan;
            else if(userInput[2].equalsIgnoreCase("D")) outputNum = kelvinMiddleMan*9/5;
            else if(userInput[2].equalsIgnoreCase("R")) outputNum = (373.15-kelvinMiddleMan)*3/2;

            System.out.println(userInput[0]+userInput[1]+" is equivalent to "+String.valueOf(outputNum)+userInput[2]);
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
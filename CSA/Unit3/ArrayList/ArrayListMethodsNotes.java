import java.util.ArrayList;
import java.util.Scanner;
public class ArrayListMethodsNotes {    
    static ArrayList<Integer> arrList = new ArrayList<Integer>();
    static int[] arr = {1,2,0,3,2,4,2,1,0,2,0,1,3,2};
    public static void main(String[] args) {
    //convert arr to arrList using a for loop  
    arrList = convertArrayToArrayList(arr); 
    System.out.println(arrList);
    
    frequency(0);
    min();
    max();
    average();
    haveCertainProperty();
   // reverse();
    //total();
   // mode();        //SHOULD print before editting and after
    //check the teacher site for data types
    //swapper();
    //checkAll();
    shiftRight();
   
}

    public static ArrayList<Integer> convertArrayToArrayList(int[] array){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i=0;i<array.length;i++){
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    public static void frequency( int f ){
        int freq = 0;
        for (int i : arrList){
            if (i==f){
                freq++;
            }
        }
        System.out.format("There are %d %ds",freq,f);
    }

    public static void min(){
        int min=Integer.MAX_VALUE;
        for (int i:arrList){
            if (i<min){
                min = i;
            }
        }
        System.out.format("\nThe minimum value is %d",min);
    }

    public static void max(){
        int max=Integer.MIN_VALUE;
        for (int i:arrList){
            if (i>max){
                max = i;
            }
        }
        System.out.format("\nThe maximum value is %d",max);
    }
    public static void average(){
        double avg = 0;
        for (int i: arrList){
            avg += i;
        }
        System.out.format("\nThe average is %2.3f",avg/arrList.size());
    }
    public static void haveCertainProperty(){
        Scanner in = new Scanner(System.in);
        boolean hasValue = false;
        int find;
        int i = 0;
        find = in.nextInt();
        System.out.println("Enter a number to find:  ");
        find = 0;
        while (!hasValue && (i<arrList.size())){
            if (arrList.get(i)==find){
                hasValue=true;
            }
            i++;
        }
        System.out.format("\nThere" + (hasValue ? "is" :"is not" )+ "a %d in the arraylist",find);
        in.close();
    }
    public static void total(){
        int total = 0;
        for (int i : arrList){
            total+=i;
        }
        System.out.format("\nThe total amount is %d",total);
    }
    public static void reverse(){
        for(int i = 0; i<arrList.size()/2;i++){
            int tmp=arrList.get(arrList.size()-1);
            //setting the first index to the last index
            arrList.set(i,arrList.get(arrList.size()-i-1));
            //setting the last index to the first index
            arrList.set(arrList.size()-i-1,tmp);
            System.out.println("\n"+arrList);
        }
    }

    public static void swapper(){
        System.out.println(arrList);
        for(int i = 0; i < arrList.size();i+=2){
            int swap = arrList.get(i);
            arrList.set(i,arrList.get(i+1));
            arrList.set(i+1,swap);
        }
        System.out.println(arrList);
    }
    public static void shiftRight(){
        arrList.add(0,arrList.get(arrList.size()-1));
        arrList.remove(arrList.size()-1);
        System.out.println(arrList);
        /*
        for (int i=arrList.size();i>1;i--){
            int shiftVal=arrList.get(i-2);
        */
    }
    public static void checkAll(int find){
        //check if all have a cartain property
        boolean hasValueSame = true;
        int i = 0;
        while (hasValueSame == true && i < arrList.size()){
            if (arrList.get(i) != find){
                hasValueSame = false;
            }          
        }
        System.out.format("\nThe values " + (hasValueSame ? "are" :"are not" )+ "all %d",find);
    }

    public static void mode(){
        int modeValue = 0;
        int mode = 0;
        ArrayList<Integer> modeCount = new ArrayList<Integer>();
        for (int a : arrList)
            modeCount.add(0);
        for (int a : arrList){
            modeCount.set( a , ( modeCount.get(a) + 1 ));
            if (modeCount.get(a) >modeValue){
                modeValue = modeCount.get(a);
                mode = a;
            }
        }
    }
}

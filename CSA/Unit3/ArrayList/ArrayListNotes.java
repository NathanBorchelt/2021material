import java.util.ArrayList;
public class ArrayListNotes {
    public static void main(String[] args) {
        System.out.println("Hello There");
        /*
        Data Type:
            string array char tuple
            int long short float double
            booleans

        ArrayList is a class due to capitalization
        List is a class as well
        */

        //to delcare datatype:

        //declare <datatype> name = new Constructor<data type>;
        ArrayList           newList1 = new ArrayList(); 
        ArrayList <String>  newList  = new ArrayList<String>();
        ArrayList           newList2 = new ArrayList();//initalizes at length of 0

        //add items
        newList2.add(1);//add is a meathod of the ArrayList class, actslike python's append()
        newList2.add(5);
        newList2.add(3);
        newList2.add(8);
        System.out.println(newList2);
        System.out.println("The size of the list "  + newList2.size());
        //Array.lenth = lenth was a parameter, now it is a function
        for (int i = 0; i < newList2.size();i++){
            System.out.println(newList2.get(i));
        }
        for (int i = 0; i < newList2.size();i++){
            int newVal = (int)(newList2.get(i));
            newVal += 2;
            newList2.set(i, newVal); //setter need index and new value
        }
        newList2.add(11);
        newList2.add(4);
        //to remove double diget items

        //remove effects the array list size
        for (int i =newList2.size()-1;i >=0;i--){
            if ((int)newList2.get(i) > 9 && (int)newList2.get(i) < 100){
                newList2.remove(i);
            }
            System.out.println(newList2);
        }

    }
}

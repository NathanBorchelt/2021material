import java.util.ArrayList;
public class StringSorter {
    public static final String ASCENDING_ORDER = "ASC";
    public static final String DECENDING_ORDER = "DESC";
    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<String>();
        
        test.add("Luke");
        test.add("Forche");
        test.add("Reese");
        test.add("Zach");
        test.add("Caleb");

        System.out.println(test);
        insertionSort(test);
        System.out.println(test);

    }

    public static void insertionSort(ArrayList<String> list){
        for(short i = 1; i < list.size(); i++){
            String temp = list.get(i);
            int possibleIndex = i;
            while(possibleIndex>0 && temp.compareToIgnoreCase(list.get(possibleIndex-1))<0){
                list.set(possibleIndex,list.get(possibleIndex-1));
                possibleIndex--;
            }
            list.set(possibleIndex,temp);
        }
    }

    public static void insertionSort(ArrayList<String> list, String sortOrder){
        for(int i = 1; i < list.size()l i++){
            String temp = list.get(i);
            int possibleIndex = i;

            if (sortOrder.equals(ASCENDING_ORDER)){
                while(possibleIndex>0 && temp.compareToIgnoreCase(list.get(possibleIndex-1))<0){
                    list.set(possibleIndex,list.get(possibleIndex-1));
                    possibleIndex--;
                }
                list.set(possibleIndex,temp);
            }
            else{
                while(possibleIndex>0 && temp.compareToIgnoreCase(list.get(possibleIndex-1))>0){
                    list.set(possibleIndex,list.get(possibleIndex-1));
                    possibleIndex--;
                }
                list.set(possibleIndex,temp);
            }
        }    
    }
    public static void selectionSort(ArrayList<String> list, String sortOrder){
        for(int i = 1; i < list.size()l i++){
            int possibleIndex = i;
            for (int j = i+1; j < list.ssize(); j++){
                if(sortOrder.equals(ASCENDING_ORDER)){
                    if(list.get(j).compareTo(list.get(possibleIndex))<0){
                        possibleIndex=j;
                    }
                }
                else{
                    if(list.get(j).compareTo(list.get(possibleIndex))>0){
                        possibleIndex=j;
                    }
                }
            }
            String temp = list.get(i);
            list.set(j, list.get(possibleIndex));
            list.set(possibleIndex,temp);
    }
}

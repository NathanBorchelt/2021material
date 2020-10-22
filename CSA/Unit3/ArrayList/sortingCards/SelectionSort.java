import java.util.ArrayList;

public class SelectionSort{
    public static void main(String[] args) {
        ArrayList<Integer> s = new ArrayList<Integer>();
        s.add(5);
        s.add(4);
        s.add(8);
        s.add(9);
        s.add(2);
        s.add(3);
        s.add(1);
        s.add(4);
        selSort(s);
        insSort(s);
    }

    public static void selSort(ArrayList<Integer> s){        
        
        // Use a for loop to iterate through all the values in the list. This loop
        // will keep track of which value will be swapped with the lowest value.
        for (int i = 0; i < s.size() - 1; i++)
        {
        // Create a variable to hold the index of the smallest number.
        int minIndex = i;
        
        // Use a sequential search to find the smallest number
        for (int searchIndex = i + 1; searchIndex < s.size(); searchIndex++)
        {
            // If the value at minIndex is larger, then set minIndex to the index of the 
            // smaller value.
            if(s.get(searchIndex) < s.get(minIndex))
            minIndex = searchIndex;
        }
        
        // Create a variable to temporarily hold the current value at index i
        // and one to hold the smallest value at index minIndex
        int temp = s.get(i);
        int smallest = s.get(minIndex);
        
        // Swap the values
        s.set(i, smallest);
        s.set(minIndex, temp);
        }
        
        // Print all the elements of the list
        System.out.print(s);
    }

    public static void insSort(ArrayList<Integer> s){
        // Begin by creating a for loop to iterate through the list, begining
        // at the second element. 
        for (int i = 1; i < s.size(); i++) 
        {
        
        // Create an integer value for valueToSort that is the first unsorted element.
        // Remember, for the insertion sort, the first element of the array is considered sorted
        int valueToSort = s.get(i); 
        
        // Create an int variable to hold the last element of the sorted portion of the array. 
        // It's right before the first element of the unsorted portion of the array
        int prevIndex = i - 1; 
        
        // Use a loop to move backwards through the sorted elements of the array using prevIndex
        // in the condition of the loop
        while (prevIndex >= 0)  
        {
            // Create an int variable to hold the sorted value. 
            int sortedValue = s.get(prevIndex);
            
            // Check if the sorted value is greater than the value to sort
            if (sortedValue > valueToSort) 
            {
            // If so, switch the values at indexes prevIndex and prevIndex + 1
            s.set(prevIndex + 1, sortedValue);
            s.set(prevIndex, valueToSort); 
            }
            prevIndex--;
        }
        }
        
        // Print all the elements of the list
        System.out.print(s);
    }
}
package com.example.triptracker2021;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSorter {

    public static final String ASCENDING_ORDER = "ASC";
    public static final String DESCENDING_ORDER = "DSC";

    //insertion method
    public static void insertionSort(List<Trip> list, String sortOrder){
        Trip temp;
        int possibleIndex;
        for(short j = 1; j<list.size();++j){
            temp = list.get(j);
            possibleIndex = j;

            if(sortOrder.equals(ASCENDING_ORDER)) {
                while (possibleIndex > 0 && temp.compareTo(list.get(possibleIndex - 1)) < 0) {
                    list.set(possibleIndex, list.get(possibleIndex - 1));
                    --possibleIndex;
                }

            }
            else {
                while (possibleIndex > 0 && temp.compareTo(list.get(possibleIndex - 1)) > 0) {
                    list.set(possibleIndex, list.get(possibleIndex + 1));
                    --possibleIndex;
                }
            }
            list.set(possibleIndex, temp);
        }

    }

    //selection method
    //https://www.geeksforgeeks.org/selection-sort/
    public static void selectionSort(List<Trip> list, String sortOrder){
        List<Trip> sorted = new ArrayList<>();
        int sortedReachingLen = list.size();
        if(sortOrder.equals(ASCENDING_ORDER)){
            for (int i = 0; i < sortedReachingLen-1; i++){
                // Find the minimum element in unsorted array
                int min_idx = i;
                for (int j = i+1; j < sortedReachingLen; j++)
                    if (list.get(j).compareTo(list.get(min_idx))<0)
                        min_idx = j;

                // Swap the found minimum element with the first
                // element
                Trip temp = list.get(min_idx);
                list.set(min_idx,list.get(i));
                list.set(i,temp);
            }
        } else{
            for (int i = 0; i < sortedReachingLen-1; i++){
                // Find the minimum element in unsorted array
                int max_idx = i;
                for (int j = i+1; j < sortedReachingLen; j++)
                    if (list.get(j).compareTo(list.get(max_idx))>0)
                        max_idx = j;

                // Swap the found minimum element with the first
                // element
                Trip temp = list.get(max_idx);
                list.set(max_idx,list.get(i));
                list.set(i,temp);
            }
        }
    }


    //mergeSort
    public static void mergeSort(ArrayList<Trip> list, String sortOrder) {
        int n = list.size();
        ArrayList<Trip> tempList = new ArrayList<>();
        for (int i=0; i<list.size(); i++)
            tempList.add(null);
        mergeSortHelper(list, 0, n-1, tempList, sortOrder);
    }
    private static void mergeSortHelper(List<Trip> list, int from, int to, ArrayList<Trip> tempList, String sortOrder){
        if (from < to)
        {
            int middle = (from + to) / 2;
            mergeSortHelper(list, from, middle, tempList, sortOrder);
            mergeSortHelper(list, middle + 1, to, tempList, sortOrder);
            merge(list, from, middle, to, tempList, sortOrder);
        }
    }
    private static void merge(List<Trip> list, int from, int mid, int to, ArrayList<Trip> tempList, String sortOrder){
        int i = from;
        int j = mid + 1;
        int k = from;
        while (i <= mid && j <= to){
            if (sortOrder.equals(ASCENDING_ORDER)) {
                if ((list.get(i)).compareTo(list.get(j)) < 0) {
                    tempList.set(k, list.get(i));
                    i++;
                } else {
                    tempList.set(k, list.get(j));
                    j++;
                }
            } else {
                if ((list.get(i)).compareTo(list.get(j)) > 0) {
                    tempList.set(k, list.get(i));
                    i++;
                } else {
                    tempList.set(k, list.get(j));
                    j++;
                }
            }
            k++;
        }
        while (i <= mid)
        {
            tempList.set(k, list.get(i));
            i++;
            k++;
        }
        while (j <= to) {
            tempList.set(k, list.get(j));
            j++;
            k++;}
        for (k = from; k <= to; k++){
            list.set(k, tempList.get(k)); }
    }
}

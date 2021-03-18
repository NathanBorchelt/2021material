package com.example.triptracker2021;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSorter {
    //insertion method
    public static void insertionSort(List<Trip> list, String sortOrder){
        Trip temp;
        int possibleIndex;
        for(short j = 0; j<list.size();++j){
            temp = list.get(j);
            possibleIndex = j;

            while(possibleIndex > 0 && temp.compareTo(list.get(possibleIndex-1))<0){

            }
        }

    }

    //selection method

    //mergeSort
}

package com.example.krustykrabsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class SecondActivity extends Activity {
    private NumberPicker mMenuScroller;
    private Button mAddSelection;
    public ArrayList<Item> selectedItems;
    @Override
    protected void onCreate(Bundle savedInstamceState){
        super.onCreate(savedInstamceState);
        setContentView(R.layout.activity_second);
        mMenuScroller = (NumberPicker) findViewById(R.id.menuItems);
        mAddSelection = (Button) findViewById(R.id.addItem);
        String[] menuItems = new String[]{"Krabby Patty", "Double Krabby Patty", "Triple Krabby  Patty","Coral Bits","Kelp Rings", "Salty Sea Dog","FootLong","Sailors Suprise","Golden Loaf","Kelp Shake","Seafoam Soda"};
        boolean[] sizeItems = new boolean[]{false,          false,                  false,                  true,       false,          false,      false,      false,              false,      false,          true};
        boolean[] mealOption = new boolean[]{true,          true,                   true,                   false,      false,          false,          false,  false,              false,      false,          false};

        //https://stackoverflow.com/questions/8227073/using-numberpicker-widget-with-strings
        mMenuScroller.setMinValue(0);
        mMenuScroller.setMaxValue(menuItems.length-1);
        mMenuScroller.setDisplayedValues(menuItems);
        mMenuScroller.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
    public void addItemsToSelection(View v) {
        int addItemIndex;
        mAddSelection.setOnClickListener(View.OnClickListener());
        addItemIndex = mMenuScroller.getValue();

    }
}

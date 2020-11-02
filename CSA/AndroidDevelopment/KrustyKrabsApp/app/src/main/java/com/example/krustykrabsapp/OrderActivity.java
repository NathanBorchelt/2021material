package com.example.krustykrabsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class OrderActivity extends Activity {
    private NumberPicker mMenuScroller;
    private Button mAddSelection;
    private Button mShowOptions;
    private LinearLayout mSandwichOptions;
    private LinearLayout mSizeOptions;
    private CheckBox mCheeseCBox;
    private CheckBox mMealCBox;
    private RadioGroup mSizeOptionsRadio;


    public ArrayList<Item> selectedItems;
    public String[] menuItems = new String[]{"Krabby Patty", "Double Krabby Patty", "Triple Krabby  Patty","Coral Bits","Kelp Rings", "Salty Sea Dog","FootLong","Sailors Suprise","Golden Loaf","Kelp Shake","Seafoam Soda"};
    public boolean[] sizeItems = new boolean[]{false,          false,                  false,                  true,       false,          false,      false,      false,              false,      false,          true};
    public boolean[] mealOption = new boolean[]{true,          true,                   true,                   false,      false,          false,          false,  false,              false,      false,          false};
    public double[] itemPrices = new double[]  {1.25,           2.00,                   3.00,                   1.00,       1.50,           1.25,       2.00,       3.00,               2.00,           2.00,           1.00};
    public int addItemIndex;
    @Override
    protected void onCreate(Bundle savedInstamceState){
        super.onCreate(savedInstamceState);
        setContentView(R.layout.activity_second);
        mMenuScroller = (NumberPicker) findViewById(R.id.menuItems);
        mAddSelection = (Button) findViewById(R.id.addItem);

        //https://stackoverflow.com/questions/8227073/using-numberpicker-widget-with-strings
        mMenuScroller.setMinValue(0);
        mMenuScroller.setMaxValue(menuItems.length-1);
        mMenuScroller.setDisplayedValues(menuItems);
        mMenuScroller.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
    public void getOptions(View v) {
        addItemIndex = mMenuScroller.getValue();

        if(sizeItems[addItemIndex]){
            mSizeOptions.setVisibility(View.VISIBLE);
            mSandwichOptions.setVisibility(View.INVISIBLE);
        }
        if(mealOption[addItemIndex]){
            mSandwichOptions.setVisibility(View.VISIBLE);
            mSizeOptions.setVisibility(View.INVISIBLE);
        }

    }

    public void addItem(View v){
        Item newInput = new Item(menuItems[addItemIndex],itemPrices[addItemIndex]);
        if(mealOption[addItemIndex]){
            newInput.setMeal(mMealCBox.isChecked());
            newInput.upcharge(2.00);
            newInput.setSauce(mCheeseCBox.isChecked());
            newInput.upcharge(.25);
        }
        if(sizeItems[addItemIndex] && mSizeOptionsRadio.indexOfChild(v)!=-1){
            newInput.setSize(mSizeOptionsRadio.indexOfChild(v));
            if(newInput.getSize()==1){
                newInput.upcharge(.25);
            }
            else if(newInput.getSize()==2){
                newInput.upcharge(.50);
            }
            selectedItems.add(newInput);
            System.out.println("Item Added");
        }
    }
}

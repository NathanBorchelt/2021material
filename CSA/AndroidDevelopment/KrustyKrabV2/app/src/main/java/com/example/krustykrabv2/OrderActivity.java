package com.example.krustykrabv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class OrderActivity extends Activity {
    private NumberPicker mMenuScroller;
    private Button mAddSelection;
    private LinearLayout mSandwichOptions;
    private LinearLayout mSizeOptions;
    private CheckBox mCheeseCBox;
    private CheckBox mMealCBox;
    private RadioGroup mSizeOptionsRadio;
    private RadioButton mSizeButton;
    private Button mCheckOut;


    public ArrayList<MenuItem> selectedItems = new ArrayList<>();
    public int wheelIndex;
    public String[] menuItems = new String[]{"Krabby Patty", "Double Krabby Patty", "Triple Krabby  Patty", "Coral Bits", "Kelp Rings", "Salty Sea Dog", "FootLong", "Sailors Suprise", "Golden Loaf", "Kelp Shake", "Seafoam Soda"};
    public boolean[] sizeItems = new boolean[]{false, false, false, true, false, false, false, false, false, false, true};
    public boolean[] mealOption = new boolean[]{true, true, true, false, false, false, false, false, false, false, false};
    public MenuItem[] menuObjs = new MenuItem[]{new KrabyPatty("Krabby Patty", 1.25), new KrabyPatty("Double Krabby Patty", 2.), new KrabyPatty("Triple Krabby Patty", 3.),
            new CorralBits(), new MiscItem("Kelp Rigs", 1.50), new MiscItem("Salty Sea Dog", 1.25), new MiscItem("FootLong", 2.), new MiscItem("Sailors Surprise", 3.),
            new MiscItem("Gold Loaf", 2.), new MiscItem("Kelp Shake", 2.), new Soda(1.)};

    @Override
    protected void onCreate(Bundle savedInstamceState) {
        super.onCreate(savedInstamceState);
        setContentView(R.layout.activity_order);
        mMenuScroller = (NumberPicker) findViewById(R.id.menuItems);
        mAddSelection = (Button) findViewById(R.id.addItem);
        mSizeOptionsRadio = (RadioGroup) findViewById(R.id.sizeOptionsRadioGroup);
        mSandwichOptions = (LinearLayout) findViewById(R.id.sandwichOptionsLayout);
        mSizeOptions = (LinearLayout) findViewById(R.id.sizeOptionLayout);
        mMealCBox = (CheckBox) findViewById(R.id.meal);
        mCheeseCBox = (CheckBox) findViewById(R.id.cheese);
        mCheckOut = (Button) findViewById(R.id.checkout);

        //https://stackoverflow.com/questions/8227073/using-numberpicker-widget-with-strings
        mMenuScroller.setMinValue(0);
        mMenuScroller.setMaxValue(menuItems.length - 1);
        mMenuScroller.setDisplayedValues(menuItems);
        mMenuScroller.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        mMenuScroller.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                wheelIndex = mMenuScroller.getValue();
                if (sizeItems[wheelIndex]) {
                    mSizeOptions.setVisibility(View.VISIBLE);
                    mSandwichOptions.setVisibility(View.GONE);
                }
                else if (mealOption[wheelIndex]) {
                    mSizeOptions.setVisibility(View.GONE);
                    mSandwichOptions.setVisibility(View.VISIBLE);
                }
                else{
                    mSizeOptions.setVisibility(View.GONE);
                    mSandwichOptions.setVisibility(View.GONE);
                }
            }
        });
        mAddSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mealOption[wheelIndex]) {
                    if (mCheeseCBox.isChecked()) {
                        if (mMealCBox.isChecked()) {
                            selectedItems.add(new KrabyPatty((KrabyPatty) menuObjs[wheelIndex], true, .25, 2.));
                        } else {
                            selectedItems.add(new KrabyPatty((KrabyPatty) menuObjs[wheelIndex], true, .25, 0.));
                        }
                    } else {
                        if (mMealCBox.isChecked()) {
                            selectedItems.add(new KrabyPatty((KrabyPatty) menuObjs[wheelIndex], false, 0., 2.));
                        } else {
                            selectedItems.add(menuObjs[wheelIndex]);
                        }
                    }
                }
                else if(sizeItems[wheelIndex]){
                    SizeableItem itemWithSizeOption = new SizeableItem(menuObjs[wheelIndex].getItemName(),menuObjs[wheelIndex].getItemCost());
                    mSizeOptionsRadio = (RadioGroup) findViewById(R.id.sizeOptionsRadioGroup);
                    //https://stackoverflow.com/questions/6440259/how-to-get-the-selected-index-of-a-radiogroup-in-android
                    int index = mSizeOptionsRadio.indexOfChild(findViewById(mSizeOptionsRadio.getCheckedRadioButtonId()));
                    if (index==0){selectedItems.add(new SizeableItem(itemWithSizeOption.getItemName(),itemWithSizeOption.getItemCost(),"Small",0.));}
                    else if (index==1){selectedItems.add(new SizeableItem(itemWithSizeOption.getItemName(),itemWithSizeOption.getItemCost(),"Medium",0.25));}
                    else if (index==2){selectedItems.add(new SizeableItem(itemWithSizeOption.getItemName(),itemWithSizeOption.getItemCost(),"Large",0.5));}
                }
                else{
                    selectedItems.add(menuObjs[wheelIndex]);
                }
            }
        });
        mCheckOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, CheckOutActivity.class);
                intent.putExtra("SELECTED_ITEMS",  selectedItems);
                startActivity(intent);

            }
        });

    }
}

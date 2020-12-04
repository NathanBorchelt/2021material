package com.example.krustykrabv2;

import android.app.Activity;
import android.os.Bundle;
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


    public ArrayList<MenuItem> selectedItems;
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
                if (mealOption[wheelIndex]) {
                    mSizeOptions.setVisibility(View.GONE);
                    mSandwichOptions.setVisibility(View.VISIBLE);
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
                } else (sizeItems[wheelIndex]){
                    if(menuObjs.equals(new CorralBits())){
                        selectedItems.add(new CorralBits((CorralBits) menuObjs[wheelIndex],mSizeButton = (RadioButton) findViewById(mSizeOptionsRadio.getCheckedRadioButtonId()),));
                    }
                }
            }
        });

    }
}

package com.example.borcheltunitconverter.ui.home;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.borcheltunitconverter.R;

import java.lang.Math;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private EditText mTempInput;
    private NumberPicker mStartUnitPicker;
    private NumberPicker mEndUnitPicker;
    private TextView mOutputText;
    private double kelvinMiddleMan = 0;
    private double outputNum = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        mTempInput = (EditText)root.findViewById(R.id.tempInput);
        mStartUnitPicker = (NumberPicker)root.findViewById(R.id.sUnitPicker);
        mEndUnitPicker = (NumberPicker)root.findViewById(R.id.eUnitPicker);
        mOutputText = (TextView)root.findViewById(R.id.outputText);

        super.onCreate(savedInstanceState);

        final String[] displayArrays = {"Fahrenheit (°F)","Celsius (°C)","Kelvin (°K)","Rankine (°Ra)","Delisle (°D)"};
        final String[] unitSymbols = {"F","C","K","R","D"};
        mStartUnitPicker.setMaxValue(displayArrays.length-1);
        mStartUnitPicker.setMinValue(0);
        mEndUnitPicker.setMaxValue(displayArrays.length-1);
        mEndUnitPicker.setMinValue(0);
        mStartUnitPicker.setDisplayedValues(displayArrays);
        mEndUnitPicker.setDisplayedValues(displayArrays);

        mTempInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                int sTempIndex = mStartUnitPicker.getValue();
                int eTempIndex = mEndUnitPicker.getValue();

                if(!mTempInput.getText().toString().equals("")) {
                    if (unitSymbols[sTempIndex].equalsIgnoreCase("F"))
                        kelvinMiddleMan = ((Double.valueOf(mTempInput.getText().toString()) - 32) * 5 / 9 + 273.15);
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("C"))
                        kelvinMiddleMan = ((Double.valueOf(mTempInput.getText().toString()) + 273.15));
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("K"))
                        kelvinMiddleMan = Double.valueOf((mTempInput.getText().toString()));
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("R"))
                        kelvinMiddleMan = (Double.valueOf((mTempInput.getText().toString())) * 5 / 9);
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("D"))
                        kelvinMiddleMan = (373.15 - (Double.valueOf((mTempInput.getText().toString())) * 2 / 3));
                }
                else kelvinMiddleMan = 0;


                if(unitSymbols[eTempIndex].equalsIgnoreCase("F")) outputNum = (kelvinMiddleMan-273.15)*9/5+32;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("C")) outputNum = kelvinMiddleMan-273.15;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("K")) outputNum = kelvinMiddleMan;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("R")) outputNum = kelvinMiddleMan*9/5;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("D")) outputNum = (373.15-kelvinMiddleMan)*3/2;

                mOutputText.setText(mTempInput.getText().toString()+" "+displayArrays[sTempIndex] + " is equal to\n"+String.valueOf(Math.round(outputNum*100)/100.)+" "+ displayArrays[eTempIndex]);
                return false;
            }
        });

        mStartUnitPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int sTempIndex = mStartUnitPicker.getValue();
                int eTempIndex = mEndUnitPicker.getValue();

                if(!mTempInput.getText().toString().equals("")) {
                    if (unitSymbols[sTempIndex].equalsIgnoreCase("F"))
                        kelvinMiddleMan = ((Double.valueOf(mTempInput.getText().toString()) - 32) * 5 / 9 + 273.15);
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("C"))
                        kelvinMiddleMan = ((Double.valueOf(mTempInput.getText().toString()) + 273.15));
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("K"))
                        kelvinMiddleMan = Double.valueOf((mTempInput.getText().toString()));
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("R"))
                        kelvinMiddleMan = (Double.valueOf((mTempInput.getText().toString())) * 5 / 9);
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("D"))
                        kelvinMiddleMan = (373.15 - (Double.valueOf((mTempInput.getText().toString())) * 2 / 3));
                }
                else kelvinMiddleMan = 0;


                if(unitSymbols[eTempIndex].equalsIgnoreCase("F")) outputNum = (kelvinMiddleMan-273.15)*9/5+32;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("C")) outputNum = kelvinMiddleMan-273.15;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("K")) outputNum = kelvinMiddleMan;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("R")) outputNum = kelvinMiddleMan*9/5;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("D")) outputNum = (373.15-kelvinMiddleMan)*3/2;

                mOutputText.setText(mTempInput.getText().toString()+" "+displayArrays[sTempIndex] + " is equal to\n"+String.valueOf(Math.round(outputNum*100)/100.)+" "+ displayArrays[eTempIndex]);            }
        });
        mEndUnitPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int sTempIndex = mStartUnitPicker.getValue();
                int eTempIndex = mEndUnitPicker.getValue();

                if(!mTempInput.getText().toString().equals("")) {
                    if (unitSymbols[sTempIndex].equalsIgnoreCase("F"))
                        kelvinMiddleMan = ((Double.valueOf(mTempInput.getText().toString()) - 32) * 5 / 9 + 273.15);
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("C"))
                        kelvinMiddleMan = ((Double.valueOf(mTempInput.getText().toString()) + 273.15));
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("K"))
                        kelvinMiddleMan = Double.valueOf((mTempInput.getText().toString()));
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("R"))
                        kelvinMiddleMan = (Double.valueOf((mTempInput.getText().toString())) * 5 / 9);
                    else if (unitSymbols[sTempIndex].equalsIgnoreCase("D"))
                        kelvinMiddleMan = (373.15 - (Double.valueOf((mTempInput.getText().toString())) * 2 / 3));
                }
                else kelvinMiddleMan = 0;


                if(unitSymbols[eTempIndex].equalsIgnoreCase("F")) outputNum = (kelvinMiddleMan-273.15)*9/5+32;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("C")) outputNum = kelvinMiddleMan-273.15;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("K")) outputNum = kelvinMiddleMan;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("R")) outputNum = kelvinMiddleMan*9/5;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("D")) outputNum = (373.15-kelvinMiddleMan)*3/2;

                mOutputText.setText(mTempInput.getText().toString()+" "+displayArrays[sTempIndex] + " is equal to\n"+String.valueOf(Math.round(outputNum*100)/100.)+" "+ displayArrays[eTempIndex]);            }
        });
        return root;
    }
}
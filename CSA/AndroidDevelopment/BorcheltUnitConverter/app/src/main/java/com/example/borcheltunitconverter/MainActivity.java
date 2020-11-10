package com.example.borcheltunitconverter;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NumberPicker mStartUnitPicker;
    private NumberPicker mEndUnitPicker;
    private EditText mTempInput;
    private TextView mOutputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mTempInput = (EditText)findViewById(R.id.tempInput);
        mStartUnitPicker = (NumberPicker)findViewById(R.id.sUnitPicker);
        mEndUnitPicker = (NumberPicker)findViewById(R.id.eUnitPicker);
        mOutputText = (TextView)findViewById(R.id.outputText);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        String[] displayArrays = {"Fahrenheit (°F)","Celsius (°C)","Kelvin (°K)","Rankine (°Ra)","Delisle (°D)"};
        final String[] unitSymbols = {"F","C","K","R","D"};
        mStartUnitPicker.setMaxValue(displayArrays.length-1);
        mStartUnitPicker.setMinValue(0);
        mEndUnitPicker.setMaxValue(displayArrays.length-1);
        mEndUnitPicker.setMinValue(0);
        mStartUnitPicker.setDisplayedValues(displayArrays);
        mEndUnitPicker.setDisplayedValues(displayArrays);

        mTempInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                double  kelvinMiddleMan = 0;
                int sTempIndex = mStartUnitPicker.getValue();
                int eTempIndex = mEndUnitPicker.getValue();
                if(unitSymbols[sTempIndex].equalsIgnoreCase("F")) kelvinMiddleMan = ((Double.valueOf(mTempInput.getText().toString())-32)*5/9+273.15);
                else if(unitSymbols[sTempIndex].equalsIgnoreCase("C")) kelvinMiddleMan = (Double.valueOf(userInput[0])+273.15);
                else if(unitSymbols[sTempIndex].equalsIgnoreCase("K")) kelvinMiddleMan = Double.valueOf(userInput[0]);
                else if(unitSymbols[sTempIndex].equalsIgnoreCase("R")) kelvinMiddleMan = (Double.valueOf(userInput[0])*5/9);
                else if(unitSymbols[sTempIndex].equalsIgnoreCase("D")) kelvinMiddleMan = (373.15-(Double.valueOf(userInput[0])*2/3));


                if(unitSymbols[eTempIndex].equalsIgnoreCase("F")) outputNum = (kelvinMiddleMan-273.15)*9/5+32;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("C")) outputNum = kelvinMiddleMan-273.15;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("K")) outputNum = kelvinMiddleMan;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("D")) outputNum = kelvinMiddleMan*9/5;
                else if(unitSymbols[eTempIndex].equalsIgnoreCase("R")) outputNum = (373.15-kelvinMiddleMan)*3/2;

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
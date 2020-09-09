package com.example.scifi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mFirstName; //instatiating the object, it acts as a global
    private TextView mLastName;  //creating a field variable or a global variable
    private TextView mCity;
    private TextView mSchool;
    private TextView mBrother;
    private TextView mSister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstName = (TextView) findViewById(R.id.txtFirstName);
        mLastName = (TextView) findViewById(R.id.txtLastName);
        mCity = (TextView) findViewById(R.id.txtCity);
        mSchool = (TextView) findViewById(R.id.txtSchool);
        mBrother = (TextView) findViewById(R.id.txtBrother);
        mSister = (TextView) findViewById(R.id.txtSister);


    }
    public void generateName(View v){
        Random random = new Random();
        String fName = mFirstName.getText().toString();// getText() gets the info
        String lName = mLastName.getText().toString();
        String city = mCity.getText().toString();
        String school = mSchool.getText().toString();
        String brother = mBrother.getText().toString();
        String sister = mSister.getText().toString();

        String sciFName = fName.substring(0,(int) (random.nextDouble()*fName.length())) + lName.substring((int) (random.nextDouble()*lName.length()));
        String sciLName = city.substring(0, (int) (random.nextDouble()*city.length())) + school.substring((int) (random.nextDouble()*school.length()));
        String sciPName = brother.substring(0,(int) (random.nextDouble()*brother.length())) + sister.substring((int) (random.nextDouble()*random.nextInt(sister.length())));

        TextView output = (TextView) findViewById(R.id.txtOutput);
        output.setText("");
        output.append("I am " + sciFName + " " + sciLName + " of the planet " + sciPName);//append is the same as println
        output.append("Welcome to the Party!");
    }
}
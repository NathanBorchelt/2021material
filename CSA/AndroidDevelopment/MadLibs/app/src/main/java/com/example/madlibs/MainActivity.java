package com.example.madlibs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mCompany;
    private TextView mPlrlNoun;
    private TextView mNumber;
    private TextView mVerbing;
    private TextView mNoun1;
    private TextView mMaleName;
    private TextView mColor;
    private TextView mNoun2;
    private TextView mPrefix;
    private TextView mPstVerb;
    private TextView mAdjective;
    private TextView mRoom;
    private TextView mOutput;
    private LinearLayout mVerticalLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompany = (TextView) findViewById(R.id.txtCompany);
        mPlrlNoun = (TextView) findViewById(R.id.txtPlrlNoun);
        mNumber = (TextView) findViewById(R.id.txtNumber);
        mVerbing = (TextView) findViewById(R.id.txtVerbing);
        mNoun1 = (TextView) findViewById(R.id.txtNoun1);
        mMaleName = (TextView) findViewById(R.id.txtMaleName);
        mColor = (TextView) findViewById(R.id.txtColor);
        mNoun2 = (TextView) findViewById(R.id.txtNoun2);
        mPrefix = (TextView) findViewById(R.id.txtPrefix);
        mPstVerb = (TextView) findViewById(R.id.txtPstVerb);
        mAdjective = (TextView) findViewById(R.id.txtAdjective);
        mRoom = (TextView) findViewById(R.id.txtBuilding);
        mVerticalLayout = (LinearLayout) findViewById(R.id.verticalLayout);
        mOutput = (TextView) findViewById(R.id.outputText);

    }
    public void printStory(View v){
        String company = mCompany.getText().toString();
        String pluralNoun = mPlrlNoun.getText().toString();
        String number = mNumber.getText().toString();
        String verbIng = mVerbing.getText().toString();
        String noun1 = mNoun1.getText().toString();
        String maleName = mMaleName.getText().toString();
        String color = mColor.getText().toString();
        String noun2 = mNoun2.getText().toString();
        String prefix = mPrefix.getText().toString();
        String pstVerb = mPstVerb.getText().toString();
        String adjective = mAdjective.getText().toString();
        String room = mRoom.getText().toString();

        mVerticalLayout.setVisibility(View.GONE);

        mOutput.setVisibility(View.VISIBLE);
        mOutput.setText("The Office at " + company + "\nAt "+ company + ", everyone sits in "+pluralNoun+"that are spaced "+number+" feet away from each other. This makes "+verbIng+" on the "+noun1+" a public ");
        mOutput.append("activity, because everyone around can hear what you are saying. It's a wonder that people get any work done with all the noise.\n\nThere is an emplyee at "+company+" named ");
        mOutput.append(maleName + ", he used to have a "+color+" "+ noun2+"on his desk, but someone "+prefix+pstVerb+" it. After the loss of his "+noun2+". "+maleName+" started muttering to himself ");
        mOutput.append("a lot. He looked really "+adjective+". I guess he was annoying people, because the company moved his desk into the "+room);


/*
System.out.printf("\n\nThe Office at %s\n",company);//Used printf so i could easily add in the variables, uned the \n so it is not one massive line
System.out.printf("At %s, everyone sits in %s that\n",company,pluralNoun);
System.out.printf("are spaced %s feet away from each other. This\n",number);
System.out.printf("makes %s on the %s a public\n",nounIng,regNoun1);
System.out.println("activity, because everyone around can hear\nwhat you are saying. It's a wonder that people\nget any work done with all the noise.");
System.out.printf("\n\nThere is an emplyee at %s named\n",company);
System.out.printf("%s, he used to have a %s %s on his\n",maleName,color,regNoun2);
System.out.printf("desk, but someone %s%s it. After the loss\n",prefix,pastVerb);
System.out.printf("of his %s. %s started muttering to himself\n",regNoun2,maleName);
System.out.printf("a lot. He looked really %s. I guess he was\n",adjective);
System.out.printf("annoying people, because the company moved\nhis desk into the %s",room);
*/



    }
}
package com.example.krustykrabv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

//multiActivity https://stackoverflow.com/questions/28438460/how-do-i-make-multiple-pages-that-i-can-go-to-in-the-app-with-buttons-and-make-t

public class MainActivity extends Activity {

    //Define your views
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find your views
        mStartButton = (Button) findViewById(R.id.startButton);

        //Assign a listener to your button
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your second activity
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
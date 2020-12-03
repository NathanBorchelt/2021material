package com.example.intentpractice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyBrandSpankinNewActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //runs onCreate method
        super.onCreate(savedInstanceState);
        //what do i open up, the layout below
        setContentView(R.layout.layout_mybrandspankingnewactivity);

        Intent myIntent = getIntent();
        String startValue = myIntent.getStringExtra("Greeting Message");
        mTextView = findViewById(R.id.textView2);
        mTextView.setText(startValue);
    }
}

package com.example.helloworldandroid;
// package is the folder where everything is contained

//normal imports, auto generated
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//when class extends another class, this is called inheritance
public class MainActivity extends AppCompatActivity {

    //if it is a function from AppCompatActivity, you ill need Overide
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sayHello( View v){
        //findViewById will link to XML to java
        EditText nameEnter = (EditText) findViewById(R.id.nameEditView);
        //you need to create an object of the Widget class fo example
        //TextVew and greetingText is a TextView object
        //findViewById() is not a method of TextView, so it must be cast
        TextView greetingText = (TextView) findViewById(R.id.helloText);

        greetingText.setText("Hi " + nameEnter.getEditableText() + ", nice to meet you!");

    }
}
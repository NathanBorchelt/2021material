package com.example.triptracker2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    // m is part of hungarian notation. m states that it is a member of a class
    private EditText mEnterEmail;
    private EditText mEnterPassword;
    private EditText mEnterName;
    private Button mLoginButton;
    // will swap between register and login
    private TextView mSwitchViewText;
    private Button mSignMeUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEnterEmail = (EditText) findViewById(R.id.enterEmail);
        mEnterPassword = (EditText) findViewById(R.id.enterPassword);
        mEnterName = (EditText) findViewById(R.id.enterName);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mSwitchViewText = (TextView) findViewById(R.id.switchViewText);
        SwitchViewOnClickListener signUpTextListener = new SwitchViewOnClickListener();
        mSwitchViewText.setOnClickListener(signUpTextListener);
        mSignMeUp = (Button) findViewById(R.id.signMeUpButton);
        RegisterOnCLickListener registerOnCLickListener = new RegisterOnCLickListener();
        mSignMeUp.setOnClickListener(registerOnCLickListener);
    }

    private class SwitchViewOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(mEnterName.getVisibility()==View.GONE){
                mEnterName.setVisibility(View.VISIBLE);
                mSwitchViewText.setText(R.string.already_registered);
                mLoginButton.setVisibility(View.GONE);
                mSignMeUp.setVisibility(View.VISIBLE);
            }
            else{
                mEnterName.setVisibility(View.GONE);
                mSwitchViewText.setText(R.string.need_to_register);
                mLoginButton.setVisibility(View.VISIBLE);
                mSignMeUp.setVisibility(View.GONE);
            }
            //if nameEditText is gone
            //  i want to set up my register page
            //else
            //  login page
        }
    }
    private class RegisterOnCLickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            char[] specChars = {'!','@','#','$','%','&','*','-','_','~'};
            char[] lowerChars = new char[26];
            char[] upperChars = new char[26];
            char[] numberics = new char[10];

            for(byte i  = 0; i < 10; i++){
                numberics[i] = (char)(i+48);
            }

            for (byte i = 0; i < 26;i++){
                lowerChars[i] = (char)(i+97);
                upperChars[i] = (char)(i+65);
            }

            if(mEnterPassword.getText().toString().length() >=8) {
                for (char spec : specChars) {
                    if (mEnterPassword.getText().toString().contains(String.valueOf(spec))) {
                        for (char number : numberics) {
                            if (mEnterPassword.getText().toString().contains(String.valueOf(number))) {
                                for (char lower : lowerChars) {
                                    if (mEnterPassword.getText().toString().contains(String.valueOf(lower))) {
                                        for (char upper : upperChars) {
                                            if (mEnterPassword.getText().toString().contains(String.valueOf(upper))) {
                                                Toast.makeText(com.example.triptracker2021.LoginActivity.this, "This Is A Valid Password", Toast.LENGTH_LONG);
                                            } else {
                                                badPassword();
                                            }
                                        }
                                    } else {
                                        badPassword();
                                    }
                                }
                            } else {
                                badPassword();
                            }
                        }
                    } else {
                        badPassword();
                    }
                }
            }
            else{
                badPassword();
            }
        }
    }
    public void badPassword(){
        Toast.makeText (com.example.triptracker2021.LoginActivity.this, "This Is NOT A Valid Password", Toast.LENGTH_LONG);
    }
}

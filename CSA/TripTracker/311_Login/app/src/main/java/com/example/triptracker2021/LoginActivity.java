package com.example.triptracker2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    // m is part of hungarian notation. m states that it is a member of a class
    private EditText mEnterEmail;
    private EditText mEnterPassword;
    private EditText mEnterName;
    private Button mLoginButton;
    // will swap between register and login
    private TextView mSwitchViewText;
    private Button mSignMeUp;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEnterEmail = (EditText) findViewById(R.id.enterEmail);
        mEnterPassword = (EditText) findViewById(R.id.enterPassword);
        mEnterName = (EditText) findViewById(R.id.enterName);
        LogInOnClickListener logInOnClickListener = new LogInOnClickListener();
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(logInOnClickListener);
        mSwitchViewText = (TextView) findViewById(R.id.switchViewText);
        SwitchViewOnClickListener signUpTextListener = new SwitchViewOnClickListener();
        mSwitchViewText.setOnClickListener(signUpTextListener);
        mSignMeUp = (Button) findViewById(R.id.signMeUpButton);
        RegisterOnCLickListener registerOnCLickListener = new RegisterOnCLickListener();
        mSignMeUp.setOnClickListener(registerOnCLickListener);

        mFirebaseAuth = FirebaseAuth.getInstance();
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
            String userEmail = mEnterEmail.getText().toString().trim();
            String password = mEnterPassword.getText().toString().trim();
            String name = mEnterName.getText().toString().trim();

            char[] specChars = {'!', '@', '#', '$', '%', '&', '*', '-', '_', '~'};
            char[] lowerChars = new char[26];
            char[] upperChars = new char[26];
            char[] numberics = new char[10];

            for (byte i = 0; i < 10; i++) {
                numberics[i] = (char) (i + 48);
            }

            for (byte i = 0; i < 26; i++) {
                lowerChars[i] = (char) (i + 97);
                upperChars[i] = (char) (i + 65);
            }
            byte specLen = (byte) specChars.length;
            byte numLen = (byte) numberics.length;
            byte charLen = (byte) lowerChars.length;


            if (userEmail.isEmpty()) {
                mEnterEmail.setError("Please enter an email id");
                mEnterEmail.requestFocus();
            } else if (password.isEmpty()) {
                mEnterPassword.setError("Please enter a password");
                mEnterPassword.requestFocus();
            } else if (name.isEmpty()) {
                mEnterName.setError("Please enter a name");
                mEnterName.requestFocus();
            } else if (!(userEmail.isEmpty() && password.isEmpty()&&name.isEmpty())) {
                if (password.length() >= 8) {
                    for (byte i = 0; i < specLen; i++) {
                        System.out.println(i);
                        System.out.println(specChars[i]);
                        if (password.contains(String.valueOf(specChars[i])) && (i < specLen)) {
                            for (byte j = 0; j < numLen; j++) {
                                System.out.println(j);
                                if (password.contains(String.valueOf(numberics[j])) && (j < numLen)) {
                                    for (byte k = 0; k < charLen; k++) {
                                        System.out.println(k);
                                        if (password.contains(String.valueOf(upperChars[k])) && (k < charLen)) {
                                            for (byte l = 0; l < charLen; l++) {
                                                System.out.println(l);
                                                if (password.contains(String.valueOf(lowerChars[l])) && (l < charLen)) {
                                                    Toast.makeText(com.example.triptracker2021.LoginActivity.this, "This is a Valid Password", Toast.LENGTH_LONG).show();
                                                    mFirebaseAuth.createUserWithEmailAndPassword(userEmail,password).addOnCompleteListener(com.example.triptracker2021.LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if(task.isSuccessful()){
                                                                Toast.makeText(com.example.triptracker2021.LoginActivity.this,"You are Registered",Toast.LENGTH_LONG);
                                                                startActivity(new Intent(com.example.triptracker2021.LoginActivity.this,com.example.triptracker2021.LoginActivity.class));
                                                            }
                                                            else{
                                                                Toast.makeText(com.example.triptracker2021.LoginActivity.this, "Registration Failed",Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                                } else badPassword();
                                            }
                                        }
                                        else badPassword();
                                    }
                                }
                                else badPassword();
                            }
                        }
                        else badPassword();
                    }
                }
                else badPassword();
            }
            }
            public void badPassword(){
                Toast.makeText(com.example.triptracker2021.LoginActivity.this, "This Is NOT A Valid Password", Toast.LENGTH_LONG).show();
            }
        }

    private class LogInOnClickListener implements View.OnClickListener {
        private FirebaseAuth.AuthStateListener mAuthStateListener;
        @Override
        public void onClick(View view) {
            String email = mEnterEmail.getText().toString().trim();
            String password = mEnterPassword.getText().toString().trim();

            //need to check to see if all fields are entered, if not, request focus
            if (email.isEmpty()) {
                mEnterEmail.setError("Please enter an email id");
                mEnterEmail.requestFocus();
            } else if (password.isEmpty()) {
                mEnterPassword.setError("Please enter a password");
                mEnterPassword.requestFocus();
            }
            else if(!(email.isEmpty()&&password.isEmpty())){
                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(com.example.triptracker2021.LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(com.example.triptracker2021.LoginActivity.this, "You are Loged In", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(com.example.triptracker2021.LoginActivity.this, com.example.triptracker2021.TripListActivity.class));
                        } else {
                            Toast.makeText(com.example.triptracker2021.LoginActivity.this, "Login  Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }
}

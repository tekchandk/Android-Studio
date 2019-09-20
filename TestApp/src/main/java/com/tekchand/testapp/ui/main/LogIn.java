package com.tekchand.testapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tekchand.testapp.MainActivity;
import com.tekchand.testapp.R;

import static com.tekchand.testapp.Constants.EMAIL;
import static com.tekchand.testapp.Constants.MyPREFERENCES;
import static com.tekchand.testapp.Constants.NAME;
import static com.tekchand.testapp.Constants.SET_ERROR;

public class LogIn extends AppCompatActivity {
    // All Shared Preferences Keys
    public static SharedPreferences sharedPreferences;
    private TextInputLayout userNameText2;
    private TextInputLayout emailText2;
    private Button loginBtn;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userNameText2 = findViewById(R.id.userNameText2);
        emailText2 = findViewById(R.id.emailText2);
        loginBtn = findViewById(R.id.loginbutton);
        sharedPreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validEmail() || !validUserName()) {
                    return;
                }
                String name = userNameText2.getEditText().getText().toString();
                String emailId = emailText2.getEditText().getText().toString();
                editor.putString(NAME, name);
                editor.putString(EMAIL, emailId);
                editor.commit();
                Intent i = new Intent(LogIn.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean validUserName() {
        String userName = userNameText2.getEditText().getText().toString();
        if(userName.isEmpty()) {
            userNameText2.setError(SET_ERROR);
            return false;
        }
        else{
            userNameText2.setError(null);
            return true;
        }
    }

    private boolean validEmail() {
        String email = emailText2.getEditText().getText().toString();
        if(email.isEmpty()) {
            emailText2.setError(SET_ERROR);
            return false;
        }
        else{
           emailText2.setError(null);
            return true;
        }
    }
}

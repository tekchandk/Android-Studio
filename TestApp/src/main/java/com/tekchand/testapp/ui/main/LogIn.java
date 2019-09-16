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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogIn extends AppCompatActivity {
    // All Shared Preferences Keys
    public static final String NAME = "nameKey";
    public static final String EMAIL = "emailKey";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences sharedPreferences;

    TextInputLayout userNameText2;
    TextInputLayout emailText2;
    Button loginBtn;
    SharedPreferences.Editor editor;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userNameText2 = findViewById(R.id.userNameText2);
        emailText2 = findViewById(R.id.emailText2);
        loginBtn = findViewById(R.id.loginbutton);
        sharedPreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String email = emailText2.getEditText().getText().toString();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);




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
            userNameText2.setError("Field can't be empty");
            return false;
        }
        else{
            userNameText2.setError(null);
           // userNameText2.setErrorEnabled(false);

            return true;
        }
    }

    private boolean validEmail() {
        String email = emailText2.getEditText().getText().toString();
        if(email.isEmpty()) {
            emailText2.setError("Field can't be empty");
            return false;
        }
        else{
           emailText2.setError(null);
           // emailText2.setErrorEnabled(false);
            return true;
        }
    }

}

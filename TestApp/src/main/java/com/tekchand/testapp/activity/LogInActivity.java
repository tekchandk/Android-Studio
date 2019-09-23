package com.tekchand.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tekchand.testapp.R;

import static com.tekchand.testapp.constant.Constants.EMAIL;
import static com.tekchand.testapp.constant.Constants.MyPREFERENCES;
import static com.tekchand.testapp.constant.Constants.NAME;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
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
        loginBtn.setOnClickListener(this);
    }

    private boolean validUserName() {
        if(getData(userNameText2).isEmpty()) {
            userNameText2.setError(getString(R.string.sign_in_invalid_field));
            return false;
        }
        else{
            userNameText2.setError(null);
            return true;
        }
    }

    private boolean validEmail() {
        if(getData(emailText2).isEmpty()) {
            emailText2.setError(getString(R.string.sign_in_invalid_field));
            return false;
        }
        else{
           emailText2.setError(null);
            return true;
        }
    }

    private String getData(View view) {
        TextInputLayout editText = (TextInputLayout) view;
        switch (editText.getId()) {
            case R.id.userNameText2:
                return userNameText2.getEditText().getText().toString();
            case R.id.emailText2:
                return emailText2.getEditText().getText().toString();
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.loginbutton:
                if (!validEmail() || !validUserName()) {
                    return;
                }
                editor.putString(NAME, getData(userNameText2));
                editor.putString(EMAIL, getData(emailText2));
                editor.commit();
                Intent i = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(i);
                break;

        }
    }
}

package com.tekchand.testapp.activities;

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
import static com.tekchand.testapp.constant.Constants.SET_ERROR;

/**
 * @author Tek Chand
 * This activity takes user's username and email for sign in the app.
 * where a user can type his valid username and email to login.
 */
public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
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

    /**
     * Return a boolean for checking a username is empty or not.
     * If username is empty it will give an error.
     * @return returnValue boolean true if username is valid, false otherwise
     */
    private boolean validUserName() {
        return valid(userNameText2);
    }

    /**
     * Return a boolean for checking a email is empty or not.
     * If username is empty it will give an error.
     * @return returnValue boolean true if email is valid, false otherwise
     */
    private boolean validEmail() {
        return valid(emailText2);
    }

    /**
     * Return a boolean for checking a user's data is empty or not.
     * @param view TextInputLayout
     * @return returnValue boolean true if user is valid, false otherwise
     */
    private boolean valid(TextInputLayout view) {
        if(getData(view).isEmpty()) {
            view.setError(SET_ERROR);
            return false;
        }
        else{
            view.setError(null);
            return true;
        }
    }


    /**
     * Get the string from the edit text.
     * @param view
     * @return the string which is typed in the edit text.
     */
    private String getData(View view) {
        if (view instanceof TextInputLayout) {
            TextInputLayout v = (TextInputLayout) view;
            return v.getEditText().getText().toString();
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

package com.tekchand.testapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tekchand.testapp.R;

import org.jetbrains.annotations.NotNull;

import static com.tekchand.testapp.constant.Constants.EMAIL;
import static com.tekchand.testapp.constant.Constants.IS_LOGIN;
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
        sharedPreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        checkLogin();
        userNameText2 = findViewById(R.id.userNameText2);
        emailText2 = findViewById(R.id.emailText2);
        loginBtn = findViewById(R.id.loginbutton);
        editor = sharedPreferences.edit();
        loginBtn.setOnClickListener(this);
        EditText editText = emailText2.getEditText();
        if(editText != null)
        emailText2.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            loginBtn.performClick();
            return true;
        });
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

    @NotNull
    private String getData(View view) {
        if (view instanceof TextInputLayout) {
            TextInputLayout v = (TextInputLayout) view;
            if(v.getEditText() != null) {
                return v.getEditText().getText().toString();
            }
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
                InputMethodManager imm = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null)
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                createLoginSession();
                Intent i = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(i);
                break;
        }
    }

    public void createLoginSession(){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(NAME, getData(userNameText2));
        editor.putString(EMAIL, getData(emailText2));
        editor.commit();
    }

    public void checkLogin(){
        // Check login status
        if(isLoggedIn()){
            // user is logged in redirect him to Main Activity
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Main Activity
            getApplicationContext().startActivity(i);
        }
    }

    private boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
}

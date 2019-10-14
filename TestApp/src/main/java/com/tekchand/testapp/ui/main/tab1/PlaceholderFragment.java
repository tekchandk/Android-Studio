package com.tekchand.testapp.ui.main.tab1;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tekchand.testapp.R;
import com.tekchand.testapp.activities.MainActivity;

import dagger.android.support.AndroidSupportInjection;

import static com.tekchand.testapp.constant.Constants.SET_ERROR;

/**
 * @author Tek Chand
 * A placeholder fragment is getting the information
 */
public class PlaceholderFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private CallbackInterface mListener;
    private EditText nameText;
    private EditText locText;
    private EditText addrText;
    private Button subBtn;

    /**
     * create a new instance of PlaceholderFragment fragment
     * @return fragment instance of PlaceholderFragment
     */
    public static PlaceholderFragment newInstance() {
        PlaceholderFragment fragment = new PlaceholderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        nameText = root.findViewById(R.id.nameText);
        locText = root.findViewById(R.id.locText);
        addrText = root.findViewById(R.id.emailText);
        subBtn = root.findViewById(R.id.subBtn);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subBtn.setOnClickListener(this);
        addrText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                subBtn.performClick();
                return true;
            }
        });
    }

    /**
     * Return a boolean for checking a person's name is empty or not.
     * If name is empty it will give an error.
     * @return returnValue boolean true if name is valid, false otherwise
     */
    private boolean validName() {
        return valid(nameText);
    }

    /**
     * Return a boolean for checking a location is empty or not.
     * If location is empty it will give an error.
     * @return returnValue boolean true if location is valid, false otherwise
     */
    private boolean validLocation() {
       return valid(locText);
    }

    /**
     * Return a boolean for checking a email is empty or not.
     * If username is empty it will give an error.
     * @return returnValue boolean true if email is valid, false otherwise
     */
    private boolean validAddr() {
       return valid(addrText);
    }

    /**
     * Return a boolean for checking a person's data is empty or not.
     * @param view EditText
     * @return returnValue boolean true if user is valid, false otherwise
     */
    private boolean valid(EditText view) {
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
        if (view instanceof EditText) {
            EditText v = (EditText) view;
            return v.getText().toString();
        }
        return "";
    }

    /**
     * Interface is implemented in MainActivity {@link MainActivity}
     */
    public interface CallbackInterface{
        void onSubmit(Human human);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        mContext = context;
        if (context instanceof CallbackInterface) {
            mListener = (CallbackInterface) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement CallbackInterface");
        }
    }

    @Override
    public void onClick(View view) {
        if(!validName() || ! validLocation() || !validAddr()) {
            return;
        }
        Human human = new Human(getData(nameText), getData(locText), getData(addrText));
        mListener.onSubmit(human);
        nameText.setText("");
        locText.setText("");
        addrText.setText("");
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
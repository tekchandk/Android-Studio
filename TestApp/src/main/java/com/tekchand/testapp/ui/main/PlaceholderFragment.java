package com.tekchand.testapp.ui.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tekchand.testapp.R;

import static com.tekchand.testapp.Constants.SET_ERROR;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private EditText nameText;
    private EditText locText;
    private EditText addrText;
    private Button subBtn;
    private Frag1 frag1;

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
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String location = locText.getText().toString();
                String address = addrText.getText().toString();
                Human human = new Human(name, location, address);
                if(!validName() || ! validLocation() || !validAddr()) {
                    return;
                }
                frag1.onSubmit(human);
                nameText.setText("");
                locText.setText("");
                addrText.setText("");
            }
        });
    }

    private boolean validName() {
        String userName =nameText.getText().toString();
        if(userName.isEmpty()) {
            nameText.setError(SET_ERROR);
            return false;
        }
        else{
            nameText.setError(null);
            return true;
        }
    }

    private boolean validLocation() {
        String email = locText.getText().toString();
        if(email.isEmpty()) {
            locText.setError(SET_ERROR);
            return false;
        }
        else{
            locText.setError(null);
            return true;
        }
    }

    private boolean validAddr() {
        String email = addrText.getText().toString();
        if(email.isEmpty()) {
            addrText.setError(SET_ERROR);
            return false;
        }
        else{
            addrText.setError(null);
            return true;
        }
    }

    public interface Frag1{
        void onSubmit(Human human);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try {
            frag1 = (Frag1) activity;
        }
        catch(ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + "must override on message");
        }
    }
}
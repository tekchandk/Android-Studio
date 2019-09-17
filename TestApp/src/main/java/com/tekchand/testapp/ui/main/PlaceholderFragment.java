package com.tekchand.testapp.ui.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import androidx.lifecycle.ViewModelProviders;

import com.tekchand.testapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    TextView textView;
    EditText nameText;
    EditText locText;
    EditText addrText;
    Button subBtn;
    Frag1 frag1;
    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        nameText = root.findViewById(R.id.nameText);
        locText = (EditText) root.findViewById(R.id.locText);
        addrText = (EditText) root.findViewById(R.id.emailText);
        subBtn = (Button) root.findViewById(R.id.subBtn);

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
                InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            }
        });

    }

    private boolean validName() {
        String userName =nameText.getText().toString();
        if(userName.isEmpty()) {
            nameText.setError("Field can't be empty");
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
            locText.setError("Field can't be empty");
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
            addrText.setError("Field can't be empty");
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
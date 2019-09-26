package com.tekchand.testapp.ui.main.tab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tekchand.testapp.R;
import com.tekchand.testapp.activities.LogInActivity;

import static com.tekchand.testapp.activities.LogInActivity.sharedPreferences;
import static com.tekchand.testapp.constant.Constants.EMAIL;
import static com.tekchand.testapp.constant.Constants.NAME;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CallbackInterface} interface
 * to handle interaction events.
 * Use the {@link SessionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SessionFrag extends Fragment implements View.OnClickListener {
    private TextView userName;
    private TextView email;
    private Button logOutBtn;
    private CallbackInterface mListener;

    /**
     * get the new Instance of SessionFrag
     * @return fragment a new instance of SessionFrag
     */
    public static SessionFrag newInstance() {
        SessionFrag fragment = new SessionFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_session, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        logOutBtn = root.findViewById(R.id.logoutbutton);
        userName = root.findViewById(R.id.userNameTextView2);
        email = root.findViewById(R.id.emailTextView2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userName.setText(getString(R.string.user_name) + sharedPreferences.getString(NAME, null));
        email.setText(getString(R.string.email_id) + sharedPreferences.getString(EMAIL,null));
        // When logout button pressed. It will go on main screen.
        logOutBtn.setOnClickListener(this);
    }

    /**
     * Clear session details
     */
    public void logoutUser(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(getContext(), LogInActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        getContext().startActivity(i);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallbackInterface) {
            mListener = (CallbackInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CallbackInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        logoutUser();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface CallbackInterface {
    }
}

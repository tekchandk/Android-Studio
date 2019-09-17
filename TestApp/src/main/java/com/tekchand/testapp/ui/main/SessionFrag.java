package com.tekchand.testapp.ui.main;

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

import static com.tekchand.testapp.ui.main.CropFertilizer.CROP;
import static com.tekchand.testapp.ui.main.CropFertilizer.FERTILIZER;
import static com.tekchand.testapp.ui.main.CropFertilizer.PREFERENCECROPFERT;
import static com.tekchand.testapp.ui.main.LogIn.EMAIL;
import static com.tekchand.testapp.ui.main.LogIn.MyPREFERENCES;
import static com.tekchand.testapp.ui.main.LogIn.NAME;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SessionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SessionFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SessionFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SessionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static SessionFrag newInstance(String param1, String param2) {
        SessionFrag fragment = new SessionFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_session, container, false);
        return root;
    }


    private TextView userName;
    private TextView email;
    private Button logOutBtn;
    private TextView cropName;
    private TextView fertilizerName;
    private Button rerfreshBtn;
    private SharedPreferences sharedPreferencesCF;
    private SharedPreferences sharedPreferencesLogIn;

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        logOutBtn = root.findViewById(R.id.logoutbutton);
        userName = root.findViewById(R.id.userNameTextView2);
        email = root.findViewById(R.id.emailTextView2);
        cropName = root.findViewById(R.id.cropTextView);
        fertilizerName = root.findViewById(R.id.fertilizerTextView);
        rerfreshBtn = root.findViewById(R.id.refreshButton);
        sharedPreferencesCF = getContext().getSharedPreferences(PREFERENCECROPFERT, Context.MODE_PRIVATE);
        sharedPreferencesLogIn = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userName.setText("User Name: " + sharedPreferencesLogIn.getString(NAME, null));
        email.setText("Email Id: " + sharedPreferencesLogIn.getString(EMAIL,null));
        rerfreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropName.setText("Crop: " + sharedPreferencesCF.getString(CROP, null));
                fertilizerName.setText("Fertilizer: " + sharedPreferencesCF.getString(FERTILIZER, null));

            }
        });


        // When logout button pressed. It will go on main screen.
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();

            }
        });

    }






    /**
     * Clear session details
     * */
    public void logoutUser(){


        // After logout redirect user to Loing Activity
        Intent i = new Intent(getContext(), LogIn.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        getContext().startActivity(i);
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteractionTab4();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionTab4();
    }
}

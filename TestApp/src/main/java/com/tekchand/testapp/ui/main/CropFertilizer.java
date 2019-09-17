package com.tekchand.testapp.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.tekchand.testapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CropFertilizer extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    public static final String CROP = "cropKey";
    public static final String FERTILIZER = "fertilizerKeyKey";
    public static final String PREFERENCECROPFERT = "cropFertKey";
    private static SharedPreferences sharedPreferencesCropFert;
    private SharedPreferences.Editor editor;

    public static CropFertilizer newInstance(int index) {
        CropFertilizer fragment = new CropFertilizer();
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
        View root = inflater.inflate(R.layout.fragment_crop_fertilizer, container, false);
        return root;
    }

    private EditText cropText;
    private EditText fertilizerText;
    private Button submitButton;
    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        cropText = root.findViewById(R.id.cropText);
        fertilizerText = (EditText) root.findViewById(R.id.fertilizerText);
        submitButton = (Button) root.findViewById(R.id.submitButton);
        sharedPreferencesCropFert = getContext().getSharedPreferences(PREFERENCECROPFERT, Context.MODE_PRIVATE);
        editor = sharedPreferencesCropFert.edit();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validCrop() || ! validFertilizer()) {
                    return;
                }
                String crop = cropText.getText().toString();
                String fertilizer = fertilizerText.getText().toString();
                editor.putString(CROP, crop);
                editor.putString(FERTILIZER, fertilizer);
                editor.commit();

                cropText.setText("");
                fertilizerText.setText("");
                InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            }
        });

    }

    private boolean validCrop() {
        String cropName =cropText.getText().toString();
        if(cropName.isEmpty()) {
            cropText.setError("Field can't be empty");
            return false;
        }
        else{
            cropText.setError(null);

            return true;
        }
    }

    private boolean validFertilizer() {
        String fertilizerName = fertilizerText.getText().toString();
        if(fertilizerName.isEmpty()) {
            fertilizerText.setError("Field can't be empty");
            return false;
        }
        else{
            fertilizerText.setError(null);
            return true;
        }
    }

    /*    @Override
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/
}
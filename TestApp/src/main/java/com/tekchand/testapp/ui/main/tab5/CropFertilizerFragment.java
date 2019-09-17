package com.tekchand.testapp.ui.main.tab5;

import android.content.Context;
import android.content.SharedPreferences;
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

import static com.tekchand.testapp.constant.Constants.CROP;
import static com.tekchand.testapp.constant.Constants.FERTILIZER;
import static com.tekchand.testapp.constant.Constants.PREFERENCECROPFERT;
import static com.tekchand.testapp.constant.Constants.SET_ERROR;

/**
 * A CropFertilizer fragment containing a simple view.
 */
public class CropFertilizerFragment extends Fragment implements View.OnClickListener {

    private CallbackInterface mListener;
    private static SharedPreferences sharedPreferencesCropFert;
    private SharedPreferences.Editor editor;

    public static CropFertilizerFragment newInstance() {
        CropFertilizerFragment fragment = new CropFertilizerFragment();
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
        fertilizerText = root.findViewById(R.id.fertilizerText);
        submitButton = root.findViewById(R.id.submitButton);
        sharedPreferencesCropFert = getContext().getSharedPreferences(PREFERENCECROPFERT, Context.MODE_PRIVATE);
        editor = sharedPreferencesCropFert.edit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        submitButton.setOnClickListener(this);
        fertilizerText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                submitButton.performClick();
                return true;
            }
        });
    }

    /**
     * to check the crop name
     * @return if crop text is not empty return true otherwise false
     */
    private boolean validCrop() {
        return valid(cropText);
    }
    /**
     * to check the fertilizer name
     * @return if fertilizer text is not empty return true otherwise false
     */
    private boolean validFertilizer() {
       return valid(fertilizerText);
    }


    /**
     * Return a boolean for checking a Crop's name is empty or not.
     * @param view EditText
     * @return returnValue boolean true if crop name is valid, false otherwise
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
    public void onClick(View view) {

        if(!validCrop() || ! validFertilizer()) {
            return;
        }
        editor.putString(CROP, getData(cropText));
        editor.putString(FERTILIZER, getData(fertilizerText));
        editor.commit();
        mListener.onSubmit();
        cropText.setText("");
        fertilizerText.setText("");
        // For hiding the soft keyboard.
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public interface CallbackInterface {
         void onSubmit();
    }
}
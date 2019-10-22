package com.tekchand.testapp.scan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tekchand.testapp.R;
import com.tekchand.testapp.title.ActionBarTitle;

import org.jetbrains.annotations.NotNull;

import static com.tekchand.testapp.constant.Constants.NAMES;


public class ScanFragment extends Fragment {

    private CallbackInterface mListener;
    private Button startScanButton;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.scan, container, false);
        return view;
    }

    @Override
    public void onAttach(@NotNull Context context) {
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startScanButton = view.findViewById(R.id.start_scan);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            startScanButton.setOnClickListener(v -> {
                startScanButton.setVisibility(View.GONE);
                assert inflater != null;
                inflater.inflate(R.layout.scaning, (ViewGroup) view);
            });
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        setActionbarTitle();
    }

    private void setActionbarTitle() {
        mListener.setActionBarTitle(NAMES[0]);
    }

    public interface CallbackInterface extends ActionBarTitle {
    }
}

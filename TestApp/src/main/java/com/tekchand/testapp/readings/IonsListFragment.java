package com.tekchand.testapp.readings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;
import com.tekchand.testapp.title.ActionBarTitle;

import org.jetbrains.annotations.NotNull;

import static com.tekchand.testapp.constant.Constants.IONS;
import static com.tekchand.testapp.constant.Constants.NAMES;


public class IonsListFragment extends Fragment {
    private RecyclerView recyclerView;
    private CallbackInterface mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerview_ions_list, container, false);
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
        recyclerView = view.findViewById(R.id.ion_recyclerview);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        IonsRecyclerAdapter adapter = new IonsRecyclerAdapter(IONS, ion -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Alert");
            builder.setMessage("You haven't calibrated the instrument for " + ion + ". Please go to ScanFragment page to scan the soil sampple.");
            builder.setNegativeButton("Cancel", null);
            builder.create();
            builder.show();
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        setActionbarTitle();
    }

    private void setActionbarTitle() {
        mListener.setActionBarTitle(NAMES[1]);
    }

    public interface CallbackInterface extends ActionBarTitle {
    }

}

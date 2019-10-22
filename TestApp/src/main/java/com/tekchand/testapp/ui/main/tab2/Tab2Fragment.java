package com.tekchand.testapp.ui.main.tab2;

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
import com.tekchand.testapp.ui.main.tab1.Human;

import java.util.ArrayList;
import java.util.List;

import static com.tekchand.testapp.utility.FakeData.getFakeData;

public class Tab2Fragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Human> humans = new ArrayList<>();
    private CallbackInterface mListener;
    private RecyclerView.Adapter adapter;
    private HumanRecyclerAdapter.OnClickListener listener;

    /**
     * get the new Instance of Tab2Fragment
     * @return fragment a new instance of Tab2Fragment
     */
    public static Tab2Fragment newInstance() {
        return new Tab2Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        humans = getFakeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2, container, false);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.listv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new HumanRecyclerAdapter(humans, human -> {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Name: " + human.getName() + "\n" + "Location: " + human.getLocation() + "\n" + "Email: " + human.getEmail());
        builder.setTitle("Hi");
       // builder.setIcon(R.drawable.ic_close_black_24dp);
        builder.setNegativeButton("Cancel", null);
        builder.create();
        builder.show();
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     * add the human to the list of humans
     * @param human an instance of Human
     */
    public void updateList(Human human) {
        humans.add(human);
        adapter.notifyDataSetChanged();
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
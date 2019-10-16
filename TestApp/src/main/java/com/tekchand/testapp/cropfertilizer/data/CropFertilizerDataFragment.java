package com.tekchand.testapp.cropfertilizer.data;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;

import java.util.ArrayList;
import java.util.List;


public class CropFertilizerDataFragment extends Fragment implements CardRecyclerAdapter.OnClickListener {

    private RecyclerView recyclerViewCards;
    private CallbackInterface mListener;


   // lists.add(new ListItem());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crop_fertilizer_data, container, false);
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
        recyclerViewCards = view.findViewById(R.id.recyclerview_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewCards.setHasFixedSize(true);

        recyclerViewCards.setLayoutManager(new LinearLayoutManager(getContext()));

        CardsRecyclerAdapter adapter = new CardsRecyclerAdapter(getItems(), getContext(), this);

        recyclerViewCards.setAdapter(adapter);
    }

    @Override
    public void onClick(ItemCard card) {
        Log.d(card.getTitle(), String.valueOf(card.getimageRes()));
    }

    private List<ListItem> getItems(){
        List<ListItem> lists = new ArrayList<>();
        List<ItemCard> cardItems = new ArrayList<>();
        cardItems.add(new ItemCard(R.drawable.wheat, "Wheat"));
        cardItems.add(new ItemCard(R.drawable.wheat, "Rice"));
        cardItems.add(new ItemCard(R.drawable.wheat, "Maize"));
        cardItems.add(new ItemCard(R.drawable.wheat, "Mustard"));
        cardItems.add(new ItemCard(R.drawable.wheat, "Sugarcane"));
        cardItems.add(new ItemCard(R.drawable.wheat, "Cotton"));
        cardItems.add(new ItemCard(R.drawable.wheat, "Potato"));
        lists.add(new ListItem("Crops", cardItems));
        lists.add(new ListItem("Fertilizers", cardItems));
        return lists;
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

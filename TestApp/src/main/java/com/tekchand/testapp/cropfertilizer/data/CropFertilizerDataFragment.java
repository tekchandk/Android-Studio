package com.tekchand.testapp.cropfertilizer.data;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tekchand.testapp.R;
import com.tekchand.testapp.title.ActionBarTitle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CropFertilizerDataFragment extends Fragment implements CardRecyclerAdapter.OnClickListener {

    private RecyclerView recyclerViewCards;
    private CallbackInterface mListener;
    private SearchView searchView;
    private FloatingActionButton fab, fabEdit, fabDownload;
    private Animation fabOpen, fabClose, fabClockwise, fabAnticlockwise;
    private boolean isOpen = false;
    private AlertDialog alertDialog1;
    private ReadCSVFile readCSVFile;
    private List <String[]> scoreList;
    private List<ListItem> items;
    private CharSequence[] titles;
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
        searchView = view.findViewById(R.id.searchView);
        fab = view.findViewById(R.id.fab);
        fabEdit = view.findViewById(R.id.fab_edit);
        fabDownload = view.findViewById(R.id.fab_download);
        fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fabClockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        fabAnticlockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);
        InputStream inputStream = getResources().openRawResource(R.raw.data);
        readCSVFile = new ReadCSVFile(inputStream);
        scoreList = readCSVFile.read();
        items =getItems();
        titles = getTitles(items);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewCards.setHasFixedSize(true);

        recyclerViewCards.setLayoutManager(new LinearLayoutManager(getContext()));

        CardsRecyclerAdapter adapter = new CardsRecyclerAdapter(items, getContext(), this);

        recyclerViewCards.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                  @Override
                  public boolean onQueryTextSubmit(String query) {
                      return false;
                  }

                  @Override
                  public boolean onQueryTextChange(String newText) {
                      newText = newText.toLowerCase();
                      List<ListItem> mList = new ArrayList<>();

                      for (ListItem listItem : items) {
                          List<ItemCard> mCardList = new ArrayList<>();
                          ListItem listItem1 = listItem;
                          for (ItemCard card : listItem.getItems()) {
                              String name = card.getTitle().toLowerCase();
                              if (name.contains(newText)) {
                                  mCardList.add(card);
                              }

                              listItem1.setItems(mCardList);
                          }
                          mList.add(listItem1);
                      }
                      adapter.setSearchOperation(mList);
                      return false;
                  }
              }
        );

        fab.setOnClickListener(v -> {
            if(isOpen) {
                fabEdit.startAnimation(fabClose);
                fabDownload.startAnimation(fabClose);
                fab.startAnimation(fabAnticlockwise);
                fabEdit.setClickable(false);
                fabDownload.setClickable(false);
                isOpen = false;

            }
            else {
                fabEdit.startAnimation(fabOpen);
                fabDownload.startAnimation(fabOpen);
                fab.startAnimation(fabClockwise);
                fabEdit.setClickable(true);
                fabDownload.setClickable(true);
                isOpen = true;
            }

        });

        fabEdit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("What do you want to add?");

            builder.setSingleChoiceItems(titles, 0, (dialog, item) ->
                    Toast.makeText(getActivity(), titles[item],Toast.LENGTH_SHORT).show());

            builder.setPositiveButton("OK", (dialog, which) -> {

            });

            builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());
            alertDialog1 = builder.create();
            alertDialog1.show();
        });
    }

    @Override
    public void onClick(ItemCard card) {
        mListener.onClickItemCard(card);
    }

    @Override
    public void onResume(){
        super.onResume();
        setActionbarTitle();
    }

    private void setActionbarTitle() {
        mListener.setActionBarTitle("Crop & Fertilizer");
    }


    private List<ListItem> getItems(){
        List<ListItem> lists = new ArrayList<>();
        List<ItemCard> cardItems_crops = new ArrayList<>();
        List<ItemCard> cardItems = new ArrayList<>();
        List<ItemCard> cardItems_fertilizers = new ArrayList<>();
        for(String[] row : scoreList) {
            List<String> info = new ArrayList<>();
            for(int i = 3; i < row.length; i++) {
                info.add(row[i]);
            }
            if(row[0].equals("crop")) {
                cardItems_crops.add(new ItemCard(R.drawable.wheat, row[1], info));
            }
            if(row[0].equals("fert")) {
                cardItems_fertilizers.add(new ItemCard(R.drawable.wheat, row[1], info));
            }
        }
        lists.add(new ListItem("Crops", cardItems_crops));
        lists.add(new ListItem("Fertilizers", cardItems_fertilizers));
        return lists;
    }

    private CharSequence[] getTitles(@NonNull List<ListItem> lists) {
        CharSequence[] titles = new CharSequence[lists.size()];
        for (int i=0;i<titles.length;i++)
            titles[i] = lists.get(i).getTitle();
        return titles;
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
    public interface CallbackInterface extends ActionBarTitle {
        void onClickItemCard(ItemCard itemCard);
        //void setTitle(String title);
    }


}

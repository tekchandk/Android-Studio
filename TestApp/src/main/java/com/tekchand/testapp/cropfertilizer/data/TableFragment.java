package com.tekchand.testapp.cropfertilizer.data;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tekchand.testapp.R;
import com.tekchand.testapp.title.ActionBarTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Table fragment shows the data of soil nutrients in a table
 */
public class TableFragment extends Fragment {

    private CallbackInterface mListener;
    private ItemCard mItemCard;

    private List<TextView>  mListIons = new ArrayList<>();

    private TextView mNitrogen, mPhosphate, mPottasium, mZinc, mSulphur, mBoron,
            mManganese, mCopper, mIron, mOrgCarbon;

    private int[] ionId = {R.id.nitrogen_amt, R.id.phosphate_amt, R.id.pottasium_amt,R.id.boron_amt, R.id.sulphur_amt,
            R.id.manganese_amt, R.id.copper_amt, R.id.zinc_amt, R.id.iron_amt, R.id.org_carbon_amt};

    public TableFragment(ItemCard itemCard) {
        mItemCard = itemCard;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_table, container, false);
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
        mListIons.add(mNitrogen);
        mListIons.add(mPhosphate);
        mListIons.add(mPottasium);
        mListIons.add(mBoron);
        mListIons.add(mSulphur);
        mListIons.add(mManganese);
        mListIons.add(mCopper);
        mListIons.add(mZinc);
        mListIons.add(mIron);
        mListIons.add(mOrgCarbon);
        for(int i = 0; i < ionId.length; i++) {
            mListIons.set(i,view.findViewById(ionId[i]));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setInfoItemCard(mItemCard);
    }

    /**
     * set the value of N,P, and K, etc. in the nutrients table
     * @param itemCard is a Crop or Fertilizer name.
     */
    private void setInfoItemCard(ItemCard itemCard) {
        for (int i = 0; i < ionId.length; i++) {
            mListIons.get(i).setText(itemCard.getInfo().get(i));
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        setActionbarTitle();
    }

    /**
     * set the title of the action bar according to the card that user chooses.
     */
    private void setActionbarTitle() {
        mListener.setActionBarTitle(mItemCard.getTitle());
    }

    public interface CallbackInterface extends ActionBarTitle {
    }
}

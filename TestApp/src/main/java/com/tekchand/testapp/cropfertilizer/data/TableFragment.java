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


public class TableFragment extends Fragment {

    private CallbackInterface mListener;
    private ItemCard mItemCard;

    private List<TextView>  mListIOns = new ArrayList<>();

    private TextView mNitrogen, mPhosphate, mPottasium, mZinc, mSulphur, mBoron,
            mManganese, mCopper, mIron, mOrgCarbon;
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
        mNitrogen = view.findViewById(R.id.nitrogen_amt);
        mPhosphate = view.findViewById(R.id.phosphate_amt);
        mPottasium = view.findViewById(R.id.pottasium_amt);
        mBoron = view.findViewById(R.id.boron_amt);
        mSulphur = view.findViewById(R.id.sulphur_amt);
        mManganese = view.findViewById(R.id.manganese_amt);
        mCopper = view.findViewById(R.id.copper_amt);
        mZinc = view.findViewById(R.id.zinc_amt);
        mIron = view.findViewById(R.id.iron_amt);
        mOrgCarbon = view.findViewById(R.id.org_carbon_amt);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            mListIOns.add(mNitrogen);
            mListIOns.add(mPhosphate);
            mListIOns.add(mPottasium);
            mListIOns.add(mBoron);
            mListIOns.add(mSulphur);
            mListIOns.add(mManganese);
            mListIOns.add(mCopper);
            mListIOns.add(mZinc);
            mListIOns.add(mIron);
            mListIOns.add(mOrgCarbon);
        setInfoItemCard(mItemCard);
    }

    private void setInfoItemCard(ItemCard itemCard) {
        for (int i = 0; i < 10; i++) {
            mListIOns.get(i).setText(itemCard.getInfo().get(i));
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        setActionbarTitle();
    }

    private void setActionbarTitle() {
        mListener.setActionBarTitle(mItemCard.getTitle());
    }

    public interface CallbackInterface extends ActionBarTitle {
    }


}

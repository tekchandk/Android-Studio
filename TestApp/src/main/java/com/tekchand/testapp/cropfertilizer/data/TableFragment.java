package com.tekchand.testapp.cropfertilizer.data;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tekchand.testapp.R;


public class TableFragment extends Fragment {

    private CallbackInterface mListener;
    private ItemCard mItemCard;

    private TextView mNitrogen, mPhosphate,
            mPottasium, mZinc, mSulphur, mBoron,
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
        setInfoItemCard(mItemCard);
    }

    private void setInfoItemCard(ItemCard itemCard) {
        Log.d("Amount: ", itemCard.getInfo().get(0));
        mNitrogen.setText("jkhjhkhhjk");
        mNitrogen.setText(itemCard.getInfo().get(0));
        mPhosphate.setText(itemCard.getInfo().get(1));
        mPottasium.setText(itemCard.getInfo().get(2));
        mZinc.setText(itemCard.getInfo().get(3));
        mSulphur.setText(itemCard.getInfo().get(4));
        mBoron.setText(itemCard.getInfo().get(5));
        mManganese.setText(itemCard.getInfo().get(6));
        mCopper.setText(itemCard.getInfo().get(7));
        mIron.setText(itemCard.getInfo().get(8));
        mOrgCarbon.setText(itemCard.getInfo().get(9));
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

package com.tekchand.testapp.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tekchand.testapp.R;
import com.tekchand.testapp.ui.main.tab1.PlaceholderFragment;
import com.tekchand.testapp.ui.main.tab2.Tab2Fragment;
import com.tekchand.testapp.ui.main.tab3.VmFragment;
import com.tekchand.testapp.ui.main.tab4.SessionFrag;
import com.tekchand.testapp.ui.main.tab5.CropFertilizerFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5};
    private final Context mContext;
    private List<Fragment> fragments = new ArrayList<>();

    @Inject
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        fragments.add(PlaceholderFragment.newInstance());
        fragments.add(Tab2Fragment.newInstance());
        fragments.add(VmFragment.newInstance());
        fragments.add(SessionFrag.newInstance());
        fragments.add(CropFertilizerFragment.newInstance());
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show no. of tabs
        return fragments.size();
    }
}
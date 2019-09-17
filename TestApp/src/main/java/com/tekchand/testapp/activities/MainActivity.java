package com.tekchand.testapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tekchand.testapp.R;
import com.tekchand.testapp.ui.main.SectionsPagerAdapter;
import com.tekchand.testapp.ui.main.tab1.Human;
import com.tekchand.testapp.ui.main.tab1.PlaceholderFragment;
import com.tekchand.testapp.ui.main.tab2.Tab2Fragment;
import com.tekchand.testapp.ui.main.tab3.VmFragment;
import com.tekchand.testapp.ui.main.tab4.SessionFrag;
import com.tekchand.testapp.ui.main.tab5.CropFertilizerFragment;

/**
 * @author Tek Chand
 * This activity show the data in four tab layout.
 */
public class MainActivity extends AppCompatActivity implements Tab2Fragment.CallbackInterface, PlaceholderFragment.CallbackInterface, VmFragment.CallbackInterface, SessionFrag.CallbackInterface, CropFertilizerFragment.CallbackInterface {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onSubmit(Human human) {
        Fragment item = sectionsPagerAdapter.getItem(1);
        if(item instanceof Tab2Fragment) {
            Tab2Fragment tab2Fragment = (Tab2Fragment) item;
            tab2Fragment.updateList(human);
        }
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onSubmit() {
        Fragment item = sectionsPagerAdapter.getItem(3);
        if(item instanceof SessionFrag) {
            SessionFrag tab4Fragment = (SessionFrag) item;
            tab4Fragment.updateData();
        }
        viewPager.setCurrentItem(3);
    }
}
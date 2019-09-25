package com.tekchand.testapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tekchand.testapp.R;
import com.tekchand.testapp.ui.main.SectionsPagerAdapter;
import com.tekchand.testapp.ui.main.tab1.Human;
import com.tekchand.testapp.ui.main.tab1.PlaceholderFragment;
import com.tekchand.testapp.ui.main.tab2.Tab2Fragment;
import com.tekchand.testapp.ui.main.tab3.vmfrag;
import com.tekchand.testapp.ui.main.tab4.SessionFrag;

/**
 * @author Tek Chand
 * This activity show the data in four tab layout.
 */
public class MainActivity extends AppCompatActivity implements Tab2Fragment.CallbackInterface, PlaceholderFragment.CallbackInterface, vmfrag.CallbackInterface, SessionFrag.CallbackInterface {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private Tab2Fragment tab2Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onSubmit(Human human) {
        tab2Fragment = (Tab2Fragment) sectionsPagerAdapter.getItem(1);
        tab2Fragment.updateList(human);
    }
}
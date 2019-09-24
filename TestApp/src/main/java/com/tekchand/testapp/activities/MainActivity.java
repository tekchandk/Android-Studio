package com.tekchand.testapp.activities;

import android.net.Uri;
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
 * This activity show the data in four tab layout using.
 */
public class MainActivity extends AppCompatActivity implements Tab2Fragment.OnFragmentInteractionListener, PlaceholderFragment.Frag1, vmfrag.OnVideoFragmentListener, SessionFrag.OnFragmentInteractionListener {

    private Tab2Fragment tab2Fragment = new Tab2Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentTab2Interaction() {

    }

    @Override
    public void onSubmit(Human human) {
      tab2Fragment.updateList(human);
    }

    @Override
    public void onVideoFragmentInteraction() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
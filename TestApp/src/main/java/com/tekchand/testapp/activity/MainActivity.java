package com.tekchand.testapp.activity;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tekchand.testapp.R;
import com.tekchand.testapp.ui.main.SectionsPagerAdapter;
import com.tekchand.testapp.ui.main.tab1.Human;
import com.tekchand.testapp.ui.main.tab1.PlaceholderFragment;
import com.tekchand.testapp.ui.main.tab2.HumanRecyclerAdapter;
import com.tekchand.testapp.ui.main.tab2.Tab2Fragment;
import com.tekchand.testapp.ui.main.tab3.Video;
import com.tekchand.testapp.ui.main.tab3.vmfrag;
import com.tekchand.testapp.ui.main.tab4.SessionFrag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tekchand.testapp.constant.Constants.REGREX;

public class MainActivity extends AppCompatActivity implements Tab2Fragment.OnFragmentInteractionListener, PlaceholderFragment.Frag1, vmfrag.OnVideoFragmentListener, SessionFrag.OnFragmentInteractionListener {

    private RecyclerView.Adapter adapter;
    private List<Human> humans = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();
    void fakeData(){
        Human human1 = new Human("Tek", "Sikar","tekchand@iitk.ac.in");
        Human human2 = new Human ("Ayush", "Kanpur", "ayushg@iitk.ac.in");
        Human human3 = new Human("Atul", "BHU", "atul@iitbhu.ac.in");
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
    }

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
    public void onFragmentInteraction(RecyclerView recyclerView) {
        fakeData();
        adapter = new HumanRecyclerAdapter(humans);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSubmit(Human human) {

        String email = human.getEmail();
        Pattern pattern = Pattern.compile(REGREX);
        Matcher matcher = pattern.matcher(email);
        humans.add(human);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onVideoFragmentInteraction() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
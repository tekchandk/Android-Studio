package com.tekchand.testapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tekchand.testapp.ui.main.Human;
import com.tekchand.testapp.ui.main.HumanRecyclerAdapter;
import com.tekchand.testapp.ui.main.PlaceholderFragment;
import com.tekchand.testapp.ui.main.SectionsPagerAdapter;
import com.tekchand.testapp.ui.main.SessionFrag;
import com.tekchand.testapp.ui.main.Tab2Fragment;
import com.tekchand.testapp.ui.main.Video;
import com.tekchand.testapp.ui.main.VideoRecyclerAdapter;
import com.tekchand.testapp.ui.main.vmfrag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tekchand.testapp.Constants.REGREX;

public class MainActivity extends AppCompatActivity implements Tab2Fragment.OnFragmentInteractionListener, PlaceholderFragment.Frag1, vmfrag.OnVideoFragmentListener, SessionFrag.OnFragmentInteractionListener {

    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter videoAdapter;
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
    public void onVideoFragmentInteraction(RecyclerView recyclerView) {
        vmfrag frag3 = new vmfrag();
        videos = frag3.videoList();
        videoAdapter = new VideoRecyclerAdapter(getApplicationContext(), videos);
        recyclerView.setAdapter(videoAdapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
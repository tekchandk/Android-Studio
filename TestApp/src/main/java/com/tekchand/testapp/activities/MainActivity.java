package com.tekchand.testapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import javax.inject.Inject;

/**
 * @author Tek Chand
 * This activity show the data in four tab layout.
 */
public class MainActivity extends AppCompatActivity implements Tab2Fragment.CallbackInterface, PlaceholderFragment.CallbackInterface, VmFragment.CallbackInterface, SessionFrag.CallbackInterface, CropFertilizerFragment.CallbackInterface {
    @Inject SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        //imageButton = findViewById(R.id.vert_dots);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bluetooth:
                Toast.makeText(this, "Bluetooth is selected" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item1:
                Toast.makeText(this, "Item1 is selected" , Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
                sectionsPagerAdapter.getItem(viewPager.getCurrentItem());
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item2 is selected" , Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
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
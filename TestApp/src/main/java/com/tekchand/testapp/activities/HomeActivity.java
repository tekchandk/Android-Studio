package com.tekchand.testapp.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;
import com.tekchand.testapp.adapter.home.HomeAdapter;
import com.tekchand.testapp.cropfertilizer.data.CropFertilizerDataFragment;
import com.tekchand.testapp.readings.IonsList;
import com.tekchand.testapp.scan.Scan;

import static com.tekchand.testapp.constant.Constants.IMAGESID;
import static com.tekchand.testapp.constant.Constants.NAMES;

public class HomeActivity extends AppCompatActivity implements
        IonsList.CallbackInterface,
        Scan.CallbackInterface,
        CropFertilizerDataFragment.CallbackInterface {

    public Toolbar mToolbar;
    private RecyclerView homeRecyclerView;
    private GridLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mToolbar = findViewById(R.id.toolbar_item1);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        homeRecyclerView = findViewById(R.id.home_recyclerview);
        layoutManager = new GridLayoutManager(HomeActivity.this, 2);
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setLayoutManager(layoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(NAMES,IMAGESID, new HomeAdapter.OnClickListener() {
            @Override
            public void onClick(String function) {

                if(function.equals("Scan")) {
                    Scan fragment= new Scan();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.home_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                    // transaction.addToBackStack(null);  // this will manage backstack
                    transaction.commit();
                }
                if(function.equals("Readings")) {
                    IonsList fragment= new IonsList();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.home_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                    // transaction.addToBackStack(null);  // this will manage backstack
                    transaction.commit();
                }

                if(function.equals("Crop & Fertilizer Data")) {
                    CropFertilizerDataFragment fragment= new CropFertilizerDataFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.home_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
                    // transaction.addToBackStack(null);  // this will manage backstack
                    transaction.commit();
                }
            }
        });
        homeRecyclerView.setAdapter(homeAdapter);
    }
}

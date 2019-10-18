package com.tekchand.testapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;
import com.tekchand.testapp.adapter.home.HomeAdapter;
import com.tekchand.testapp.cropfertilizer.data.CropFertilizerDataFragment;
import com.tekchand.testapp.cropfertilizer.data.ItemCard;
import com.tekchand.testapp.cropfertilizer.data.TableFragment;
import com.tekchand.testapp.readings.IonsList;
import com.tekchand.testapp.scan.Scan;

import static com.tekchand.testapp.constant.Constants.IMAGESID;
import static com.tekchand.testapp.constant.Constants.NAMES;

public class HomeActivity extends AppCompatActivity implements
        IonsList.CallbackInterface,
        Scan.CallbackInterface,
        CropFertilizerDataFragment.CallbackInterface,
        TableFragment.CallbackInterface {

    public Toolbar mToolbar;
    private RecyclerView homeRecyclerView;
    private GridLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mToolbar = findViewById(R.id.toolbar_item1);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(false);
        //mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        homeRecyclerView = findViewById(R.id.home_recyclerview);
        layoutManager = new GridLayoutManager(HomeActivity.this, 2);
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setLayoutManager(layoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(NAMES,IMAGESID, new HomeAdapter.OnClickListener() {
            @Override
            public void onClick(String function) {
                fragmentTransaction(function);
            }
        });
        homeRecyclerView.setAdapter(homeAdapter);
    }

    private void fragmentTransaction(String function) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(function.equals("Scan")) {
            transaction.replace(R.id.home_container, new Scan()); // fragment container id in first parameter is the  container(Main layout id) of Activity
        }
        else if(function.equals("Readings")) {
            transaction.replace(R.id.home_container, new IonsList()); // fragment container id in first parameter is the  container(Main layout id) of Activity
        }
        else if(function.equals("Crop & Fertilizer Data")) {
            transaction.replace(R.id.home_container, new CropFertilizerDataFragment()); // fragment container id in first parameter is the  container(Main layout id) of Activity
        }
        transaction.addToBackStack(null);  // this will manage backstack
        transaction.commit();


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClickItemCard(ItemCard itemCard) {
        TableFragment fragment= new TableFragment(itemCard);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.crop_fertilizer_data_fragment, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
        transaction.addToBackStack(null);  // this will manage backstack
        transaction.commit();

    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}

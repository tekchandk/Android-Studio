package com.tekchand.testapp.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tekchand.testapp.R;
import com.tekchand.testapp.adapter.home.HomeAdapter;
import com.tekchand.testapp.cropfertilizer.data.CropFertilizerDataFragment;
import com.tekchand.testapp.cropfertilizer.data.ItemCard;
import com.tekchand.testapp.cropfertilizer.data.TableFragment;
import com.tekchand.testapp.readings.IonsListFragment;
import com.tekchand.testapp.scan.ScanFragment;
import com.tekchand.testapp.title.ActionBarTitle;

import static com.tekchand.testapp.constant.Constants.IMAGESID;
import static com.tekchand.testapp.constant.Constants.NAMES;

public class HomeActivity extends AppCompatActivity implements
        IonsListFragment.CallbackInterface,
        ScanFragment.CallbackInterface,
        CropFertilizerDataFragment.CallbackInterface,
        TableFragment.CallbackInterface,
        ActionBarTitle {

    public Toolbar mToolbar;
    private ActionBar supportActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mToolbar = findViewById(R.id.toolbar_item1);

        setSupportActionBar(mToolbar);
        supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayShowTitleEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView homeRecyclerView = findViewById(R.id.home_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(HomeActivity.this, 2);
        homeRecyclerView.setHasFixedSize(true);
        homeRecyclerView.setLayoutManager(layoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(NAMES,IMAGESID, new HomeAdapter.OnClickListener() {
            @Override
            public void onClick(String function) {
                HomeActivity.this.fragmentTransaction(function);
            }

            @Override
            public void onLongPress(String function) {
                HomeActivity.this.createDialogBox(function);
            }
        });
        homeRecyclerView.setAdapter(homeAdapter);
    }


    /**
     * Shows a new fragment according to the pressed button.
     * @param buttonName is the name of button.
     */
    private void fragmentTransaction(String buttonName) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new Fragment();
        if(buttonName.equals(NAMES[0])) {
            fragment = new ScanFragment();
        }
        else if(buttonName.equals(NAMES[1])) {
            fragment = new IonsListFragment();
        }
        else if(buttonName.equals(NAMES[2])) {
            fragment = new CropFertilizerDataFragment();
        }
        transaction.add(R.id.home_container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
        transaction.addToBackStack(null);  // this will manage backstack
        transaction.commit();
    }

    /**
     * Create a dialog box for
     * @param buttonName
     */
    private void createDialogBox(String buttonName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(buttonName.equals(NAMES[0])) {
            builder.setMessage("Before taking the readings from a soil sample, the device must be calibrated for the constituent ions" +
                    "Tap on this card to calibrate the device");
        }
        else if(buttonName.equals(NAMES[1])) {
            builder.setMessage("Tap on this card to find out the concentration of various ions from an unknown soil sample");
        }
        else if(buttonName.equals(NAMES[2])) {
            builder.setMessage("Tap on this card to know about the ions and their proportions required for crop and fertilizer");
        }

        builder.setNegativeButton("Cancel", null);
        builder.create();
        builder.show();
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int numberOfFragment = (getSupportFragmentManager().getBackStackEntryCount() - 1);
        if (numberOfFragment >= 0) {
            Fragment previousFragment = getSupportFragmentManager().getFragments().get(numberOfFragment);
            if (previousFragment != null) {
                previousFragment.onResume();
            }
        } else {
            this.onResume();
        }
    }

    public void onClickItemCard(ItemCard itemCard) {
        Fragment fragment= new TableFragment(itemCard);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.crop_fertilizer_data_fragment, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
        transaction.addToBackStack(null);  // this will manage backstack
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        setActionBarTitle(getString(R.string.app_name));
    }

    @Override
    public void setActionBarTitle(@NonNull String title) {
        if( supportActionBar!= null)
        supportActionBar.setTitle(title);
    }
}

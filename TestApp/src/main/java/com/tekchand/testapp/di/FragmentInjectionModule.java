package com.tekchand.testapp.di;

import androidx.annotation.NonNull;

import com.tekchand.testapp.di.scopes.FragmentScope;
import com.tekchand.testapp.ui.main.tab1.PlaceholderFragment;
import com.tekchand.testapp.ui.main.tab2.Tab2Fragment;
import com.tekchand.testapp.ui.main.tab3.VmFragment;
import com.tekchand.testapp.ui.main.tab4.SessionFrag;
import com.tekchand.testapp.ui.main.tab5.CropFertilizerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentInjectionModule {

    @FragmentScope
    @ContributesAndroidInjector()
    @NonNull
    public abstract PlaceholderFragment placeHolderFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector()
    @NonNull
    public abstract Tab2Fragment tab2FragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector()
    @NonNull
    public abstract VmFragment vmFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector()
    @NonNull
    public abstract SessionFrag sessionFragInjector();

    @FragmentScope
    @ContributesAndroidInjector()
    @NonNull
    public abstract CropFertilizerFragment cropFertilizerFragmentInjector();

}

package com.tekchand.testapp.di;

import com.tekchand.testapp.activities.LogInActivity;
import com.tekchand.testapp.activities.MainActivity;
import com.tekchand.testapp.di.scopes.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityInjectionModule {

    @ActivityScope
    @ContributesAndroidInjector()
    public abstract MainActivity contributeMainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector()
    public abstract LogInActivity contributeLoginActivityInjector();


}

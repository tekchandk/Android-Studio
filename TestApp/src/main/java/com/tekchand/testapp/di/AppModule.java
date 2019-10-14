package com.tekchand.testapp.di;

import android.content.Context;

import com.tekchand.testapp.TestAppApplication;
import com.tekchand.testapp.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @AppScope
    @Provides
    public static Context provideApplicationContext(TestAppApplication application) {
        return application.getApplicationContext();
    }

}
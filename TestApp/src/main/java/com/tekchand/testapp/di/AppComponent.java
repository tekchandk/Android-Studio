package com.tekchand.testapp.di;

import com.tekchand.testapp.TestAppApplication;
import com.tekchand.testapp.di.scopes.AppScope;

import dagger.BindsInstance;
import dagger.Component;

@AppScope
@Component(modules = {
        AppModule.class,
        ActivityInjectionModule.class,
        FragmentInjectionModule.class
})
public interface AppComponent {

    void inject(TestAppApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder testAppApplication(TestAppApplication application);

        AppComponent build();
    }
}



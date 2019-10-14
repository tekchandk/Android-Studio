package com.tekchand.testapp.di;

import androidx.fragment.app.Fragment;

import dagger.MapKey;

@MapKey
public @interface CustomFragmentKey {
    Class<? extends Fragment> value();
}

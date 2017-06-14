package com.spring.tinklabssample.dagger.module;

import com.spring.tinklabssample.view.DemoFragment;
import com.spring.tinklabssample.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by spring on 13/6/2017.
 */

@Module
abstract class AndroidBindingModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract DemoFragment demoFragment();
}

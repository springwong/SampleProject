package com.spring.tinklabssample.core;

import android.app.Activity;
import android.app.Application;

import com.spring.tinklabssample.dagger.module.DaggerMainApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by spring on 13/6/2017.
 */

public class MainApplication extends DaggerApplication {



    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        //dagger inject for Dependency injection
        return DaggerMainApplicationComponent.builder().create(this);
    }
}

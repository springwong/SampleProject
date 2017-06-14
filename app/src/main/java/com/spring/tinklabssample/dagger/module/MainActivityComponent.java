package com.spring.tinklabssample.dagger.module;

import com.spring.tinklabssample.core.MainApplication;
import com.spring.tinklabssample.view.MainActivity;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by spring on 13/6/2017.
 */

@Component(
        modules = {
                ApplicationModule.class,
                AndroidBindingModule.class,
                AndroidSupportInjectionModule.class
        }
)
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}

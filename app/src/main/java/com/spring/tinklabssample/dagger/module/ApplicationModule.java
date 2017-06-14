package com.spring.tinklabssample.dagger.module;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.spring.tinklabssample.core.MainApplication;
import com.spring.tinklabssample.data.DummyTripAdvisorService;
import com.spring.tinklabssample.data.TripAdvisorService;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by spring on 13/6/2017.
 */

@Module
abstract class ApplicationModule {

    @Binds
    abstract MainApplication application(MainApplication application);

    @Provides
    static CallAdapter.Factory callFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    static Converter.Factory convertFactory() {
        return GsonConverterFactory.create();
    }


    /***
     * provide retrofit, default point to a api domain
     * @param callAdapter
     * @param converter
     * @return
     */
    @Provides
    static Retrofit retrofit(CallAdapter.Factory callAdapter, Converter.Factory converter) {
        return new Retrofit.Builder()
                .baseUrl("https://staging.handy.travel")
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapter)
                .build();
    }

    @Provides
    static TripAdvisorService service(Retrofit retrofit) {
        return new DummyTripAdvisorService();
//        return retrofit.create(TripAdvisorService.class);
    }

}

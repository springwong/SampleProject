package com.spring.tinklabssample.view;

import android.app.ActionBar;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.spring.tinklabssample.R;
import com.spring.tinklabssample.dagger.module.DaggerMainActivityComponent;
import com.spring.tinklabssample.data.TripAdvisorService;
import com.spring.tinklabssample.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DaggerActivity;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends DaggerAppCompatActivity {

    ActivityMainBinding binding;
    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    public static class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new DemoFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "CITY GUIDE";
                case 1:
                    return "SHOP";
                case 2:
                    return "EAT";
                default:
                    return "DEFAULT";
            }
        }
    }


}

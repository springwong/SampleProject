package com.spring.tinklabssample;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.spring.tinklabssample.view.DemoFragment;
import com.spring.tinklabssample.view.MainActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by spring on 14/6/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void mainActivity_testViewPagerAdapter() throws Exception {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.viewPager);
        Assert.assertEquals(3, viewPager.getAdapter().getCount());
        Assert.assertEquals("CITY GUIDE", viewPager.getAdapter().getPageTitle(0));
    }

    @Test
    public void mainActivity_checkFragmentClass() throws Exception {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        ViewPager viewPager = (ViewPager) mainActivity.findViewById(R.id.viewPager);
        FragmentPagerAdapter adapter = (FragmentPagerAdapter) viewPager.getAdapter();
        Assert.assertEquals(DemoFragment.class, adapter.getItem(0).getClass());
    }

}

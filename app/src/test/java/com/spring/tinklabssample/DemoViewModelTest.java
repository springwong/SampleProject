package com.spring.tinklabssample;

import com.spring.tinklabssample.model.TripAdvisorModel;
import com.spring.tinklabssample.data.TripAdvisorService;
import com.spring.tinklabssample.viewmodel.DemoViewModel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

/**
 * Created by spring on 14/6/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DemoViewModelTest {
    @InjectMocks
    DemoViewModel demoViewModel;
    @Mock TripAdvisorService service;

    private TripAdvisorModel testModel;

    @BeforeClass
    public static void setupRx() throws Exception {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUp() throws Exception {
        testModel = new TripAdvisorModel(
                Arrays.asList(
                        new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")
                        , new TripAdvisorModel.AdvisorData("Hello", "path", "test")

                ));
        Mockito.when(service.getTripAdvisor(anyInt(), anyInt())).thenReturn(
                Observable.just(testModel)
        );
        demoViewModel.added = PublishSubject.create();
    }

    @Test
    public void testOnCreate() throws Exception {
        TestObserver<List<TripAdvisorModel.AdvisorData>> testObserver = new TestObserver<>();
        demoViewModel.added.subscribe(testObserver);

        demoViewModel.onCreate();

        testObserver.assertSubscribed();
        testObserver.assertNoErrors();
        testObserver.assertValue(testModel.dataList);
        Assert.assertEquals(1, demoViewModel.pageNo);
        Assert.assertEquals(testModel.dataList.size(), demoViewModel.dataSet.size());
    }

    @Test
    public void testLoadMore() throws Exception {
        TestObserver<List<TripAdvisorModel.AdvisorData>> testObserver = new TestObserver<>();
        demoViewModel.added.subscribe(testObserver);

        demoViewModel.onCreate();
        demoViewModel.loadMore();

        testObserver.assertSubscribed();
        testObserver.assertNoErrors();

        Assert.assertEquals(2, demoViewModel.pageNo);
        Assert.assertEquals(testModel.dataList.size() * 2, demoViewModel.dataSet.size());
    }
}

package com.spring.tinklabssample.viewmodel;

import com.spring.tinklabssample.model.TripAdvisorModel;
import com.spring.tinklabssample.data.TripAdvisorService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by spring on 14/6/2017.
 */

public class DemoViewModel {
    public int numberOfItemPerCall = 10;
    public int pageNo = 0;
    private boolean isLoading = false;
    public List<TripAdvisorModel.AdvisorData> dataSet = new ArrayList<>();

    @Inject
    TripAdvisorService service;

    public PublishSubject<List<TripAdvisorModel.AdvisorData>> added;


    @Inject
    public DemoViewModel() {
        added = PublishSubject.create();
    }

    public void onCreate() {
        nextPageData();
    }

    public void loadMore() {
        if(isLoading) {
            return;
        }
        nextPageData();
    }

    private void nextPageData() {
        pageNo += 1;
        isLoading = true;
        service.getTripAdvisor(numberOfItemPerCall, pageNo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(tripAdvisorModel -> tripAdvisorModel.dataList)
                .doOnError(throwable -> isLoading = false)
                .doOnError(throwable -> pageNo -= 1)
                .doOnComplete(() -> isLoading = false)
                .subscribe(advisorDatas -> {
                    dataSet.addAll(advisorDatas);
                    added.onNext(advisorDatas);
                }, throwable -> {added.onError(throwable);});
    }
}

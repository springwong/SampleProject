package com.spring.tinklabssample.data;

import com.spring.tinklabssample.model.TripAdvisorModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by spring on 13/6/2017.
 */

public interface TripAdvisorService {
    @GET("openapi/trip_advisor")
    Observable<TripAdvisorModel> getTripAdvisor(@Query("limit") int limit, @Query("page") int page);
}

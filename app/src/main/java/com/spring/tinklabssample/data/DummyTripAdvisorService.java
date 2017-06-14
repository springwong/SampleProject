package com.spring.tinklabssample.data;

import com.spring.tinklabssample.model.TripAdvisorModel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by spring on 15/6/2017.
 */

public class DummyTripAdvisorService implements TripAdvisorService {
    @Override
    public Observable<TripAdvisorModel> getTripAdvisor(@Query("limit") int limit, @Query("page") int page) {
        List<String> title = Arrays.asList("Woaw Store", "Armoury");
        List<String> imagePath = Arrays.asList(
                "http://hospitalityawards.com/fileadmin/_processed_/csm_Credit-Hyatt-Hotels-1024x783_760d50a3d0.png"
                , "http://bbyo.org/azabbgic/Hyatt_Regency_Dallas.png");
        List<String> desc = Arrays.asList(
               "Lifestyle-fashion store Woaw is in hip district of SOHO, surrounded by a slow of population."
                , "This photogenic store offers everything from artisansal men's shoes and blazers, to shirt an ..."
        );
        Random random = new Random();
        TripAdvisorModel model = new TripAdvisorModel(
                Arrays.asList(
                        new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                        , new TripAdvisorModel.AdvisorData(title.get(random.nextInt(2)), imagePath.get(random.nextInt(2)), desc.get(random.nextInt(2)))
                )
        );

        return Observable.just(model);
    }
}

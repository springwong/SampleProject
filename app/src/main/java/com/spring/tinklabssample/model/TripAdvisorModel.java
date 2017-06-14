package com.spring.tinklabssample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by spring on 13/6/2017.
 */

public class TripAdvisorModel {
    @SerializedName("data")
    public List<AdvisorData> dataList;

    public TripAdvisorModel(List<AdvisorData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "TripAdvisorModel{" +
                "dataList=" + dataList +
                '}';
    }

    public static class AdvisorData {
        @SerializedName("userName")
        String userName;

        @SerializedName("avatarImageUrl")
        String avatarImageUrl;

        @SerializedName("location")
        String location;

        @SerializedName("rating")
        int rating;

        @SerializedName("recommend")
        String recommend;

        @SerializedName("url")
        String url;

        @SerializedName("hotelName")
        String hotelName;

        @SerializedName("country")
        String country;

        public AdvisorData(String userName, String avatarImageUrl, String recommend) {
            this.userName = userName;
            this.avatarImageUrl = avatarImageUrl;
            this.recommend = recommend;
        }

        @Override
        public String toString() {
            return "AdvisorData{" +
                    "userName='" + userName + '\'' +
                    ", avatarImageUrl='" + avatarImageUrl + '\'' +
                    ", location='" + location + '\'' +
                    ", rating=" + rating +
                    ", recommend='" + recommend + '\'' +
                    ", url='" + url + '\'' +
                    ", hotelName='" + hotelName + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

        public String getUserName() {
            return userName;
        }

        public String getAvatarImageUrl() {
            return avatarImageUrl;
        }

        public String getRecommend() {
            return recommend;
        }


    }
}

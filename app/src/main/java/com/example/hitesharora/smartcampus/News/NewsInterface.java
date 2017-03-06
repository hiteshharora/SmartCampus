package com.example.hitesharora.smartcampus.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hitesharora on 3/5/2017.
 */

public interface NewsInterface {

    @GET("news/menu")
    Call<NewsMenuCategory> getNewsMenu(@Query("news/menu") String apiKey);

    @GET("news/feed/id/1/")
    Call<NewsFeed> getNewsFeed();
}

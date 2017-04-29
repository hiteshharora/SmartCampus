package com.example.hitesharora.smartcampus.Streaming;

import com.example.hitesharora.smartcampus.News.NewsFeed;
import com.example.hitesharora.smartcampus.News.NewsMenuCategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hitesharora on 3/5/2017.
 */

public interface StreamingInterface {


    @GET("realtime/default")
    Call<StreamingItem> getStreaming();
}

package com.example.hitesharora.smartcampus.News;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsFeed implements Serializable{

    @SerializedName("id")
	public long FeedId;

    @SerializedName("feed_name")
	public String FeedName;

    @SerializedName("feed_url")
	public String FeedUrl;

    @SerializedName("show")
	public int isActive;

    @SerializedName("feeds")
	public ArrayList<NewsItem> newsItems;

}

package com.example.hitesharora.smartcampus.News;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class NewsItem implements Serializable {

	@SerializedName("item_number")
	public String ItemId;

	@SerializedName("feed_id")
	public long FeedId;

	@SerializedName("title")
	public String Title;

	@SerializedName("link")
	public String Link;

	@SerializedName("author")
	public String Author;

	@SerializedName("description")
	public String Description;

	@SerializedName("content")
	public String Content;

	@SerializedName("pub_date")
	public String PublishedDate;

	@SerializedName("thumbnail")
	public String Thumbnail;
}

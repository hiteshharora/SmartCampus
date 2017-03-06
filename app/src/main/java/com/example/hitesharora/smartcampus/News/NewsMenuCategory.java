package com.example.hitesharora.smartcampus.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsMenuCategory{

	public long CategoryId;
	
	public String CategoryName;
	
	public ArrayList<NewsMenuItem> newsMenuItems;
	

	public static NewsMenuCategory fromJson(JSONObject jsonObject)  {
	    	NewsMenuCategory category = new NewsMenuCategory();
	        try {
	        	category.CategoryId = jsonObject.getLong("category_id");
	        	category.CategoryName = jsonObject.getString("category_name");
	        	category.newsMenuItems = new ArrayList<NewsMenuItem>();
	            JSONArray newsMenuItems = jsonObject.getJSONArray("menuItems");
	            category.newsMenuItems =  NewsMenuItem.fromJson(newsMenuItems, category);
	        } catch (JSONException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return category;
    }	
	    
    public static ArrayList<NewsMenuCategory> fromJson(JSONArray jsonArray) {
        ArrayList<NewsMenuCategory> categories = new ArrayList<NewsMenuCategory>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject categoryJSON = null;
            try {
            	categoryJSON = jsonArray.getJSONObject(i);
            	NewsMenuCategory category = NewsMenuCategory.fromJson(categoryJSON);
                if (category != null) {
                    categories.add(category);
                }            	
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return categories;
    }
}

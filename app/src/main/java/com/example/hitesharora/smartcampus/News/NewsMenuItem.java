package com.example.hitesharora.smartcampus.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsMenuItem {

	public long ItemId;

	public long CategoryId;

	public String ItemName;

	public String IconClass;

	public String IconName;
	
	public NewsMenuCategory newsMenuCategory;
	
	
    public static NewsMenuItem fromJson(JSONObject jsonObject, NewsMenuCategory category) {
    	NewsMenuItem item = new NewsMenuItem();
        try {
        	item.ItemId = jsonObject.getLong("item_id");
        	item.ItemName = jsonObject.getString("item_name");
        	item.CategoryId = category.CategoryId;
        	item.newsMenuCategory = category;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }	
    
    public static ArrayList<NewsMenuItem> fromJson(JSONArray jsonArray, NewsMenuCategory category) {
        ArrayList<NewsMenuItem> items = new ArrayList<NewsMenuItem>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemJSON = null;
            try {
            	itemJSON = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            NewsMenuItem item = NewsMenuItem.fromJson(itemJSON, category);
            if (item != null) {
            	items.add(item);
            }
        }
        return items;
    }
}

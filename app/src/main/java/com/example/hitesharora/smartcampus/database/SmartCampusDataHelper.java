package com.example.hitesharora.smartcampus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.hitesharora.smartcampus.News.NewsFeed;
import com.example.hitesharora.smartcampus.News.NewsItem;
import java.util.ArrayList;

/**
 */
public class SmartCampusDataHelper {

    public static SmartCampusDataHelper mInstance;

    private Context mContext;

    private SmartCampusDataHelper() {
    }

    private SmartCampusDataHelper(Context context) {
        mContext = context;
    }

    public static SmartCampusDataHelper getInstance(Context context) {
        if (mInstance == null)
            mInstance = new SmartCampusDataHelper(context);
        return mInstance;
    }

    public void insertNewsData(NewsFeed response) {
        if (response != null) {
            ContentValues newsFeed = new ContentValues();
            newsFeed.put(NewsFeedTable.COLUMN_FEED_ID, response.FeedId);
            newsFeed.put(NewsFeedTable.COLUMN_FEED_NAME, response.FeedName);
            newsFeed.put(NewsFeedTable.COLUMN_FEED_URL, response.FeedName);
            newsFeed.put(NewsFeedTable.COLUMN_IS_ACTIVE, response.isActive);
            NewsFeedApi.getInstance(mContext).insertNewsFeed(newsFeed);

            int length = response.newsItems.size();
            ContentValues[] newsFeedItems = new ContentValues[length];
            for (int i = 0; i < length; i++) {
                ContentValues feedItem = new ContentValues();
                feedItem.put(NewsFeedItemTable.COLUMN_FEED_ID, response.newsItems.get(i).FeedId);
                feedItem.put(NewsFeedItemTable.COLUMN_ITEM_ID, response.newsItems.get(i).ItemId);
                feedItem.put(NewsFeedItemTable.COLUMN_TITLE, response.newsItems.get(i).Title);
                feedItem.put(NewsFeedItemTable.COLUMN_AUTHOR, response.newsItems.get(i).Author);
                feedItem.put(NewsFeedItemTable.COLUMN_LINK, response.newsItems.get(i).Link);
                feedItem.put(NewsFeedItemTable.COLUMN_DESCRIPTION, response.newsItems.get(i).Description);
                feedItem.put(NewsFeedItemTable.COLUMN_CONTENT, response.newsItems.get(i).Content);
                feedItem.put(NewsFeedItemTable.COLUMN_PUBLISHED_DATE, response.newsItems.get(i).PublishedDate);
                feedItem.put(NewsFeedItemTable.COLUMN_THUMBNAIL, response.newsItems.get(i).Thumbnail);
              newsFeedItems[i] = feedItem;
            }
            NewsFeedApi.getInstance(mContext).insertBulkFeedItem(newsFeedItems);
        }
    }

    public NewsFeed getNewsFeed() {
        NewsFeed item = new NewsFeed();
        Cursor appCursor = NewsFeedApi.getInstance(mContext).queryNewsFeed();
        if (appCursor != null && appCursor.getCount() > 0) {
            appCursor.moveToFirst();
            item.FeedId = appCursor.getInt(appCursor.getColumnIndex(NewsFeedTable.COLUMN_FEED_ID));
            item.FeedName = appCursor.getString(appCursor.getColumnIndex(NewsFeedTable.COLUMN_FEED_NAME));
            item.FeedUrl = appCursor.getString(appCursor.getColumnIndex(NewsFeedTable.COLUMN_FEED_URL));
            item.isActive = appCursor.getInt(appCursor.getColumnIndex(NewsFeedTable.COLUMN_IS_ACTIVE));
            appCursor.close();
        }

        Cursor appCursorItem = NewsFeedApi.getInstance(mContext).queryNewsFeedItem((int) item.FeedId);
        if (appCursorItem != null && appCursorItem.getCount() > 0) {
            appCursorItem.moveToFirst();
            ArrayList<NewsItem> feedItemList = new ArrayList<NewsItem>();
            do {
                NewsItem feedItem = new NewsItem();
                feedItem.FeedId = appCursorItem.getInt(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_FEED_ID));
                feedItem.ItemId = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_ITEM_ID));
                feedItem.Author = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_AUTHOR));
                feedItem.Content = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_CONTENT));
                feedItem.Description = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_DESCRIPTION));
                feedItem.Link = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_LINK));
                feedItem.PublishedDate = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_PUBLISHED_DATE));
                feedItem.Title = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_TITLE));
                feedItem.Thumbnail = appCursorItem.getString(appCursorItem.getColumnIndex(NewsFeedItemTable.COLUMN_THUMBNAIL));
                feedItemList.add(feedItem);
            } while (appCursorItem.moveToNext());

            appCursorItem.close();
            item.newsItems = feedItemList;
        }
        return item;
    }
}

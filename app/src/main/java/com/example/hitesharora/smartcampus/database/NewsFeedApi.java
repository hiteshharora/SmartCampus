package com.example.hitesharora.smartcampus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

/**
 */
public class NewsFeedApi {

    public static NewsFeedApi mInstance;
    private Context mContext;

    private NewsFeedApi(Context context) {
        mContext = context;
    }

    public static NewsFeedApi getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new NewsFeedApi(context);
        }
        return mInstance;
    }

    /**
     * method used to insert row via content provider
     *
     * @param value
     * @return
     */
    public int insertNewsFeed(ContentValues value) {
        Uri uri = mContext.getContentResolver().insert(SmartCampusContentProvider.CONTENT_URI_NEWS_FEED, value);
        System.out.println("Conversation Added URI :: " + uri);
        return Integer.parseInt(uri.getLastPathSegment());
    }

    public Cursor queryNewsFeed() {
        Cursor appCursor = mContext.getContentResolver().query(SmartCampusContentProvider.CONTENT_URI_NEWS_FEED, null, null, null, null);
        return appCursor;
    }

    public Cursor queryNewsFeedItem(int feedId) {
        return mContext.getContentResolver().query(
                SmartCampusContentProvider.CONTENT_URI_NEWS_FEED_ITEM, null,
                NewsFeedItemTable.COLUMN_FEED_ID + "=?", new String[] { feedId + "" },null,null);
    }

    /**
     * method used to insert row via content provider
     *
     * @param value
     * @return
     */
    public int insertNewsFeedItem(ContentValues value) {
        Uri uri = mContext.getContentResolver().insert(SmartCampusContentProvider.CONTENT_URI_NEWS_FEED_ITEM, value);
        System.out.println("Conversation Added URI :: " + uri);
        return Integer.parseInt(uri.getLastPathSegment());
    }

    /**
     * method used to insert row via content provider
     *
     * @param value
     * @return
     */
    public int updateNewsFeed(ContentValues value, int id) {
        int uri = mContext.getContentResolver().update(SmartCampusContentProvider.CONTENT_URI_NEWS_FEED, value, NewsFeedItemTable.COLUMN_ITEM_ID + "=?", new String[]{id + ""});
        // System.out.println("Conversation Added URI :: " + uri);
        return uri;
    }


    /**
     * method used to insert row via content provider
     *
     * @param value
     * @return
     */
    public int updateNewsFeedItem(ContentValues value, int id) {
        int uri = mContext.getContentResolver().update(SmartCampusContentProvider.CONTENT_URI_NEWS_FEED_ITEM, value, NewsFeedItemTable.COLUMN_ITEM_ID + "=?", new String[]{id + ""});
        // System.out.println("Conversation Added URI :: " + uri);
        return uri;
    }

    /**
     * method used to delete rows with the specified id via content provider
     *
     * @param id
     */
    public void deleteNewsFeed(int id) {
        mContext.getContentResolver().delete(
                SmartCampusContentProvider.CONTENT_URI_NEWS_FEED,
                NewsFeedTable.COLUMN_FEED_ID + "=?", new String[]{id + ""});
    }


    /**
     * method used to delete rows with the specified id via content provider
     *
     * @param id
     */
    public void deleteNewsFeedItem(int id) {
        mContext.getContentResolver().delete(
                SmartCampusContentProvider.CONTENT_URI_NEWS_FEED_ITEM,
                NewsFeedItemTable.COLUMN_ITEM_ID + "=?", new String[]{id + ""});
    }

    /**
     * method used to clear all rows via content provider
     */
    public void clear() {
        mContext.getContentResolver().delete(
                SmartCampusContentProvider.CONTENT_URI_NEWS_FEED, null, null);

        mContext.getContentResolver().delete(
                SmartCampusContentProvider.CONTENT_URI_NEWS_FEED_ITEM, null, null);
    }

    /**
     * method used to insert all rows via content provider
     *
     * @param values
     * @return
     */
    public int insertBulk(ContentValues[] values) {
        int noOfRecordInserted = mContext.getContentResolver().bulkInsert(
                SmartCampusContentProvider.CONTENT_URI_NEWS_FEED, values);
        // System.out.println("Inserted Record Count :: " + noOfRecordInserted);
        return noOfRecordInserted;
    }

    /**
     * method used to insert all rows via content provider
     *
     * @param values
     * @return
     */
    public int insertBulkFeedItem(ContentValues[] values) {
        int noOfRecordInserted = mContext.getContentResolver().bulkInsert(
                SmartCampusContentProvider.CONTENT_URI_NEWS_FEED_ITEM, values);
        // System.out.println("Inserted Record Count :: " + noOfRecordInserted);
        return noOfRecordInserted;
    }
}

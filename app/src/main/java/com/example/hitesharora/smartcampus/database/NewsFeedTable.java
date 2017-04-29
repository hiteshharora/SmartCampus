package com.example.hitesharora.smartcampus.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 */
public class NewsFeedTable {

    public static final String TABLE_NEWS_FEED = "NewsFeed";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FEED_ID = "FeedId";
    public static final String COLUMN_FEED_NAME = "FeedName";
    public static final String COLUMN_FEED_URL = "FeedUrl";
    public static final String COLUMN_IS_ACTIVE = "isActive";


    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE_NEWS_FEED
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FEED_ID + " integer, "
            + COLUMN_FEED_NAME + " text, "
            + COLUMN_FEED_URL + " text, "
            + COLUMN_IS_ACTIVE + " integer, "
            + "UNIQUE (" + COLUMN_ID + ") ON CONFLICT REPLACE" + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(NewsFeedTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS_FEED);
        onCreate(database);
    }
}

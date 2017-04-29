package com.example.hitesharora.smartcampus.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 */
public class NewsFeedItemTable {

    public static final String TABLE_NEWS_FEED_ITEM = "NewsFeedItem";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FEED_ID = "FeedId";
    public static final String COLUMN_ITEM_ID = "ItemId";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_LINK = "Link";
    public static final String COLUMN_AUTHOR = "Author";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_CONTENT = "Content";
    public static final String COLUMN_PUBLISHED_DATE = "PublishedDate";
    public static final String COLUMN_THUMBNAIL = "Thumbnail";


    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table " + TABLE_NEWS_FEED_ITEM
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FEED_ID + " text, "
            + COLUMN_ITEM_ID + " text, "
            + COLUMN_TITLE + " text, "
            + COLUMN_LINK + " text, "
            + COLUMN_AUTHOR + " text, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_CONTENT + " text, "
            + COLUMN_PUBLISHED_DATE + " text, "
            + COLUMN_THUMBNAIL + " text, "
            + "UNIQUE (" + COLUMN_ID + ") ON CONFLICT REPLACE" + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(NewsFeedItemTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS_FEED_ITEM);
        onCreate(database);
    }
}

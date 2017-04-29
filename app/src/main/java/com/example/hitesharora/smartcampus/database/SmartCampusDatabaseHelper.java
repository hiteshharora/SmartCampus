package com.example.hitesharora.smartcampus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 */
public class SmartCampusDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SmartCampus.db";
    private static final int DATABASE_VERSION = 1;

    public SmartCampusDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        NewsFeedTable.onCreate(db);
        NewsFeedItemTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        NewsFeedTable.onUpgrade(db, oldVersion, newVersion);
        NewsFeedItemTable.onUpgrade(db, oldVersion, newVersion);
    }
}

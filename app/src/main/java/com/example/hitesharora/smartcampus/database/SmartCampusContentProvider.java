package com.example.hitesharora.smartcampus.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 */
public class SmartCampusContentProvider extends ContentProvider {

    SmartCampusDatabaseHelper mSmartCampustDatabaseHelper;

    private static final String AUTHORITY = "vnd.smartcampus.android.provider";
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final String BASE_PATH_NEWS_FEED = "newsfeed";
    private static final int NEWS_FEED = 10;
    private static final int NEWS_FEED_ID = 20;
    public static final Uri CONTENT_URI_NEWS_FEED = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_NEWS_FEED);

    private static final String BASE_PATH_NEWS_FEED_ITEM = "newsfeeditem";
    private static final int NEWS_FEED_ITEM = 30;
    private static final int NEWS_FEED_ITEM_ID = 40;
    public static final Uri CONTENT_URI_NEWS_FEED_ITEM = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_NEWS_FEED_ITEM);

    static {

        sUriMatcher.addURI(AUTHORITY, BASE_PATH_NEWS_FEED, NEWS_FEED);
        sUriMatcher.addURI(AUTHORITY, BASE_PATH_NEWS_FEED + "/#", NEWS_FEED);

        sUriMatcher.addURI(AUTHORITY, BASE_PATH_NEWS_FEED_ITEM, NEWS_FEED_ITEM);
        sUriMatcher.addURI(AUTHORITY, BASE_PATH_NEWS_FEED_ITEM + "/#", NEWS_FEED_ITEM);
    }

    @Override
    public boolean onCreate() {
        mSmartCampustDatabaseHelper = new SmartCampusDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Check if the caller has requested a column which does not exists
        // checkColumns(projection);

        int uriType = sUriMatcher.match(uri);

        switch (uriType) {
            case NEWS_FEED:
                queryBuilder.setTables(NewsFeedTable.TABLE_NEWS_FEED);
                break;

            case NEWS_FEED_ID:
                queryBuilder.setTables(NewsFeedTable.TABLE_NEWS_FEED);
                // Adding the ID to the original query
                queryBuilder.appendWhere(NewsFeedTable.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;

            case NEWS_FEED_ITEM:
                queryBuilder.setTables(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM);
                break;

            case NEWS_FEED_ITEM_ID:
                queryBuilder.setTables(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM);
                // Adding the ID to the original query
                queryBuilder.appendWhere(NewsFeedItemTable.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = mSmartCampustDatabaseHelper.getReadableDatabase();
        Cursor cursor;

        cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = sUriMatcher.match(uri);
        Uri resultUri = null;
        SQLiteDatabase sqlDB = mSmartCampustDatabaseHelper.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case NEWS_FEED:
                id = sqlDB.insert(NewsFeedTable.TABLE_NEWS_FEED, null, values);
                resultUri = Uri.parse(BASE_PATH_NEWS_FEED + "/" + id);
                break;

            case NEWS_FEED_ID:
                id = sqlDB.insert(NewsFeedTable.TABLE_NEWS_FEED, null, values);
                resultUri = Uri.parse(BASE_PATH_NEWS_FEED + "/" + id);
                break;

            case NEWS_FEED_ITEM:
                id = sqlDB.insert(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM, null, values);
                resultUri = Uri.parse(BASE_PATH_NEWS_FEED_ITEM + "/" + id);
                break;

            case NEWS_FEED_ITEM_ID:
                id = sqlDB.insert(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM, null, values);
                resultUri = Uri.parse(BASE_PATH_NEWS_FEED_ITEM + "/" + id);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mSmartCampustDatabaseHelper.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType) {

            case NEWS_FEED:
                rowsDeleted = sqlDB.delete(NewsFeedTable.TABLE_NEWS_FEED, selection, selectionArgs);
                break;

            case NEWS_FEED_ID:
                String id_profile = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(NewsFeedTable.TABLE_NEWS_FEED,
                            NewsFeedTable.COLUMN_ID + "=" + id_profile, null);
                } else {
                    rowsDeleted = sqlDB.delete(NewsFeedTable.TABLE_NEWS_FEED,
                            NewsFeedTable.COLUMN_ID + "=" + id_profile + " and "
                                    + selection, selectionArgs);
                }
                break;

            case NEWS_FEED_ITEM:
                rowsDeleted = sqlDB.delete(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM, selection, selectionArgs);
                break;

            case NEWS_FEED_ITEM_ID:
                String id_report_group = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM,
                            NewsFeedItemTable.COLUMN_ID + "=" + id_report_group, null);
                } else {
                    rowsDeleted = sqlDB.delete(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM,
                            NewsFeedItemTable.COLUMN_ID + "=" + id_report_group + " and "
                                    + selection, selectionArgs);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int uriType = sUriMatcher.match(uri);
        SQLiteDatabase sqlDB = mSmartCampustDatabaseHelper.getWritableDatabase();

        int rowUpdated = 0;
        switch (uriType) {
            case NEWS_FEED:
                rowUpdated = sqlDB.update(NewsFeedTable.TABLE_NEWS_FEED, values, selection, selectionArgs);
                break;

            case NEWS_FEED_ID:
                String id_profile = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowUpdated = sqlDB.update(NewsFeedTable.TABLE_NEWS_FEED, values,
                            NewsFeedTable.COLUMN_ID + "=" + id_profile, null);
                } else {
                    rowUpdated = sqlDB.update(NewsFeedTable.TABLE_NEWS_FEED, values,
                            NewsFeedTable.COLUMN_ID + "=" + id_profile + " and "
                                    + selection, selectionArgs);
                }
                break;

            case NEWS_FEED_ITEM:
                rowUpdated = sqlDB.update(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM, values, selection, selectionArgs);
                break;

            case NEWS_FEED_ITEM_ID:

                String id_report_group = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowUpdated = sqlDB.update(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM, values,
                            NewsFeedItemTable.COLUMN_ID + "=" + id_report_group, null);
                } else {
                    rowUpdated = sqlDB.update(NewsFeedItemTable.TABLE_NEWS_FEED_ITEM, values,
                            NewsFeedItemTable.COLUMN_ID + "=" + id_report_group + " and "
                                    + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowUpdated;
    }

    /**
     * for executing bulk insert query
     *
     * @param uri
     * @param values
     * @return
     */
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int numInserted = 0;
        String table = "";

        int uriType = sUriMatcher.match(uri);

        switch (uriType) {
            case NEWS_FEED:
                table = NewsFeedTable.TABLE_NEWS_FEED;
                break;

            case NEWS_FEED_ITEM:
                table = NewsFeedItemTable.TABLE_NEWS_FEED_ITEM;
                break;
        }

        SQLiteDatabase sqlDB = mSmartCampustDatabaseHelper.getWritableDatabase();
        sqlDB.beginTransaction();
        try {
            for (ContentValues cv : values) {
                long newID = sqlDB.insertWithOnConflict(table, null, cv,
                        SQLiteDatabase.CONFLICT_REPLACE);
                if (newID < 0) {
                    throw new SQLException("Failed to insert row into " + uri);
                }
            }
            sqlDB.setTransactionSuccessful();
            getContext().getContentResolver().notifyChange(uri, null);
            numInserted = values.length;
        } finally {
            sqlDB.endTransaction();
        }
        return numInserted;
    }
}

package com.example.kumar.newsapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.kumar.newsapp.models.NewsItem;

/**
 * Created by Ekta on 28-07-2017.
 */

public class NewsDb {
    //Method to add news record in database from server
    public static void updateNews(SQLiteDatabase db, NewsItem[] newsItems) {
        db.beginTransaction();
        deleteNews(db);
        try {
            for (NewsItem newsItem : newsItems) {
                ContentValues val = new ContentValues();
                val.put(Contract.TABLE_NEWS.COLUMN_NAME_TITLE,
                        newsItem.getTitle());
                val.put(Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION,
                        newsItem.getDescription());
                val.put(Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT,
                        newsItem.getPublishedAt());
                val.put(Contract.TABLE_NEWS.COLUMN_NAME_IMAGEURL,
                        newsItem.getImageUrl());
                Log.d("TEST", "Create table SQL: " + newsItem.getImageUrl());
                val.put(Contract.TABLE_NEWS.COLUMN_NAME_URL,
                        newsItem.getUrl());
                db.insert(Contract.TABLE_NEWS.TABLE_NAME, null, val);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    //To delete all the news records from the database
    private static void deleteNews(SQLiteDatabase db) {
        db.delete(Contract.TABLE_NEWS.TABLE_NAME, null, null);
    }

    //Method to read all news records from database
    public static CursorNews getAllNewsCursor(SQLiteDatabase db) {
        Cursor cursor = db.query(Contract.TABLE_NEWS.TABLE_NAME, null, null,
                null, null, null,
                Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT + " DESC");
        return new CursorNews(cursor);
    }
}

package com.example.kumar.newsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    //Implementing Database of SQLiteOpenHelper to maintain the News App
    private static final String DATABASE_NAME = "NewsApp.db";

    private static final int DATABASE_VERSION = 1;

    private static final String TAG = "dbhelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryString = "CREATE TABLE " + Contract.TABLE_NEWS.TABLE_NAME + " ("+
                Contract.TABLE_NEWS._ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.TABLE_NEWS.COLUMN_NAME_AUTHOR + " TEXT, " +
                Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION + " TEXT , " +
                Contract.TABLE_NEWS.COLUMN_NAME_IMAGEURL + " TEXT , " +
                Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT + " TEXT , " +
                Contract.TABLE_NEWS.COLUMN_NAME_TITLE + " TEXT , " +
                Contract.TABLE_NEWS.COLUMN_NAME_URL + " TEXT  " + "); ";

        Log.d(TAG, "Create table SQL: " + queryString);
        db.execSQL(queryString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

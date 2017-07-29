package com.example.kumar.newsapp.database;

import android.database.Cursor;

/**
 * Created by Ekta on 28-07-2017.
 */

public class CursorNews {
    //Class for DB cursor to get the news from the table
    private Cursor cursor;

    public CursorNews(Cursor cursor){
        this.cursor = cursor;
    }

    public void moveToposition(int pos) {
        cursorCheck();
        cursor.moveToPosition(pos);
    }

    //Checking if the cursor is null, throw an exception
    private void cursorCheck() {
        if (cursor == null)
            throw new RuntimeException("cursor is null");
    }

    public int getCount() {
        cursorCheck();
        return cursor.getCount();
    }

    public String getpublishedAt() {
        cursorCheck();
        return cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT));
    }

    public String getImageUrl() {
        cursorCheck();
        return  cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_IMAGEURL));
    }

    public String getTitle() {
        cursorCheck();
        return cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_TITLE));
    }

    public String getDescription() {
        cursorCheck();
        return cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION));
    }

    public String getUrl() {
        cursorCheck();
        return cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_URL));
    }
}

package com.example.kumar.newsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kumar.newsapp.database.DBHelper;
import com.example.kumar.newsapp.database.NewsDb;
import com.example.kumar.newsapp.models.NewsItem;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Ekta on 28-07-2017.
 */

public class NewsAsync {
    //For refresing the news app in the database
    private static final String TAG = "NewsAsync";

    public static void updateDatabase(Context context) {

        String apiResults = null;
        try {
            URL apiUrl = NetworkUtils.buildURL();
            apiResults = NetworkUtils.getResponseFromHttpUrl(apiUrl);
            if (apiResults != null && !apiResults.isEmpty()) {
                NewsItem[] newsItems = NewsJson.getNews(apiResults);
                SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
                NewsDb.updateNews(db, newsItems);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.kumar.newsapp;

import android.util.Log;

import com.example.kumar.newsapp.adapters.MainViewAdapter;
import com.example.kumar.newsapp.models.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ekta on 28-07-2017.
 */

public class NewsJson {
    private static final String TAG = "NewsJSON";

    public static NewsItem[] getNews(String jResponse) {
        NewsItem[] newsItem = null;
        try {
            JSONObject obj = new JSONObject(jResponse);
            JSONArray list = obj.getJSONArray("articles");

            //items = new ArrayList<>(list.length());
            if (list != null) {
                newsItem = new NewsItem[list.length()];
                for (int i = 0; i < list.length(); i++) {
                    JSONObject o = list.getJSONObject(i);
                    newsItem[i] = new NewsItem();
                    newsItem[i].setAuthor(o.getString("author"));
                    newsItem[i].setTitle(o.getString("title"));
                    newsItem[i].setDescription(o.getString("description"));
                    newsItem[i].setUrl(o.getString("url"));
                    newsItem[i].setImageUrl(o.getString("urlToImage"));
                    newsItem[i].setPublishedAt(o.getString("publishedAt"));

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItem;
    }
}

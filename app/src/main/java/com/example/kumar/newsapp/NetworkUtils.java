package com.example.kumar.newsapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Ekta on 6/14/2017.
 */

public class NetworkUtils {

    public static final String BASE_URL = "https://newsapi.org/v1/articles";
    public static final String SOURCE_PARAM_QUERY = "source";
    public static final String SORTBY_PARAM_QUERY = "sortBy";
    public static final String APIKEY_PARAM_QUERY = "apiKey";
    public static final String SORT_BY_LATEST ="latest";
    public static final String API_KEY_VALUE = "8eff322fdb4e408a89eccde94a4a370e";

    public static URL buildURL(){
        Uri uri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(
                SOURCE_PARAM_QUERY, "the-next-web").appendQueryParameter(SORTBY_PARAM_QUERY,
                SORT_BY_LATEST).appendQueryParameter(APIKEY_PARAM_QUERY,
                API_KEY_VALUE).build();
        URL url = null;

        try {
            url = new URL(uri.toString());
            Log.d("NetworkUtils::buildURL", "Url: " +url);
        }catch(Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

package com.example.kumar.newsapp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.kumar.newsapp.NewsAsync;

/**
 * Created by Ekta on 6/17/2017.
 */

//Implementation of AsyncTaskLoader for loading the news
public class NewsAsyncLoader extends AsyncTaskLoader<Void> {

   private static final String TAG = "NewsAsyncLoader";
   private Context context;

   //Loading the news from server to the Databse
   public NewsAsyncLoader(Context context) {
      super(context);
      this.context = context;
   }

   @Override protected void onStartLoading() {
      super.onStartLoading();
      forceLoad();
      Log.d(TAG, "AsyncLoad onStartLoading");
   }

   @Override public Void loadInBackground() {
      Log.d(TAG, "AsyncLoad loadInBackground");
      NewsAsync.updateDatabase(context);
      return null;
   }

   @Override public void deliverResult(Void data) {
      super.deliverResult(data);
      Log.d(TAG, "NewsAsyncLoad Result " + data);
   }
}

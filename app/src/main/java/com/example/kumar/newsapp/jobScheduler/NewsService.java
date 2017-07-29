package com.example.kumar.newsapp.jobScheduler;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.kumar.newsapp.NewsAsync;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by Ekta on 28-07-2017.
 */

//Job scheduler that updates the db
public class NewsService extends JobService {
    private static final String TAG = "NewsService";

    //Job scheduler updating the database with the latest news
    @Override public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob");
        new AsyncTask<Void, Void, Void>() {
            @Override protected Void doInBackground(Void... params) {
                NewsAsync.updateDatabase(NewsService.this);
                return null;
            }
        }.execute();

        Toast.makeText(NewsService.this, "News updated!",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob");
        return false;
    }
}

package com.example.kumar.newsapp.jobScheduler;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by Ekta on 28-07-2017.
 */


public class NewsJobScheduler {
    private static final String TAG = "NewsJobScheduler";
    private static final String NEWS_JOB_TAG = "ManiNewsLoaderSchedulerJob";

    public static void scheduleNewsLoad(Context context) {
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        //For the background job every minute done by the Firebase Dispatcher
        Job constraintRefreshJob = dispatcher.newJobBuilder().setService(
                NewsService.class).setTag(NEWS_JOB_TAG).setConstraints(
                Constraint.ON_ANY_NETWORK).setLifetime(Lifetime.FOREVER)
                .setRecurring(true).setTrigger(Trigger.executionWindow(0, 60))
                .setReplaceCurrent(true).build();

        dispatcher.schedule(constraintRefreshJob);
        Log.d(TAG, "NewsService in background");
    }

    public static void stopScheduledNewsLoad(Context context) {
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        dispatcher.cancel(NEWS_JOB_TAG);
        Log.d(TAG, "NewsServiceBackground job stopped");
    }
}


package com.example.kumar.newsapp;

import android.app.LoaderManager;
import android.content.Intent;

import android.content.Loader;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.kumar.newsapp.adapters.MainViewAdapter;
import com.example.kumar.newsapp.database.CursorNews;
import com.example.kumar.newsapp.database.DBHelper;
import com.example.kumar.newsapp.database.NewsDb;
import com.example.kumar.newsapp.jobScheduler.NewsJobScheduler;
import com.example.kumar.newsapp.loader.NewsAsyncLoader;


public class MainActivity extends AppCompatActivity
        implements NewsClickListener, LoaderManager.LoaderCallbacks<Void>{

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private MainViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        CursorNews newsCursor = NewsDb.getAllNewsCursor(db);

        adapter = new MainViewAdapter(newsCursor, this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
        getLoaderManager().initLoader(0, null, this);

        checkFirstRunAndFirstLoad();
        displayNews();
    }

    //To load first when the application is installed the first time
    private void checkFirstRunAndFirstLoad() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                this);
        boolean firstRun = prefs.getBoolean("firstRun", true);
        if (firstRun) {
            reLoad();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
        }
    }

    private void reLoad() {
        getLoaderManager().restartLoader(0, null, this).forceLoad();
    }

    //Staring background news load
    @Override protected void onStart() {
        super.onStart();
        NewsJobScheduler.scheduleNewsLoad(this);
    }

    @Override public Loader<Void> onCreateLoader(int id, Bundle args) {
        return new NewsAsyncLoader(this);
    }

    @Override public void onLoadFinished(Loader<Void> loader, Void data) {
        Log.d(TAG, "onLoadFinished");
        displayNews();
    }

    //Displaying news which is in the Database
    private void displayNews() {
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        CursorNews newsCursor = NewsDb.getAllNewsCursor(db);
        adapter.swapCursor(newsCursor);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override public void onLoaderReset(Loader<Void> loader) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSubmitButtonEnabled(true);

        Log.d("onCOMenu:searchView", searchView.toString());
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override protected void onStop() {
        super.onStop();
        NewsJobScheduler.stopScheduledNewsLoad(this);
    }

    @Override
    public void onClick(ClickEvent event) {
        Uri uri = Uri.parse(event.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
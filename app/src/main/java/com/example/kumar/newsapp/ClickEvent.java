package com.example.kumar.newsapp;

import android.view.View;

/**
 * Created by Ekta on 28-07-2017.
 */

public class ClickEvent {
    private View view;
    private String url;

    public ClickEvent(View view, String url) {
        this.view = view;
        this.url = url;
    }

    public View getSource() {
        return view;
    }

    public String getUrl() {
        return url;
    }
}

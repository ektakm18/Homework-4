package com.example.kumar.newsapp;


import java.util.EventListener;

/**
 * Created by Ekta on 28-07-2017.
 */

//Listener which is implemented in the main activity
public interface NewsClickListener extends EventListener {
    public abstract void onClick(ClickEvent event);
}


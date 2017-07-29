package com.example.kumar.newsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kumar.newsapp.ClickEvent;
import com.example.kumar.newsapp.NewsClickListener;
import com.example.kumar.newsapp.R;
import com.example.kumar.newsapp.database.CursorNews;
import com.example.kumar.newsapp.models.NewsItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ekta on 6/17/2017.
 */

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.ViewHolder>{

    private static final String TAG = MainViewAdapter.class.getName();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");
    private NewsClickListener listner;
    private CursorNews newsCursor;

    //Implementation of the adapter to load the news from Db

    public MainViewAdapter(CursorNews cursor, NewsClickListener listener) {

        this.newsCursor = cursor;
        this.listner = listener;
    }

    public void swapCursor(CursorNews cursor) {
        this.newsCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        newsCursor.moveToposition(position);
        holder.url = newsCursor.getUrl();
        holder.textViewTitle.setText(newsCursor.getTitle());
        holder.textViewDescription.setText(newsCursor.getDescription());

        //Using Picasso to load the image from db
        Log.e(TAG, "Image url is:  " +newsCursor.getImageUrl());
        Picasso.with(holder.imageView.getContext()).load(newsCursor.getImageUrl())
                .into(holder.imageView);

        try {
            Date publishedDate = dateFormat.parse(newsCursor.getpublishedAt());
            holder.textViewPublishedAt.setText(publishedDate.toString());
        } catch (ParseException e) {
            Log.e(TAG, "Parsing date filed with error: " + e.getClass().getName() +
                    ": " + e.getMessage(), e);
            holder.textViewPublishedAt.setText(newsCursor.getpublishedAt());
        }
    }

    @Override
    public int getItemCount() {
        return newsCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPublishedAt;
        private ImageView imageView;
        private String url;


        public ViewHolder(View itemView) {

            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
            textViewPublishedAt = (TextView) itemView.findViewById(R.id.publishedAt);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (url != null) {
                ClickEvent event = new ClickEvent(v, url);
                listner.onClick(event);
            }
        }
    }
}
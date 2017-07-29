package com.example.kumar.newsapp.models;

/**
 * Created by Ekta on 6/17/2017.
 */

public class NewsItem {
    public static final String TITLE_NAME = "title";
    public static final String DESCRIPTION_NAME = "description";
    public static final String URL_TO_IMAGE_NAME = "urlToImage";
    public static final String URL_NAME = "url";
    public static final String AUTHOR_NAME = "author";
    public static final String PUBLISHED_AT_NAME = "publishedAt";


    private String title;
    private String description;
    private String imageUrl;
    private String url;
    private String author;
    private String publishedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}

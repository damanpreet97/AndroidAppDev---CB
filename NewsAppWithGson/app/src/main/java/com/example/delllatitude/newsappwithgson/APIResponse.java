package com.example.delllatitude.newsappwithgson;

import java.util.ArrayList;

public class APIResponse {

    ArrayList<NewsData> articles = new ArrayList<>();

    public ArrayList<NewsData> getNewsDataArrayList() {
        return articles;
    }
}

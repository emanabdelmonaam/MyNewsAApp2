package com.example.android.mynewsaapp2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class MyNewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public MyNewsLoader(Context context, String vUrl) {
        super(context);
        this.mUrl = vUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        ArrayList<News> myNewsArray = QueryUtils.fetchMyNewsData(mUrl);
        return myNewsArray;

    }
}
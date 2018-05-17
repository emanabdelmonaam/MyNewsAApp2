package com.example.android.mynewsaapp2;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MyNewsLoader extends AsyncTaskLoader<List<MyItemNews>> {

    //Query URL
    private String mUrl;

    public MyNewsLoader(Context context, String vUrl) {
        super(context);
        this.mUrl = vUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // This is on a background thread.
    @Override
    public ArrayList<MyItemNews> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        ArrayList<MyItemNews> myNewsArray = QueryUtils.fetchMyNewsData(mUrl);
        return myNewsArray;

    }
}

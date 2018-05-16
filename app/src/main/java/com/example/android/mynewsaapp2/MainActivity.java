package com.example.android.mynewsaapp2;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<MyItemNews>>{

    private static final String LOG_TAG = MainActivity.class.getName();

    public static  final String USGS_REQUEST_URL =
     "https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=dc97bfb10c9a4874873c1f8698cb54f6";

    private MyNewsAdapter newsAdapter;

    // Constant value for the story loader ID.
    private static final int MYNEWS_LOADER_ID = 1;

    //Current network status.
    // private NetworkInfo networkInfo;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_news_activity_main);

        ListView myNewsListView = (ListView) findViewById(R.id.list);
        //newAdapter
        newsAdapter = new MyNewsAdapter(this, new ArrayList<MyItemNews>());

        myNewsListView.setAdapter(newsAdapter);

        myNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                MyItemNews currentMyNews = newsAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri myNewsUri = Uri.parse(currentMyNews.getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, myNewsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);

            }
        });

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(MYNEWS_LOADER_ID, null, this);

      //  loaderManager.initLoader(MYNEWS_LOADER_ID, null, this);



    }

    @Override
    public Loader<List<MyItemNews>> onCreateLoader(int i, Bundle args) {

        return new MyNewsLoader(this, USGS_REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<MyItemNews>> loader, List<MyItemNews> myNewsArray) {

        newsAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.

        if (myNewsArray != null && !myNewsArray.isEmpty()) {
            newsAdapter.addAll(myNewsArray);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<MyItemNews>> loader) {
        newsAdapter.clear();

    }
}

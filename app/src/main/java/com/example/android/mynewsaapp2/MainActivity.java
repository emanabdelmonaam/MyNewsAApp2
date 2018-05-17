package com.example.android.mynewsaapp2;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<MyItemNews>>,
             SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String LOG_TAG = MainActivity.class.getName();

    public static  final String CNN_REQUEST_URL =
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

        // Find a reference in xml
        ListView myNewsListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        myNewsListView.setEmptyView(mEmptyStateTextView);

       // Create a new adapter that takes an empty list
       newsAdapter = new MyNewsAdapter(this, new ArrayList<MyItemNews>());

       //set the Adapter
        myNewsListView.setAdapter(newsAdapter);

        // Obtain a reference to the SharedPreferences file for this app
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // And register to be notified of preference changes
        // So we know when the user has adjusted the query settings
        prefs.registerOnSharedPreferenceChangeListener(this);

        // Set an item click listener on the ListView,
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

        // Get a reference to the Connectivity Manager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager,


        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(MYNEWS_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(getString(R.string.settings_min_magnitude_key)) ||
                key.equals(getString(R.string.settings_order_by_key))){
            // Clear the ListView as a new query will be kicked off
            newsAdapter.clear();

            // Hide the empty state text view as the loading indicator will be displayed
            mEmptyStateTextView.setVisibility(View.GONE);

            // Show the loading indicator while new data is being fetched
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.VISIBLE);

            // Restart the loader to requery the USGS as the query settings have been updated
            getLoaderManager().restartLoader(MYNEWS_LOADER_ID, null, this);
        }
    }


    @Override
    public Loader<List<MyItemNews>> onCreateLoader(int i, Bundle args) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(CNN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("orderby", orderBy);

        return new MyNewsLoader(this, uriBuilder.toString());

    }

    @Override
    public void onLoadFinished(Loader<List<MyItemNews>> loader, List<MyItemNews> myNewsArray) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

       // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_news);

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.

        if (myNewsArray != null && !myNewsArray.isEmpty()) {
            //newsAdapter.addAll(myNewsArray);
            updateUi(myNewsArray);

        }

    }

    private void updateUi(List<MyItemNews> myNewsArray) {
    }

    @Override
    public void onLoaderReset(Loader<List<MyItemNews>> loader) {
        newsAdapter.clear();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


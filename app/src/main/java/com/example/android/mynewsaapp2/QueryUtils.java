package com.example.android.mynewsaapp2;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){

    }
    /**
     * Returns new URL object from the given string URL.
     */
    public static ArrayList<MyItemNews> fetchMyNewsData(String requestUrl)
    {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
        }

        // Return the list
        return (ArrayList<MyItemNews>) extractFeatureFromJson(jsonResponse);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {

            }
        } catch (IOException e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<MyItemNews> extractFeatureFromJson(String myNewsJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(myNewsJSON)) {
            return null;
        }

        // Create an empty ArrayList
        ArrayList<MyItemNews> myNewsArray = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(myNewsJSON);
            JSONArray newsArrayJson = baseJsonResponse.getJSONArray("articles");

            JSONObject firstFeature = newsArrayJson.getJSONObject(0);

            for (int i = 0; i < newsArrayJson.length(); i++) {

                JSONObject currentJson = newsArrayJson.getJSONObject(i);

                String Title = currentJson.getString("title");
                String Author = currentJson.getString("author");
                String Description = currentJson.getString("description");
                String WebUrl = currentJson.getString("url");
                String PublishedTime = currentJson.getString("publishedAt");

                MyItemNews myItemNews = new MyItemNews(Title, Author, Description, WebUrl, PublishedTime);
                myNewsArray.add(myItemNews);

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        // Return the list of news
        return myNewsArray;
    }

}
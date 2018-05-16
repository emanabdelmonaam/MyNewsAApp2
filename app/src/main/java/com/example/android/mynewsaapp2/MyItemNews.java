package com.example.android.mynewsaapp2;

public class MyItemNews {

    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private int mUrlImage;
    private int mDate;

    public static  final String USGS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=dc97bfb10c9a4874873c1f8698cb54f6";



    public MyItemNews(String vAuthor, String vTitle, String vDescription, String vUrl, int vUrlImage, int vDate) {

        this.mAuthor = vAuthor;
        this.mTitle = vTitle;
        this.mDescription = vDescription;
        this.mUrl = vUrl;
        this.mUrlImage = vUrlImage;
        this.mDate=vDate;
    }


    public String getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }

    public int getmUrlImage() {
        return mUrlImage;
    }

    public int getmDate() {
        return mDate;
    }


}

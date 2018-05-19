package com.example.android.mynewsaapp2;

public class MyItemNews {

    private String mTitle;
    private String mAuthor;
    private String mDescription;
    private String mUrl;
    private String mDate;

    public MyItemNews(String vTitle, String vAuthor, String vDescription, String vUrl, String vDate) {

        this.mTitle = vTitle;
        this.mAuthor = vAuthor;
        this.mDescription = vDescription;
        this.mUrl = vUrl;
        this.mDate=vDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDate() {
        return mDate;
    }

}
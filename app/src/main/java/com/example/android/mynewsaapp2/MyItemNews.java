package com.example.android.mynewsaapp2;

public class MyItemNews {

    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mUrlImage;
    private String mDate;



    public MyItemNews(String vAuthor, String vTitle, String vDescription, String vUrl, String vUrlImage, String vDate) {

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

    public String getmUrlImage() {
        return mUrlImage;
    }

    public String getmDate() {
        return mDate;
    }


}

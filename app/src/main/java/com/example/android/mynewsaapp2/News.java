package com.example.android.mynewsaapp2;

public class News {

    private String mTitle;
    private String mType;
    private String mSection;
    private String mDate;
    private String mAuthor;
    private String mUrl;

    public News(String vTitle, String vType, String vSection, String vDate, String vAuthor, String vUrl) {

        this.mTitle = vTitle;
        this.mType = vType;
        this.mSection = vSection;
        this.mDate=vDate;
        this.mAuthor = vAuthor;
        this.mUrl = vUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmType() {
        return mType;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmUrl() {
        return mUrl;
    }
}
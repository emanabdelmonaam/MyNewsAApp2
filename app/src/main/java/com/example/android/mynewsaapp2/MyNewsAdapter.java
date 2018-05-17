package com.example.android.mynewsaapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class MyNewsAdapter extends ArrayAdapter<MyItemNews> {

    private String author;
    private static final String LOCATION_SEPARATOR = " of ";


    public MyNewsAdapter(Context context, ArrayList<MyItemNews>newsArrayList) {
        super(context, 0, newsArrayList);

    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
// Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item_view, parent, false);
        }

        // Get the {@link news} object located at this position in the list
        MyItemNews currentmyNews = getItem(position);

        // Find the TextView with view ID magnitude
        TextView textViewTitle = (TextView) listItemView.findViewById(R.id.title_one);
        textViewTitle.setText(currentmyNews.getmTitle());

        TextView textViewAuthor =(TextView) listItemView.findViewById(R.id.author_two);
        processAuthorTextView(textViewAuthor, author);

        //textViewAuthor.setText(currentmyNews.getmAuthor());

        TextView textViewDiscription =(TextView) listItemView.findViewById(R.id.description_three);
        textViewDiscription.setText(currentmyNews.getmDescription());

        TextView textViewUrl =(TextView) listItemView.findViewById(R.id.url_four);
        textViewUrl.setText(currentmyNews.getmUrl());

        TextView textDate =(TextView) listItemView.findViewById(R.id.date_five);
        textDate.setText(currentmyNews.getmDate());


        return listItemView;


    }

    //This will check if the author is available and set it.
    //If it is not, then take out the whole author TextView from the list_item
    private void processAuthorTextView(TextView authorTextView, String author) {
        if (author == null) {
            authorTextView.setVisibility(View.GONE);
        } else {
            authorTextView.setVisibility(View.VISIBLE);
            authorTextView.setText(author);
        }
    }

}


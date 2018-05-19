package com.example.android.mynewsaapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MyNewsAdapter extends ArrayAdapter<News> {

       private static final String LOCATION_SEPARATOR = " of ";

    public MyNewsAdapter(Context context, ArrayList<News>newsArrayList) {
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

        // Get the object located at this position in the list
        News currentmyNews = getItem(position);

        // Find the TextView with view ID
        TextView textViewTitle =(TextView) listItemView.findViewById(R.id.text_title);
        textViewTitle.setText(currentmyNews.getmTitle());

        TextView textViewType =(TextView) listItemView.findViewById(R.id.text_section);
        textViewType.setText(currentmyNews.getmType());

        TextView textViewSection =(TextView) listItemView.findViewById(R.id.text_type);
        textViewSection.setText(currentmyNews.getmSection());

        TextView textViewDate =(TextView) listItemView.findViewById(R.id.text_date);
        textViewDate.setText(currentmyNews.getmDate());

        TextView textViewAuthor = (TextView) listItemView.findViewById(R.id.text_author);
        textViewAuthor.setText(currentmyNews.getmAuthor());

        return listItemView;
    }
}
package com.guatemala.api.jsonparse;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by API on 09/06/2015.
 */
public class TweetAdapter extends ArrayAdapter<Tweet> {

    private LayoutInflater inflater;
    private TextView tFirstname;
    private TextView tLastname;
    private TextView tUsername;

    public TweetAdapter(Activity activity, ArrayList<Tweet> items) {
        super(activity, R.layout.row_tweet, items);
        inflater = activity.getWindow().getLayoutInflater();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Tweet tweet = getItem(position);

        if(convertView==null){
            convertView = inflater.from(getContext()).inflate(R.layout.row_tweet, parent, false);
        }
        tFirstname = (TextView) convertView.findViewById(R.id.tvFirstname);
        tFirstname.setText(tweet.firstname);
        tLastname = (TextView) convertView.findViewById(R.id.tvLastname);
        tLastname.setText(tweet.lastname);
        tUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        tUsername.setText(tweet.username);
        return convertView;

    }
}


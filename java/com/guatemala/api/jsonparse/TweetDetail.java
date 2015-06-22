package com.guatemala.api.jsonparse;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by API on 22/06/2015.
 */
public class TweetDetail extends ActionBarActivity {
    private TextView tFirstname;
    private TextView tLastname;
    private TextView tUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        tFirstname = (TextView) findViewById(R.id.tvFirstname);
        tLastname = (TextView) findViewById(R.id.tvLastname);
        tUsername = (TextView) findViewById(R.id.tvUsername);
        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        String username = getIntent().getStringExtra("username");
        tFirstname.setText(firstname);
        tLastname.setText(lastname);
        tUsername.setText(username); 


    }


}
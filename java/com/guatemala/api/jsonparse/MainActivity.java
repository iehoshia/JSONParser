package com.guatemala.api.jsonparse;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    private TweetAdapter adapter;
    ArrayList<Tweet> valores = new ArrayList<Tweet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String TAG ="AsyncTaskParseJson.java";
        String StringUrl = "http://www.apixela.net/android/json.html";
        JSONArray dataJsonArr = null;
        adapter = new TweetAdapter(this, valores);
        setListAdapter(adapter);

        try{
            JsonParser jParser = new JsonParser();
            JSONObject json = jParser.getJSONFromUrl(StringUrl);
            dataJsonArr = json.getJSONArray("Users");

            for (int i=0; i<dataJsonArr.length(); i++){
                JSONObject c = dataJsonArr.getJSONObject(i);

                String firstname = c.getString("firstname");
                String lastname = c.getString("lastname");
                String username = c.getString("username");
                Tweet tweet = new Tweet(firstname,
                        lastname,
                        username);
                valores.add(tweet);
            }
            adapter.notifyDataSetChanged();

        } catch (JSONException e){
            e.printStackTrace();;
        }

        new AsyncTaskParseJson().execute();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Tweet tweet = (Tweet)getListAdapter().getItem(position);
        Intent intent= new Intent(MainActivity.this,TweetDetail.class);
        //Toast.makeText(getApplicationContext(),value.username,Toast.LENGTH_LONG).show();
        intent.putExtra("firstname", tweet.firstname);
        intent.putExtra("lastname", tweet.lastname);
        intent.putExtra("username", tweet.username);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG ="AsyncTaskParseJson.java";
        String StringUrl = "http://www.apixela.net/android/json.html";
        JSONArray dataJsonArr = null;


        @Override
        protected String doInBackground(String... strings) {
                try{
                    JsonParser jParser = new JsonParser();
                    JSONObject json = jParser.getJSONFromUrl(StringUrl);
                    dataJsonArr = json.getJSONArray("Users");

                    for (int i=0; i<dataJsonArr.length(); i++){
                        JSONObject c = dataJsonArr.getJSONObject(i);

                        String firstname = c.getString("firstname");
                        String lastname = c.getString("lastname");
                        String username = c.getString("username");

                        //Log.e(TAG, "firstname: "+ firstname
                        //           + " lastname: " + lastname
                        //            + " username: " + username);

                    }

                } catch (JSONException e){
                    e.printStackTrace();;
                }
            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg){

        }
    }
}

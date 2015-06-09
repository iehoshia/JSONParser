package com.guatemala.api.jsonparse;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView lstView;
    ArrayAdapter<String> adapter;
    ArrayList<String> valors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstView = (ListView) findViewById(R.id.lstJSON);

        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1,
                valors);
        lstView.setAdapter(adapter);


        new AsyncTaskParseJson().execute();
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

                        Log.e(TAG, "firstname: "+ firstname
                                   + " lastname: " + lastname
                                    + " username: " + username);

                    }

                } catch (JSONException e){
                    e.printStackTrace();;
                }
            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg){
            try{
                JsonParser jParser = new JsonParser();
                JSONObject json = jParser.getJSONFromUrl(StringUrl);
                dataJsonArr = json.getJSONArray("Users");

                for (int i=0; i<dataJsonArr.length(); i++){
                    JSONObject c = dataJsonArr.getJSONObject(i);

                    String firstname = c.getString("firstname");
                    String lastname = c.getString("lastname");
                    String username = c.getString("username");

                    valors.add(TAG+ "firstname: "+ firstname
                            + " lastname: " + lastname
                            + " username: " + username);
                    adapter.notifyDataSetChanged();


                }

            } catch (JSONException e){
                e.printStackTrace();;
            }
        }
    }
}

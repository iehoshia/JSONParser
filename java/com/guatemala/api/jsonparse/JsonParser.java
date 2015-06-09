package com.guatemala.api.jsonparse;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by API on 09/06/2015.
 */
public class JsonParser {

    final String TAG = "JsonParser.java";
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONObject getJSONFromUrl(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            is = conn.getInputStream();
            try{

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while( (line = reader.readLine()) != null){
                    sb.append(line + "n");
                }
                json = sb.toString();


            } catch (Exception e){
                Log.e(TAG, "Error convirtiendo resultado" + e.toString());
            }
            is.close();

            try{
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e(TAG, "Error traduciendo datos" + e.toString());
            }

        } catch (Exception e) {
            Log.e(TAG, "Error traduciendo datos" + e.toString());
        }
        return jObj;

    }

}

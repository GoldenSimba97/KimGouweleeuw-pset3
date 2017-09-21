package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kimgo on 21-9-2017.
 */

public class TrackAsyncTask2 extends AsyncTask<String, Integer, String> {
    Context context;
    DataActivity dataAct;

    public TrackAsyncTask2(DataActivity data) {
        this.dataAct = data;
        this.context = this.dataAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Loading data...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper2.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ArrayList<String> list = new ArrayList<>();

        try {
            JSONObject trackStreamObj = new JSONObject(result);
            JSONObject trackObj = trackStreamObj.getJSONObject("track");
//            Log.d("length2", resultObj.toString());
            String name = trackObj.getString("name");
            String artist = trackObj.getString("artist");

            JSONObject albumObj = trackObj.getJSONObject("album");
            String albumTitle = albumObj.getString("title");

            JSONObject toptagsObj = trackObj.getJSONObject("toptags");
            JSONArray tagsObj = toptagsObj.getJSONArray("tags");
//            Log.d("length", num);
            StringBuilder string = new StringBuilder();
            String genre = "";
            for (int i = 1; i < tagsObj.length(); ++i) {
                JSONObject tag = tagsObj.getJSONObject(i);
                genre = tag.getString("name");
                string.append(genre);
                if ((i + 1) < tagsObj.length()) {
                    string.append(", ");
                }
            }
            String genres = string.toString();

            JSONObject wikiObj = trackObj.getJSONObject("wiki");
            String summary = wikiObj.getString("summary");

            list.add(name);
            list.add(artist);
            list.add(albumTitle);
            list.add(genres);
            list.add(summary);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        this.dataAct.trackStartIntent2(list);
    }

}


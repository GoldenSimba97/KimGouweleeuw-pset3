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
        Log.d("hello", result);

        try {
            JSONObject trackStreamObj = new JSONObject(result);
            JSONObject trackObj = trackStreamObj.getJSONObject("track");
//            Log.d("length2", resultObj.toString());
            String name = trackObj.getString("name");

            JSONObject artistObj = trackObj.getJSONObject("artist");
            String artist = artistObj.getString("name");

            list.add(name + " - " + artist);
            list.add("Track name: " + name);
            list.add("Artist: " + artist);

            Log.d("hoi", "hoi");
            JSONObject toptagsObj = trackObj.getJSONObject("toptags");
            JSONArray tagsObj = toptagsObj.getJSONArray("tag");
            Integer len = tagsObj.length();
            Log.d("length", len.toString());
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
            list.add("Genres: " + genres);

            JSONObject albumObj = trackObj.getJSONObject("album");
            if (albumObj.length() != 0) {
                String albumTitle = albumObj.getString("title");
                Log.d("hello", albumTitle);
                list.add("Album " + albumTitle);
            }

//            JSONObject wikiObj = trackObj.getJSONObject("wiki");
//            if (wikiObj.length() != 0) {
//                String summary = wikiObj.getString("summary");
//                list.add(summary);
//            }
        } catch (JSONException e1) {
            e1.printStackTrace();
            Log.d("dead", "dead");
        }
        Log.d("list", list.toString());
        this.dataAct.trackStartIntent2(list);
    }

}


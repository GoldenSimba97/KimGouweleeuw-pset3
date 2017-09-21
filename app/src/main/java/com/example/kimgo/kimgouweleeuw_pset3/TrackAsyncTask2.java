package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * TrackAsyncTask2 created by kimgo on 21-9-2017.
 */

class TrackAsyncTask2 extends AsyncTask<String, Integer, String> {
    private Context context;
    private DataActivity dataAct;

    TrackAsyncTask2(DataActivity data) {
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
            String name = trackObj.getString("name");

            JSONObject artistObj = trackObj.getJSONObject("artist");
            String artist = artistObj.getString("name");

            list.add(name + " - " + artist);
            list.add("Track name: " + name);
            list.add("Artist: " + artist);

            JSONObject toptagsObj = trackObj.getJSONObject("toptags");
            JSONArray tagsObj = toptagsObj.getJSONArray("tag");
            StringBuilder string = new StringBuilder();
            for (int i = 1; i < tagsObj.length(); ++i) {
                JSONObject tag = tagsObj.getJSONObject(i);
                String genre = tag.getString("name");
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
                list.add("Album " + albumTitle);
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        this.dataAct.trackStartIntent2(list);
    }

}


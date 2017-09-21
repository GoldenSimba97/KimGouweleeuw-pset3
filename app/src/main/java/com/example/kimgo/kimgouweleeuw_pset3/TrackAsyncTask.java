package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * TrackAsyncTask created by kimgo on 19-9-2017.
 */

class TrackAsyncTask extends AsyncTask<String, Integer, String> {
    private Context context;
    private MainActivity mainAct;

    TrackAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Searching...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ArrayList<String> list = new ArrayList<>();

        try {
            JSONObject trackStreamObj = new JSONObject(result);
            JSONObject resultObj = trackStreamObj.getJSONObject("results");
            JSONObject trackMatchesObj = resultObj.getJSONObject("trackmatches");
            JSONArray trackObj = trackMatchesObj.getJSONArray("track");
            for (int i = 0; i < trackObj.length(); ++i) {
                JSONObject track = trackObj.getJSONObject(i);
                String name = track.getString("name");
                String artist = track.getString("artist");
                list.add(name + " - " + artist);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.mainAct.trackStartIntent(list);
    }

}

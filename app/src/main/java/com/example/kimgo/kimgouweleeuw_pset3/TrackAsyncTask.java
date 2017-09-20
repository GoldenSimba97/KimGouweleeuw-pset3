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
 * Created by kimgo on 19-9-2017.
 */

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainAct;

    public TrackAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Searching for tracks...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ArrayList<String> list = new ArrayList<>();
//        list.add("halo");
        Log.d("length", list.toString());
        Log.d("length3", "hoi" + result);

        try {
            JSONObject trackStreamObj = new JSONObject(result);
            JSONObject resultObj = trackStreamObj.getJSONObject("results");
            Log.d("length2", resultObj.toString());
            JSONObject trackMatchesObj = resultObj.getJSONObject("trackmatches");
            JSONArray trackObj = trackMatchesObj.getJSONArray("track");
//            Integer len = trackObj.length();
//            String num = len.toString();
//            lala(num);
//            Log.d("length", num);
            for (int i = 0; i < trackObj.length(); ++i) {
                JSONObject track = trackObj.getJSONObject(i);
                String name = track.getString("name");
                String artist = track.getString("artist");
                list.add(name);
                list.add(artist);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.mainAct.trackStartIntent(list);
    }

//    public void lala(String num) {
//        Log.d("length", num);
//    }
}

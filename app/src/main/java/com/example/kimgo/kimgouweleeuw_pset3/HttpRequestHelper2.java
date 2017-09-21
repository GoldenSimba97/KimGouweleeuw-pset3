package com.example.kimgo.kimgouweleeuw_pset3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kimgo on 21-9-2017.
 */

public class HttpRequestHelper2 {

    protected static synchronized String downloadFromServer(String... params) {
        String result = "";
        String chosenTag = params[0];
//        chosenTag.replaceAll("\\s+","");
        String[] split = chosenTag.split(" - ");
        String track = split[0];
        String artist = split[1];

        Log.d("tag", artist);

        URL url = null;
        try {
            url = new URL("http://ws.audioscrobbler.com/2.0/?" + "method=" + "track.getInfo" + "&api_key=" + "3f730f7100c7cbfef7e1b9145c6c6ccb" + "&artist=" + artist + "&track=" + track + "&format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assert url != null;
        Log.d("url", url.toString());

        HttpURLConnection connect;

        if (url != null) {
            try {
                connect = (HttpURLConnection) url.openConnection();

                connect.setRequestMethod("GET");

                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bufferedReader = new BufferedReader
                            (new InputStreamReader(connect.getInputStream()));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

package com.example.kimgo.kimgouweleeuw_pset3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HttpRequestHelper2 created by kimgo on 21-9-2017.
 */

class HttpRequestHelper2 {

    static synchronized String downloadFromServer(String... params) {
        String result = "";
        String chosenTag = params[0];
        String[] split = chosenTag.split(" - ");
        String track = split[0];
        track = track.replaceAll("[^A-Za-z]+", "%20");
        String artist = split[1];
        artist = artist.replaceAll("[^A-Za-z]+", "%20");

        URL url = null;
        try {
            url = new URL("http://ws.audioscrobbler.com/2.0/?" + "method=" + "track.getInfo" + "&api_key=" + "3f730f7100c7cbfef7e1b9145c6c6ccb" + "&artist=" + artist + "&track=" + track + "&format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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

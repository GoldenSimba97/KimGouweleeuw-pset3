package com.example.kimgo.kimgouweleeuw_pset3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * HttpRequestHelper created by kimgo on 19-9-2017.
 */

class HttpRequestHelper {

    static synchronized String downloadFromServer(String... params) {
        String result = "";
        String chosenTag = params[0];

        URL url = null;
        URL url2 = null;
        try {
            url = new URL("http://ws.audioscrobbler.com/2.0/?" + "method=" + "track.search" + "&track=" + chosenTag + "&api_key=" + "3f730f7100c7cbfef7e1b9145c6c6ccb" + "&format=json");
            url2 = new URL("http://ws.audioscrobbler.com/2.0/?" + "method=" + "artist.search" + "&artist=" + chosenTag + "&api_key=" + "3f730f7100c7cbfef7e1b9145c6c6ccb" + "&format=json");
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

        if (url2 != null) {
            try {
                connect = (HttpURLConnection) url2.openConnection();
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

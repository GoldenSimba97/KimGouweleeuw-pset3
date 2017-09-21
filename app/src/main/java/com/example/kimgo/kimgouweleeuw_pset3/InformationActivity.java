package com.example.kimgo.kimgouweleeuw_pset3;

import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InformationActivity extends AppCompatActivity {
    ArrayList<String> trackArray;
    ArrayList<String> listenArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        loadFromSharedPrefs();

        TextView trackProduct = (TextView) findViewById(R.id.track);
//        TextView artistProduct = (TextView) findViewById(R.id.a2);
//        TextView albumProduct = (TextView) findViewById(R.id.a3);
//        TextView genresProduct = (TextView) findViewById(R.id.a4);
//        TextView summaryProduct = (TextView) findViewById(R.id.summaryView);


//        Intent i = getIntent();
//        // getting attached intent data
//        String product = i.getStringExtra("track");

        Bundle extras = getIntent().getExtras();
        trackArray = (ArrayList<String>) extras.getSerializable("track");

        Log.d("array", trackArray.toString());
        assert trackArray != null;
//        for (int i = 0; i < trackArray.size(); ++i) {
//            TextView a1 = (TextView) findViewById(R.id.a1);
//            a1.setText(trackArray.get(i));
//        }

        StringBuilder builder = new StringBuilder();
        for (String details : trackArray) {
            builder.append(details).append("\n");
        }

        trackProduct.setText(builder.toString());

//        trackProduct.setText(trackArray.get(1));
//        artistProduct.setText(trackArray.get(2));
//        albumProduct.setText(trackArray.get(3));
//        genresProduct.setText(trackArray.get(4));
//        summaryProduct.setText(trackArray.get(5));

        findViewById(R.id.addToList).setOnClickListener(new addToList());

//        findViewById(R.id.addToList).setOnClickListener(new addToListenList());
    }

//    private class addToListenList implements View.OnClickListener {
//        @Override public void onClick(View view) {
//
////            Intent intent = new Intent(view.getContext(), MainActivity.class);
////            startActivity(intent);
////            finish();
//        }
//    }

    public class addToList implements View.OnClickListener {
        @Override public void onClick(View view) {
//            ArrayList<String> listenArray = new ArrayList<>();
            String track = trackArray.get(0);
//            listenArray.add(track);
            if (!listenArray.contains(track)) {
                Toast.makeText(getApplicationContext(),"Track added to Listen List",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), ListActivity.class);
                intent.putExtra("listen", track);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(),"Track has already been added to Listen List",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void loadFromSharedPrefs() {
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("listenlist", null);
        assert set != null;
        listenArray = new ArrayList<>(set);

//        String editTextValueRestored = prefs.getString("editTrack", null);

//        if (list != null) {
//            editTrack.setText(editTextValueRestored);
//        }
    }

}

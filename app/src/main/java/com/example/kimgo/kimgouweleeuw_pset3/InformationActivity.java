package com.example.kimgo.kimgouweleeuw_pset3;

import android.app.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {
    ArrayList<String> trackArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        TextView trackProduct = (TextView) findViewById(R.id.trackView);
        TextView artistProduct = (TextView) findViewById(R.id.artistView);
        TextView albumProduct = (TextView) findViewById(R.id.albumView);
        TextView genresProduct = (TextView) findViewById(R.id.genresView);
        TextView summaryProduct = (TextView) findViewById(R.id.summaryView);


//        Intent i = getIntent();
//        // getting attached intent data
//        String product = i.getStringExtra("track");

        Bundle extras = getIntent().getExtras();
        trackArray = (ArrayList<String>) extras.getSerializable("track");

        Log.d("array", trackArray.toString());
        assert trackArray != null;
        trackProduct.setText(trackArray.get(1));
        artistProduct.setText(trackArray.get(2));
        albumProduct.setText(trackArray.get(3));
        genresProduct.setText(trackArray.get(4));
        summaryProduct.setText(trackArray.get(5));

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
            ArrayList<String> listenArray = new ArrayList<>();
            String track = trackArray.get(0);
            listenArray.add(track);
            Toast.makeText(getApplicationContext(),"Track added to Listen List",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), ListActivity.class);
            intent.putExtra("listen", listenArray);
            startActivity(intent);
            finish();
        }
    }

}

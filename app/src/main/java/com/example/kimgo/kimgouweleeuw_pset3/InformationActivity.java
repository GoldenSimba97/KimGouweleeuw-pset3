package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity {

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
        ArrayList<String> trackArray = (ArrayList<String>) extras.getSerializable("track");

//        Log.d("array", trackArray.toString());
        assert trackArray != null;
        trackProduct.setText(trackArray.get(1));
        artistProduct.setText(trackArray.get(2));
        albumProduct.setText(trackArray.get(3));
        genresProduct.setText(trackArray.get(4));
        summaryProduct.setText(trackArray.get(5));

        ArrayList<String> listenArray = new ArrayList<>();

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


}

package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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

        Bundle extras = getIntent().getExtras();
        trackArray = (ArrayList<String>) extras.getSerializable("track");

        assert trackArray != null;

        StringBuilder builder = new StringBuilder();
        for (String details : trackArray) {
            builder.append(details).append("\n");
        }

        trackProduct.setText(builder.toString());

        findViewById(R.id.addToList).setOnClickListener(new addToList());
    }

    private class addToList implements View.OnClickListener {
        @Override public void onClick(View view) {
            String track = trackArray.get(0);
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
        SharedPreferences prefs = this.getSharedPreferences("settings", MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("listenlist", null);
        assert set != null;
        listenArray = new ArrayList<>(set);
    }

}

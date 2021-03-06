package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    TextView tvResult;
    ListView lvItems;
    ArrayList<String> trackArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        final DataActivity dataAct = this;

        tvResult = (TextView) findViewById(R.id.tvFound);
        lvItems = (ListView) findViewById(R.id.listViewID);

        Bundle extras = getIntent().getExtras();
        trackArray = (ArrayList<String>) extras.getSerializable("data");

        makeTrackAdapter();

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String track = ((TextView) view).getText().toString();
                TrackAsyncTask2 asyncTask = new TrackAsyncTask2(dataAct);
                asyncTask.execute(track);
            }
        });
    }

    public void trackStartIntent2(ArrayList<String> track) {
        Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
        intent.putExtra("track", track);
        this.startActivity(intent);
    }

    public void makeTrackAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, trackArray);
        lvItems = (ListView) findViewById(R.id.listViewID);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }
}


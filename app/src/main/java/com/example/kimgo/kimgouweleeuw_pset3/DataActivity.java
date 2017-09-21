package com.example.kimgo.kimgouweleeuw_pset3;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        tvResult = (TextView) findViewById(R.id.tvFound);
        lvItems = (ListView) findViewById(R.id.listViewID);

        Bundle extras = getIntent().getExtras();
        trackArray = (ArrayList<String>) extras.getSerializable("data");

//        tvResult.setText(trackArray.toString());

        makeTrackAdapter();

        // listening to single list item on click
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String track = ((TextView) view).getText().toString();

                TrackAsyncTask2 asyncTask = new TrackAsyncTask2(this);
                asyncTask.execute(track);



            }
        });
    }

    public void trackStartIntent2(ArrayList<String> track) {
//        Log.d("hallo", "hallo2");
        Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
        intent.putExtra("track", track);
        this.startActivity(intent);
    }

    public void makeTrackAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, trackArray);
        lvItems = (ListView) findViewById(R.id.listViewID);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }
}


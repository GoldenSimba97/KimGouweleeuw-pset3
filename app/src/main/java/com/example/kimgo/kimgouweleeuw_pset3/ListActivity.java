package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListActivity extends AppCompatActivity {
    ListView lvItems;
    ArrayList<String> trackArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvItems = (ListView) findViewById(R.id.listViewID);

        Bundle extras = getIntent().getExtras();
        trackArray = (ArrayList<String>) extras.getSerializable("listen");

        makeTrackAdapter2();


    }

    public void saveToSharedPrefs(View view) {
//        String editTextValue = editTrack.getText().toString();

        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(trackArray);
        editor.putStringSet("listenlist", set);
        editor.commit();

//        editor.putString("editTrack", editTextValue);
//        editor.commit();
    }

    public void makeTrackAdapter2() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, trackArray);
        lvItems = (ListView) findViewById(R.id.listViewID);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }
}

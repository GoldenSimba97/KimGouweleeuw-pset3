package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListActivity extends AppCompatActivity {
    ListView lvItems;
    ArrayList<String> listenArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvItems = (ListView) findViewById(R.id.listViewID);

        Bundle extras = getIntent().getExtras();
        listenArray = (ArrayList<String>) extras.getSerializable("listen");

//        saveToSharedPrefs();
        loadFromSharedPrefs();


        makeTrackAdapter2();


    }

    @Override
    protected void onStop() {
        saveToSharedPrefs();
        super.onStop();
    }

    public void saveToSharedPrefs() {
//        String editTextValue = editTrack.getText().toString();

        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

//        Log.d("list", "hello");
        Set<String> set = new HashSet<String>();
        assert listenArray != null;
        set.addAll(listenArray);
        editor.putStringSet("listenlist", set);
        editor.commit();

//        editor.putString("editTrack", editTextValue);
//        editor.commit();
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

    public void makeTrackAdapter2() {
        if (listenArray != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, listenArray);
            lvItems = (ListView) findViewById(R.id.listViewID);
            assert lvItems != null;
            lvItems.setAdapter(arrayAdapter);
        }
    }
}

package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListActivity extends AppCompatActivity {
    ListView lvItems;
    ArrayList<String> listenArray = new ArrayList<>();
    String track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        loadFromSharedPrefs();

        lvItems = (ListView) findViewById(R.id.listViewID);

        Bundle extras = getIntent().getExtras();
        track = (String) extras.getSerializable("listen");

        if (track != "Main") {
            listenArray.add(track);
        }


        Log.d("array", listenArray.toString());

//        saveToSharedPrefs();

        makeTrackAdapter2();

        findViewById(R.id.searchmore).setOnClickListener(new backToMain());

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String track = ((TextView) view).getText().toString();

                Intent intent = new Intent(view.getContext(), DeleteActivity.class);
                intent.putExtra("delete", track);
                startActivity(intent);
                finish();


            }
        });


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

    public class backToMain implements View.OnClickListener {
        @Override public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
//            intent.putExtra("listen", track);
            startActivity(intent);
            finish();
        }
    }
}

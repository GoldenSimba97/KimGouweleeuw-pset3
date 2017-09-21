package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    EditText editTrack;
    MainActivity mainAct;
    ArrayList<String> listenArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mainAct = this;

        editTrack = (EditText) findViewById(R.id.editTrack);
        assert editTrack != null;
        editTrack.setHint("Search");

        findViewById(R.id.search).setOnClickListener(new trackSearch());

        if (listenArray != null) {
            loadFromSharedPrefs();
        }
        findViewById(R.id.goToListen).setOnClickListener(new goToListenList());


    }


    private class trackSearch implements View.OnClickListener {
        @Override public void onClick(View view) {
            String trackSearch = editTrack.getText().toString();
            if (!trackSearch.isEmpty()) {
                TrackAsyncTask asyncTask = new TrackAsyncTask(mainAct);
                asyncTask.execute(trackSearch);

                editTrack.getText().clear();
            }
        }
    }

    public void trackStartIntent(ArrayList<String> trackData) {
        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("data", trackData);
        this.startActivity(dataIntent);
    }


    public void loadFromSharedPrefs() {
        SharedPreferences prefs = this.getSharedPreferences("settings", MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("listenlist", null);
        assert set != null;
        listenArray = new ArrayList<>(set);
    }

    private class goToListenList implements View.OnClickListener {
        @Override public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ListActivity.class);
            intent.putExtra("listen", "Main");
            startActivity(intent);
            finish();
        }
    }

}
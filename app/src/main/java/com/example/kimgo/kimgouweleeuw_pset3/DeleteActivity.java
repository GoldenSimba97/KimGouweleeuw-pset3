package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteActivity extends AppCompatActivity {
    ArrayList<String> listenArray = new ArrayList<>();
    String track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        loadFromSharedPrefs();

        TextView trackView = (TextView) findViewById(R.id.track);

        Bundle extras = getIntent().getExtras();
        track = (String) extras.getSerializable("delete");

        Resources res = getResources();

        String text = res.getString(R.string.delete, track);
        trackView.setText(text);

        findViewById(R.id.delete).setOnClickListener(new deleteTrack());
    }

    private class deleteTrack implements View.OnClickListener {
        @Override public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ListActivity.class);
            listenArray.remove(track);
            saveToSharedPrefs();
            intent.putExtra("listen", "Delete");
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStop() {
        saveToSharedPrefs();
        super.onStop();
    }

    public void saveToSharedPrefs() {
        SharedPreferences prefs = this.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> set = new HashSet<>();
        assert listenArray != null;
        set.addAll(listenArray);
        editor.putStringSet("listenlist", set);
        editor.apply();
    }

    public void loadFromSharedPrefs() {
        SharedPreferences prefs = this.getSharedPreferences("settings", MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("listenlist", null);
        assert set != null;
        listenArray = new ArrayList<>(set);
    }
}

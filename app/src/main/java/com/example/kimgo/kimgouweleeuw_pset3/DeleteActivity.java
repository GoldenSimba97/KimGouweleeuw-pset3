package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public class deleteTrack implements View.OnClickListener {
        @Override public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ListActivity.class);
            listenArray.remove(track);
//            listenArray.remove("Delete");
//            listenArray.remove("Main");
            saveToSharedPrefs();
            Log.d("delete", listenArray.toString());
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
}

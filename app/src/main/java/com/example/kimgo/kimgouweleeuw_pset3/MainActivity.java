package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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

//    public void trackSearch(View view) {
//        String trackSearch = editTrack.getText().toString();
//        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
//        asyncTask.execute(trackSearch);
//
//        editTrack.getText().clear();
//    }

    public class trackSearch implements View.OnClickListener {
        @Override public void onClick(View view) {
            String trackSearch = editTrack.getText().toString();
            TrackAsyncTask asyncTask = new TrackAsyncTask(mainAct);
            asyncTask.execute(trackSearch);

            editTrack.getText().clear();
        }
    }

    public void trackStartIntent(ArrayList<String> trackData) {
        Log.d("hallo", "hallo2");
        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("data", trackData);
        this.startActivity(dataIntent);
    }

//    public void saveToSharedPrefs(View view) {
//        String editTextValue = editTrack.getText().toString();
//
//        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//
//        editor.putString("editTrack", editTextValue);
//        editor.commit();
//    }
//
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

    public class goToListenList implements View.OnClickListener {
        @Override public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ListActivity.class);
            intent.putExtra("listen", "Main");
            startActivity(intent);
            finish();
        }
    }

}
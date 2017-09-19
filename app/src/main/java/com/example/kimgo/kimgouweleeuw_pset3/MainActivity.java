package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTrack;
//    Button go;
//    TextView showResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTrack = (EditText) findViewById(R.id.editTrack);
        assert editTrack != null;
        editTrack.setHint("Search for a track");

//        findViewById(R.id.button2).setOnClickListener(new trackSearch());

//        loadFromSharedPrefs();

//        go = (Button) findViewById(R.id.button2);
//        showResult = (TextView) findViewById(R.id.textView);
//        showResult.setMovementMethod(new ScrollingMovementMethod());
    }

    public void trackSearch(View view) {
        String trackSearch = editTrack.getText().toString();
        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
        asyncTask.execute(trackSearch);

        editTrack.getText().clear();
    }


//    public class trackSearch implements View.OnClickListener {
//        @Override public void onClick(View view) {
//            String trackSearch = editTrack.getText().toString();
//            TrackAsyncTask asyncTask = new TrackAsyncTask(this);
//            asyncTask.execute(trackSearch);
//
//            editTrack.getText().clear();
//        }
//    }

    public void trackStartIntent(ArrayList<String> trackData) {
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
//    public void loadFromSharedPrefs() {
//        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
//
//        String editTextValueRestored = prefs.getString("editTrack", null);
//
//        if (editTextValueRestored != null) {
//            editTrack.setText(editTextValueRestored);
//        }
//    }
}

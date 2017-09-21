package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        TextView trackView = (TextView) findViewById(R.id.track);

        Bundle extras = getIntent().getExtras();
        String track = (String) extras.getSerializable("delete");

        Resources res = getResources();

        String text = res.getString(R.string.delete, track);
        trackView.setText(text);

        findViewById(R.id.delete).setOnClickListener(new deleteTrack());
    }

    public class deleteTrack implements View.OnClickListener {
        @Override public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
//            intent.putExtra("listen", track);
            startActivity(intent);
            finish();
        }
    }
}

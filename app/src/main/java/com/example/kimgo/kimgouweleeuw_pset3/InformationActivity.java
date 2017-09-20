package com.example.kimgo.kimgouweleeuw_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        TextView txtProduct = (TextView) findViewById(R.id.trackView);

        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("track");
        // displaying selected product name
        txtProduct.setText(product);
    }
}

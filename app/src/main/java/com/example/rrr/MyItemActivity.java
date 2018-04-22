package com.example.rrr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MyItemActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String email;
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        String type = "myload";

       BackgroundWorker backgroundWorker = new BackgroundWorker(this);
       backgroundWorker.execute(type, email);

    }

}

package com.example.rrr;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = (TextView) findViewById(R.id.regtext);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }

        });
    }


    public void OnLogin(View view) {

                EditText EmailEt = (EditText)findViewById(R.id.etEmail);
                EditText PasswordEt = (EditText)findViewById(R.id.etPassword);
                String email = EmailEt.getText().toString();
                String password = PasswordEt.getText().toString();
                String type = "login";

                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, email, password);

                }

    }


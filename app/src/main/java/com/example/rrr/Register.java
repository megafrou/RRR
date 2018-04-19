package com.example.rrr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText name, surname, email, password;
    String str_name, str_surname, str_email, str_password;
   // Integer int_telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        email =(EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
    }
     public void OnReg(View view) {
         String str_name = name.getText().toString();
         String str_surname = surname.getText().toString();
         String str_email = email.getText().toString();
         String str_password = password.getText().toString();

         if (str_name.equals("") || str_surname.equals("") || str_email.equals("") || str_password.equals("")) {
             Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_LONG).show();
         } else {
             String type = "register";
             BackgroundWorker backgroundWorker = new BackgroundWorker(this);
             backgroundWorker.execute(type, str_name, str_surname, str_email, str_password);

         }
     }
}

package com.example.rrr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2/12/2017.
 */

public class HomeActivity extends AppCompatActivity {
    ConnectionDetector cd; //αντικείμενο της κλάσης ConnectionDetector

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cd = new ConnectionDetector(this);

        Button btn1 =(Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.supercook.com"));
                startActivity(intent);
            }
        });

        Button btn2 =(Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //  if(cd.isConnected()) {               //η μεταβλητή isConnected που επιστρέφεται είναι αληθής
                    startActivity(new Intent(HomeActivity.this, Login.class));
                   // finish();
                   // return;
               // }
               // else                                  //διαφορετικά θα εμφανιστεί το μνμ που λέει ότι δεν υπάρχει σύνδεση
                // {
                //   Toast.makeText(HomeActivity.this, "Δεν υπάρχει σύνδεση. Παρακαλώ συνδεθείτε για να συνεχίσετε", Toast.LENGTH_SHORT).show();
               // }
            }
        });

        Button btn3 =(Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MapsActivity.class));
            }
        });
    }
}

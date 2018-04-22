package com.example.rrr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Christine Megarchioti on 11/4/2018.
 */

public class MyItemActivityClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_item_click);

        String category, description, condition, date,url, user, myuser;
        Intent intent = getIntent();
        category  = intent.getStringExtra("itclickcategory");
        description = intent.getStringExtra("itclickdescription");
        condition  = intent.getStringExtra("itclickcondition");
        date  = intent.getStringExtra("itclickdate");
        user = intent.getStringExtra("itclickuser");
        url = intent.getStringExtra("itimgurl");
        myuser = intent.getStringExtra("myuser");

        ImageView Imgview = (ImageView) findViewById(R.id.imageView);

        Picasso.with(this).load(url).fit().into(Imgview);


        TextView TvCategory = (TextView) findViewById(R.id.item_category);
        TvCategory.setText(category);

        TextView TvDescription = (TextView) findViewById(R.id.item_description);
        TvDescription.setText(description);

        TextView TvCondition= (TextView) findViewById(R.id.item_condition);
        TvCondition.setText(condition);

        TextView TvDate = (TextView) findViewById(R.id.item_date);
        TvDate.setText(date);
    }
}
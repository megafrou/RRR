package com.example.rrr;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import static com.example.rrr.R.id.item_category;
import static com.example.rrr.R.id.item_description;
import static com.example.rrr.R.id.listview;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    String myemail;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ItemAdapter itemAdapter;
    ListView listView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        listView = (ListView) findViewById(listview);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemAdapter = new ItemAdapter(this, R.layout.activity_item);

        listView.setAdapter(itemAdapter);

        json_string = getIntent().getExtras().getString("json_data");
        myemail = getIntent().getExtras().getString("email");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("items");
            Integer count = 0;

            String filename, path, date, category, description, condition, user;
            while (count < jsonArray.length()) {

                JSONObject JO = jsonArray.getJSONObject(count);
                filename = JO.getString("filename");
                path = JO.getString("path");
                date = JO.getString("submission_date");
                category = JO.getString("category");
                description = JO.getString("description");
                condition = JO.getString("item_condition");
                user = JO.getString("user_id");

                Items items = new Items(filename, path, date, category, description, condition, user);
                itemAdapter.add(items);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              Items listItem = itemAdapter.getItem(position);

              String category = listItem.getCategory();
              String description = listItem.getDescription();
              String condition = listItem.getCondition();
              String date = listItem.getDate();
              String user = listItem.getUser();
              String url = listItem.getImgUrl();

              if (user.equals(myemail))  {

                  Intent intent = new Intent(getApplicationContext(), MyItemActivityClick.class);
                  intent.putExtra("itclickcategory", category);
                  intent.putExtra("itclickdescription", description);
                  intent.putExtra("itclickcondition", condition);
                  intent.putExtra("itclickdate", date);
                  intent.putExtra("itclickuser", user);
                  intent.putExtra("itimgurl", url);
                  intent.putExtra("myuser", myemail);
                  Toast.makeText(getApplicationContext(), "myuser "+myemail, Toast.LENGTH_LONG).show();
                  startActivity(intent);
              }
              else{
                  Intent intent = new Intent(getApplicationContext(), ItemActivityClick.class);
                  intent.putExtra("itclickcategory", category);
                  intent.putExtra("itclickdescription", description);
                  intent.putExtra("itclickcondition", condition);
                  intent.putExtra("itclickdate", date);
                  intent.putExtra("itclickuser", user);
                  intent.putExtra("itimgurl", url);
                  intent.putExtra("myuser", myemail);
                  Toast.makeText(getApplicationContext(), "myuser "+myemail, Toast.LENGTH_LONG).show();
                  startActivity(intent);

              }

       }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.allposts_id){
            Intent intent =  new Intent(DisplayListView.this, ItemActivity.class);
            intent.putExtra("email", myemail);
            startActivity(intent);
            return true;
        }
        if(id == R.id.myposts_id){

            Intent intent =  new Intent(DisplayListView.this, MyItemActivity.class);
            intent.putExtra("email", myemail);
            startActivity(intent);
            return true;
        }
        if(id == R.id.newpost_id){
            Intent intent =  new Intent(DisplayListView.this, NewItem.class);
            intent.putExtra("email", myemail);
            startActivity(intent);
            return true;
        }
        return true;
    }
}


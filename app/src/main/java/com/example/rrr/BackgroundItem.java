package com.example.rrr;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.rrr.R.layout.activity_new_item;

/**
 * Created by user on 19/2/2018.
 */

public class BackgroundItem  extends AsyncTask<String,Void,String> {


    Context context;
    BackgroundItem(Context ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Uploading item. Please wait.... ", Toast.LENGTH_LONG).show();

       // AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Uploading item. Please wait.... ");
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String upload_url = "http://192.168.1.73:8080/upload.php";

        if (type.equals("upload")) {
            try {
                String path = params[1];
                String submission_date = params[2];
                String user_id = params[3];
                String category = params[4];
                String description = params[5];
                String item_condition = params[6];
                String image_data = params[7];

                URL url = new URL(upload_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("image_data", "UTF-8") + "=" + URLEncoder.encode(image_data, "UTF-8") + "&"
                        + URLEncoder.encode("path", "UTF-8") + "=" + URLEncoder.encode(path, "UTF-8") + "&"
                        + URLEncoder.encode("submission_date", "UTF-8") + "=" + URLEncoder.encode(submission_date, "UTF-8") + "&"
                        + URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8")+"&"
                        + URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(category, "UTF-8") + "&"
                        + URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8") + "&"
                        + URLEncoder.encode("item_condition", "UTF-8") + "=" + URLEncoder.encode(item_condition, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return user_id;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {

        Intent intnew = new Intent(BackgroundItem.this.context, ItemActivity.class);
        intnew.putExtra("email", result);
        BackgroundItem.this.context.startActivity(intnew);
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}





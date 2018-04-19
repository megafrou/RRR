package com.example.rrr;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.drawable.ic_menu_gallery;
import static com.example.rrr.R.id.imageView;
import static com.example.rrr.R.layout.activity_new_item;

public class NewItem extends AppCompatActivity implements View.OnClickListener {

    private ImageView camView;
    private String userChoosenTask;
    Bitmap FixBitmap;
    ByteArrayOutputStream byteArrayOutputStream ;
    byte[] byteArray ;
    String ConvertImage ;



    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {

            setContentView(activity_new_item);

            setUI();

            setUITEXT();

        } catch (Exception e) {

            e.printStackTrace();

        }

        Spinner dropdowncat = (Spinner) findViewById(R.id.category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdowncat.setAdapter(adapter);

        dropdowncat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner dropdowncond = (Spinner) findViewById(R.id.condition);
        ArrayAdapter<CharSequence> adaptercond = ArrayAdapter.createFromResource(this, R.array.condition, android.R.layout.simple_spinner_item);
        adaptercond.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdowncond.setAdapter(adaptercond);
        dropdowncond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void setUITEXT() {

    }

    private void setUI() {

        camView = (ImageView) findViewById(R.id.galleryview);

        camView.setOnClickListener(this);

    }


    @Override

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.galleryview:

                selectImage();

                break;

        }
    }


    @Override

    protected void onResume() {

        super.onResume();

    }


    @Override

    protected void onPause() {

        super.onPause();

    }

    @Override

    protected void onDestroy() {

        super.onDestroy();

    }

    private void selectImage() {

        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(NewItem.this);

        builder.setTitle("Add Photo!");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                boolean result = Utility.checkPermission(NewItem.this);

                if (items[item].equals("Take Photo")) {

                    userChoosenTask = "Take Photo";

                    if (result)

                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {

                    userChoosenTask = "Choose from Library";

                    if (result)

                        galleryIntent();

                } else if (items[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }


    private void galleryIntent() {

        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);//

        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

    }

    private void cameraIntent() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, REQUEST_CAMERA);

    }

    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (userChoosenTask.equals("Take Photo"))

                        cameraIntent();

                    else if (userChoosenTask.equals("Choose from Library"))

                        galleryIntent();

                } else {

                    //code for deny

                }

                break;

        }

    }


    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == SELECT_FILE)

                onSelectFromGalleryResult(data);

            else if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);

        }

    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);


        File destination = new File(Environment.getExternalStorageDirectory(),

                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;

        try {

            destination.createNewFile();

            fo = new FileOutputStream(destination);

            fo.write(bytes.toByteArray());

            fo.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        FixBitmap = thumbnail;
        camView.setImageBitmap(thumbnail);

    }

    @SuppressWarnings("deprecation")

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;

        if (data != null) {

            try {

                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

            } catch (IOException e) {

                e.printStackTrace();

            }

        }
        FixBitmap = bm;
        camView.setImageBitmap(bm);
    }

    public void OnPost(View view) {

        Spinner categspin = (Spinner) findViewById(R.id.category);
        Spinner condspin = (Spinner)findViewById(R.id.condition);

            ImageView iv = (ImageView) findViewById(R.id.galleryview);
            Bitmap FixBitmap = ((BitmapDrawable)iv.getDrawable()).getBitmap();


        EditText descriptionEt = (EditText)findViewById(R.id.itdescription);
        String category = categspin.getSelectedItem().toString();
        String condition = condspin.getSelectedItem().toString();
        String description = descriptionEt.getText().toString();
        SimpleDateFormat databaseDateFormate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = databaseDateFormate.format(new Date());
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String path = "http://192.168.1.73:8080/AndroidImageUpload/";
        String type = "upload";
        Log.d("category", category);
        Log.d("condition", condition);
        Log.d("description", description);
        Log.d("currentDate", currentDate);
        Log.d("email", email);
        Log.d("type", type);

        byteArrayOutputStream = new ByteArrayOutputStream();

        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byteArray = byteArrayOutputStream.toByteArray();

        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

        BackgroundItem backgroundItem = new BackgroundItem(this);
        backgroundItem.execute(type, path, currentDate, email, category, description, condition, ConvertImage);

      //  Intent newintent = new Intent(this, DisplayListView.class);
      //  this.startActivity(newintent);

    }
}
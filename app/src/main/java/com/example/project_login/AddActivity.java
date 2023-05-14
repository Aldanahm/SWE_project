package com.example.project_login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

        TextView textView, textView3, textView4, textView5, textView6;
        //ImageView imageView;
        //FloatingActionButton addPhoto;
        //ImageButton pickImg ;
        Button add, view;
        ImageButton back;
        EditText Title, price, size, desc;
        ListView lv_customerList;
        /* Uri uri;
         Bitmap imageToStore;*/
        ArrayAdapter itemArrayAdapter;

        DataBaseHelper dataBaseHelper;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            textView = findViewById(R.id.textView);
            textView.setTextColor(Color.WHITE);
            textView3 = findViewById(R.id.textView3);
            textView3.setTextColor(Color.BLACK);
            textView4 = findViewById(R.id.textView4);
            textView4.setTextColor(Color.BLACK);
            textView5 = findViewById(R.id.textView5);
            textView5.setTextColor(Color.BLACK);
            textView6 = findViewById(R.id.textView6);
            textView6.setTextColor(Color.BLACK);
            //imageView = findViewById(R.id.imageView);
            //addPhoto = findViewById(R.id.addPhoto);
            //pickImg =findViewById(R.id.pickImg);;
            add = findViewById(R.id.add);
            add.setTextColor(Color.WHITE);
            view = findViewById(R.id.view);
            view.setTextColor(Color.WHITE);
            Title = findViewById(R.id.Title);
            price = findViewById(R.id.price);
            size = findViewById(R.id.size);
            desc = findViewById(R.id.desc);
            lv_customerList = findViewById(R.id.lv_customerList);
            back = findViewById(R.id.back);

            dataBaseHelper = new DataBaseHelper(AddActivity.this);



        /*addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImagePicker.with(MainActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        }); */
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddModel addMod = null;

                    try {
                        addMod = new AddModel(-1, Title.getText().toString(), desc.getText().toString(), Integer.parseInt(price.getText().toString()), size.getText().toString());
                        Toast.makeText(AddActivity.this, addMod.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(AddActivity.this);
                    boolean b = dataBaseHelper.addOne(addMod);
                    Toast.makeText(AddActivity.this, "SUCCESS= " + b, Toast.LENGTH_SHORT).show();
                    itemArrayAdapter = new ArrayAdapter<AddModel>(AddActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                    lv_customerList.setAdapter(itemArrayAdapter);
                    // ShowItemsOnListView(dataBaseHelper);

                }
            });
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    extracted();
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(AddActivity.this);

                    //Toast.makeText(AddActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
                    // itemArrayAdapter = new ArrayAdapter<AddModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                    // lv_customerList.setAdapter(itemArrayAdapter);
                }
            });
            lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                private void ShowitemsOnListView(DataBaseHelper dataBaseHelper) {
                    itemArrayAdapter = new ArrayAdapter<AddModel>(AddActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                    lv_customerList.setAdapter(itemArrayAdapter);
                }
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                    AddModel ClickedStudent = (AddModel) adapterView.getItemAtPosition(i);
                    dataBaseHelper.DeleteOne(ClickedStudent);
                    ShowitemsOnListView(dataBaseHelper);
                    Toast.makeText(AddActivity.this, "Deleted" + ClickedStudent.toString(), Toast.LENGTH_SHORT).show();
                }
            });


        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);

            }
        });*/


        }

        private void extracted() {
            itemArrayAdapter = new ArrayAdapter<AddModel>(AddActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
            lv_customerList.setAdapter(itemArrayAdapter);
        }
    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            uri = data.getData();


            //imageView.setImageURI(uri);
            //addPhoto.hide();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                pickImg.setImageBitmap(decodeStream);

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            uri = data.getData();


            //imageView.setImageURI(uri);
            //addPhoto.hide();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                pickImg.setImageBitmap(decodeStream);

            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openGallerie() {
        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT) ;
        intentImg.setType("image/*") ;
        startActivityForResult(intentImg, 100) ;
    }*/


/*
    ByteArrayOutputStream byteArray = new ByteArrayOutputStream() ;
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), Image) ;
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray) ;
                        byte[] img = byteArray.toByteArray() ; */
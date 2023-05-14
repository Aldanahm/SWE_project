package com.example.project_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String ITEMS_TABLE = "Items_Table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "Items_Title";
    public static final String COLUMN_DESCRIPTION = "Items_Desc";
    public static final String COLUMN_PRICE = "Items_Price";
    public static final String COLUMN_SIZE = "Items_Size";

    //public static final String COLUMN_IMAGE = "Items_Image";
    //private ByteArrayOutputStream byteArray ;
    //byte[] img = byteArray.toByteArray() ;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "added_items.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*COLUMN_IMAGE + " BLOB, " +*/
        String createTableStatement = "Create TABLE " + ITEMS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT," + COLUMN_PRICE + " INT, "+ COLUMN_SIZE + " TEXT )"; ;
        db.execSQL(createTableStatement) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(AddModel addMod){
        SQLiteDatabase db = this.getWritableDatabase();
        /*Bitmap imageToStoreBitmap = addMod.getImage() ;
        byteArray = new ByteArrayOutputStream() ;
        imageToStoreBitmap.compress(Bitmap.CompressFormat., 100, byteArray) ;
        img = byteArray.toByteArray();*/

        ContentValues cv = new ContentValues();
        //cv.put(COLUMN_IMAGE, img) ;
        cv.put(COLUMN_TITLE, AddModel.getTitle());
        cv.put(COLUMN_DESCRIPTION, AddModel.getDesc());
        cv.put(COLUMN_PRICE, AddModel.getPrice());
        cv.put(COLUMN_SIZE, AddModel.getSize());
        long insert = db.insert(ITEMS_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean DeleteOne(@NonNull AddModel addMod){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString= "Delete From " + ITEMS_TABLE + " WHERE " + COLUMN_ID + " = " + addMod.getId() ;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{
            // nothing happens. no one is added.
            return false;
        }
        //close
    }
    public List<AddModel> getEveryone() {
        List<AddModel> returnList = new ArrayList<>();
        //get data from DB
        String queryString = "SELECT * FROM " + ITEMS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int itemID = cursor.getInt(0);
                String itemtitle = cursor.getString(1);
                String itemdesc = cursor.getString(2);
                int itemPr = cursor.getInt(3);
                String itemsize = cursor.getString(4);

                AddModel newAdd = new AddModel(itemID, itemtitle, itemdesc, itemPr, itemsize);
                returnList.add(newAdd);
            } while (cursor.moveToNext());
        } else {
// doing nothing
        }
        cursor.close();
        db.close();


        return returnList;
    }

    }





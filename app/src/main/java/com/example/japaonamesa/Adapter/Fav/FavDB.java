package com.example.japaonamesa.Adapter.Fav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavDB extends SQLiteOpenHelper {

    private static final String TAG = "FavDB";
    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "FavDatabase";
    private static String TABLE_NAME = "favoriteTable";

    public static String KEY_ID = "id";
    public static String ITEM_TITLE = "itemName";
    public static String ITEM_IMAGE = "itemImage";
    public static String FAVORITE_STATUS = "fStatus";
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT," + ITEM_TITLE + " TEXT,"
            + ITEM_IMAGE + " TEXT," + FAVORITE_STATUS + " TEXT)";

    public FavDB(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        Log.d(TAG, "FavDB constructor called"); // Log para rastrear a criação do objeto
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "Database created with table: " + TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade called. Old version: " + oldVersion + ", New version: " + newVersion);
    }

    //create empty table
    public void insertEmpty() {
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Log.d(TAG, "Inserting empty values into table");
        try {
        //enter your value
        for (int x = 1 ; x < 11 ; x++) {
            cv.put(KEY_ID, x);
            cv.put(FAVORITE_STATUS, "0");
            long result = database.insert(TABLE_NAME, null, cv);
            Log.d(TAG, "Inserted empty record with id: " + x + ", Result: " + result);
        }
    } catch (Exception e) {
        Log.e(TAG, "Error inserting empty values: ", e);
    } finally {
            database.close(); // Certifique-se de fechar a conexão
            Log.d(TAG, "Database connection closed after inserting empty values");
        }
    }

    //insert data into database
    public void insertIntoTheDatabase(String item_name, int item_image, String id, String fav_status) {
        SQLiteDatabase database;
        database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Log.d(TAG, "Inserting data into table with id: " + id);
        try {
            cv.put(ITEM_TITLE, item_name);
            cv.put(ITEM_IMAGE, item_image);
            cv.put(KEY_ID, id);
            cv.put(FAVORITE_STATUS, fav_status);
            database.insert(TABLE_NAME, null, cv);
            Log.d("FavDB Status", item_name + ", favstatus - " + fav_status + " - . " + cv);
        } catch (Exception e) {
            Log.e(TAG, "Error inserting data: ", e);
        } finally {
            database.close(); // Fechar conexão
            Log.d(TAG, "Database connection closed after inserting data");
        }
    }

    //read all data
    public Cursor read_all_data(String id) {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=" + id + "";
        Log.d(TAG, "Reading data from table with id: " + id);
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(sql, null);
            Log.d(TAG, "Cursor returned with count: " + cursor.getCount());
        } catch (Exception e) {
            Log.e(TAG, "Error reading data: ", e);
        }
        return cursor; // Lembre-se de fechar o cursor após o uso
    }

    //remove line from database
    public void remove_fav(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET " + FAVORITE_STATUS + " ='0' WHERE " + KEY_ID + "=" + id + "";
        Log.d(TAG, "Removing favorite with id: " + id);
        try {
            database.execSQL(sql);
            Log.d(TAG, "Favorite removed for id: " + id);
        } catch (Exception e) {
            Log.e(TAG, "Error removing favorite: ", e);
        } finally {
            database.close(); // Fechar conexão
            Log.d(TAG, "Database connection closed after removing favorite");
        }
    }

    //select all favorite list
    public Cursor select_all_favorite_list() {
        SQLiteDatabase database = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + FAVORITE_STATUS + " ='1'";
        Log.d(TAG, "Selecting all favorite items");
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(sql, null);
            Log.d(TAG, "Cursor returned with favorite count: " + cursor.getCount());
        } catch (Exception e) {
            Log.e(TAG, "Error selecting favorite list: ", e);
        }
        return cursor; // Lembre-se de fechar o cursor após o uso
    }
}

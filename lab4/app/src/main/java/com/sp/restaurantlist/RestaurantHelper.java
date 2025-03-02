package com.sp.restaurantlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "restaurantlist.db";
    private static final int DATABASE_VERSION = 1;

    public RestaurantHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // Corrected to use DATABASE_VERSION
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE restaurants_table (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "restaurantName TEXT, " +
                "restaurantAddress TEXT, " +
                "restaurantTel TEXT, " +
                "restaurantType TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle any future database upgrades here
    }

    // Corrected the method signature to return a Cursor
    public Cursor getAll() {
        return getReadableDatabase().rawQuery(
                "SELECT _id, restaurantName, restaurantAddress, restaurantTel, " +
                        "restaurantType FROM restaurants_table ORDER BY restaurantName", null);
    }
    public Cursor getById(String id)
    {
        String[] args ={id};
        return (getReadableDatabase().rawQuery(
                "SELECT _id, restaurantName, restaurantAddress, restaurantTel," +
                        "restaurantType FROM restaurants_table WHERE _ID = ?",args));
    }

    public void insert(String restaurantName, String restaurantAddress, String restaurantTel, String restaurantType) {
        ContentValues cv = new ContentValues();
        cv.put("restaurantName", restaurantName);
        cv.put("restaurantAddress", restaurantAddress);
        cv.put("restaurantTel", restaurantTel);
        cv.put("restaurantType", restaurantType);

        // Corrected table name to "restaurants_table"
        getWritableDatabase().insert("restaurants_table", null, cv);
    }

    public void update(String id, String restaurantName, String restaurantAddress, String restaurantTel, String restaurantType) {
        ContentValues cv = new ContentValues();
        String[] args = {id};
        cv.put("restaurantName", restaurantName);
        cv.put("restaurantAddress", restaurantAddress);
        cv.put("restaurantTel", restaurantTel);
        cv.put("restaurantType", restaurantType);

        getWritableDatabase().update("restaurants_table", cv, "_ID =?", args); // Corrected table name
    }

    public String getID(Cursor c){
        return(c.getString(0));
    }
    public String getRestaurantName(Cursor c) {
        return c.getString(1);
    }

    public String getRestaurantAddress(Cursor c) {
        return c.getString(2);
    }

    public String getRestaurantTel(Cursor c) {
        return c.getString(3);
    }

    public String getRestaurantType(Cursor c) {
        return c.getString(4);
    }
}

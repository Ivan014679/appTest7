package com.example.pets.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class PetsDB extends SQLiteOpenHelper {
    public PetsDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase pets) {
        pets.execSQL("CREATE TABLE users(id integer primary key autoincrement," +
                " firstname text not null, lastname text not null," +
                " username text not null unique, email text not null unique, password text not null)");
    }

    public boolean insert(String[] data){
        //Let DB read-write
        SQLiteDatabase pets_db = this.getWritableDatabase();
        try {
            //Save data
            ContentValues reg = new ContentValues();
            reg.put("firstname", data[0]);
            reg.put("lastname", data[1]);
            reg.put("username", data[2]);
            reg.put("email", data[3]);
            reg.put("password", data[4]);

            pets_db.insertOrThrow("users", null, reg);

            pets_db.close();
            return true;
        }catch(android.database.sqlite.SQLiteConstraintException ex){
            pets_db.close();
            return false;
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase pets, int i, int i1) {

    }
}

package com.example.pets.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void connection(Context context, int crud){
        //Connection to DB
        PetsDB manager = new PetsDB(context,
                "manager", null, 1);
        //Let DB read-write
        SQLiteDatabase pets_db = manager.getWritableDatabase();
        switch(crud){
            case 0:
            case 1:
                insert();
            default:
                return;
        }
        pets_db.close();
    }

    private void insert(){

    }

    @Override
    public void onUpgrade(SQLiteDatabase pets, int i, int i1) {

    }
}

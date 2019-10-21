package com.example.pets.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pets.classes.User;

import java.util.ArrayList;
import java.util.List;

public class PetsDB extends SQLiteOpenHelper {
    public PetsDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase pets) {
        pets.execSQL("CREATE TABLE users(id integer primary key autoincrement," +
                " firstname text not null, lastname text not null," +
                " email text not null unique, password text not null, birth text," +
                " country integer default -1, phone text," +
                " gender char default '?')");
    }

    public boolean insert(User data){
        //Let DB read-write
        SQLiteDatabase pets_db = this.getWritableDatabase();
        try {
            //Save data
            ContentValues reg = new ContentValues();
            reg.put("firstname", data.getFirstname());
            reg.put("lastname", data.getLastname());
            reg.put("email", data.getEmail());
            reg.put("password", data.getPassword());

            pets_db.insertOrThrow("users", null, reg);

            pets_db.close();
            return true;
        }catch(android.database.sqlite.SQLiteConstraintException ex){
            pets_db.close();
            return false;
        }
    }

    public boolean login(User data){
        //Let DB read-write
        SQLiteDatabase pets_db = this.getWritableDatabase();

        //Get data
        Cursor cursor = pets_db.rawQuery("SELECT * from users where email='" + data.getEmail() + "' and password='" + data.getPassword() + "' limit 1", null);

        boolean login = cursor.getCount() != 0;
        cursor.close();
        return login;
    }

    public List<User> select(char gender){
        List<User> userList = new ArrayList<>();
        //Let DB read
        SQLiteDatabase pets_db = this.getReadableDatabase();
        //Get data
        Cursor cursor;
        if(gender == 'M' || gender == 'F'){
            cursor = pets_db.rawQuery("SELECT * from users where gender='" + gender + "'", null);
        }else{
            cursor = pets_db.rawQuery("SELECT * from users", null);
        }
        //Store all users into a user list
        while (cursor.moveToNext()) {
            userList.add(new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    Integer.parseInt(cursor.getString(6)), cursor.getString(7), cursor.getString(8).charAt(0)));
        }

        cursor.close();
        return userList;
    }

    public User selectOne(String email){
        User user = null;
        //Let DB read
        SQLiteDatabase pets_db = this.getReadableDatabase();
        //Get data
        Cursor cursor = pets_db.rawQuery("SELECT * from users where email='" + email + "' limit 1", null);

        //Store user into a user class
        while (cursor.moveToNext()) {
            user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    Integer.parseInt(cursor.getString(6)), cursor.getString(7), cursor.getString(8).charAt(0));
        }

        cursor.close();
        return user;
    }

    public void update(User user){
        //Let DB read-write
        SQLiteDatabase pets_db = this.getWritableDatabase();

        //Save data
        ContentValues reg = new ContentValues();
        reg.put("firstname", user.getFirstname());
        reg.put("lastname", user.getLastname());
        reg.put("password", user.getPassword());
        reg.put("birth", user.getBirth());
        reg.put("country", user.getCountry());
        reg.put("phone", user.getPhone());
        reg.put("gender", Character.toString(user.getGender()));

        pets_db.update("users", reg, "email='" + user.getEmail() + "'", null);

        pets_db.close();
    }

    public void delete(String email){
        //Let DB read-write
        SQLiteDatabase pets_db = this.getWritableDatabase();

        pets_db.delete("users", "email='" + email + "'", null);
        pets_db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase pets, int i, int i1) {

    }
}

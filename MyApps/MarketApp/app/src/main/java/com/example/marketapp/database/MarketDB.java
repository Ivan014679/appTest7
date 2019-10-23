package com.example.marketapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.marketapp.classes.Product;

import java.util.ArrayList;
import java.util.List;

public class MarketDB extends SQLiteOpenHelper {
    public MarketDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase pets) {
        pets.execSQL("CREATE TABLE products(id integer primary key autoincrement," +
                " name text not null, category text not null," +
                " quantity integer not null)");
    }

    public boolean insert(Product data){
        //Let DB read-write
        SQLiteDatabase market_db = this.getWritableDatabase();
        try {
            //Save data
            ContentValues reg = new ContentValues();
            reg.put("name", data.getName());
            reg.put("category", data.getCategory());
            reg.put("quantity", data.getQuantity());

            market_db.insertOrThrow("products", null, reg);

            market_db.close();
            return true;
        }catch(android.database.sqlite.SQLiteConstraintException ex){
            market_db.close();
            return false;
        }
    }

    public List<Product> select(){
        List<Product> productList = new ArrayList<>();
        //Let DB read
        SQLiteDatabase market_db = this.getReadableDatabase();
        //Get data
        Cursor cursor;

        cursor = market_db.rawQuery("SELECT * from products", null);

        //Store all users into a user list
        while (cursor.moveToNext()) {
            productList.add(new Product(cursor.getString(1), cursor.getString(2),
                    Integer.parseInt(cursor.getString(3))));
        }

        cursor.close();
        return productList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase pets, int i, int i1) {

    }
}

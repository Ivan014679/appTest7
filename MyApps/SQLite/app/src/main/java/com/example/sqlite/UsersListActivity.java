package com.example.sqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sqlite.database.connectionDB;

public class UsersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Connection to DB
        connectionDB connection = new connectionDB(this,
                "manager", null, 1);
        //Let DB read
        SQLiteDatabase databaseDB = connection.getReadableDatabase();
        //Get data
        Cursor cursor = databaseDB.rawQuery("SELECT * from users", null);

        TableLayout ll = findViewById(R.id.userstable);
        while (cursor.moveToNext()) {
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView firstname = new TextView(this);
            TextView lastname = new TextView(this);
            TextView email = new TextView(this);

            firstname.setText(cursor.getString(1));
            lastname.setText(cursor.getString(2));
            email.setText(cursor.getString(3));
            firstname.setBackgroundResource(R.drawable.border);
            lastname.setBackgroundResource(R.drawable.border);
            email.setBackgroundResource(R.drawable.border);
            row.addView(firstname);
            row.addView(lastname);
            row.addView(email);
            ll.addView(row, cursor.getPosition() + 1);
        }

        //Close database
        databaseDB.close();
    }
}

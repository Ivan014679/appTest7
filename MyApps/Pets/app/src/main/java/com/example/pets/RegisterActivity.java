package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.pets.database.PetsDB;

public class RegisterActivity extends AppCompatActivity {
    private EditText fname, lname, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.editTextFName);
        lname = findViewById(R.id.editTextLName);
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
    }

    public void signUp(View view) {
        //Connection to DB
        PetsDB manager = new PetsDB(this,
                "manager", null, 1);
        //Let DB read-write
        SQLiteDatabase pets_db = manager.getWritableDatabase();


    }
}

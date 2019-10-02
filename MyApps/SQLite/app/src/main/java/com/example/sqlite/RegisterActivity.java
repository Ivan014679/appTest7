package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.database.connectionDB;

public class RegisterActivity extends AppCompatActivity {
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.editTextFName);
        lname = findViewById(R.id.editTextLName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
    }

    public void signUp(View view){
        //Connection to DB
        connectionDB connection = new connectionDB(this,
                "manager", null, 1);
        //Let DB read-write
        SQLiteDatabase databaseDB = connection.getWritableDatabase();

        //Get data
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String onlinemail = email.getText().toString();
        String pass = password.getText().toString();

        //Save data
        ContentValues reg = new ContentValues();
        reg.put("firstname", firstname);
        reg.put("lastname", lastname);
        reg.put("email", onlinemail);
        reg.put("password", pass);

        databaseDB.insert("users", null, reg);
        Toast.makeText(this, "Registed", Toast.LENGTH_SHORT);

        //Close database
        databaseDB.close();
        /*Homework
        1. Validate: No repetir el correo, Si ya existe, el sistema debe mostrar un mensaje y redireccionar a LoginActivity
        2. Complete fields
        3. Crear una opción que permita al usuario ver una lista de usuarios
         */
    }
}

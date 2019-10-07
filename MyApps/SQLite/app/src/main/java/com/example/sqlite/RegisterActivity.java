package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        try {
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

            databaseDB.insertOrThrow("users", null, reg);

            Toast.makeText(this, getString(R.string.toastregistered), Toast.LENGTH_SHORT).show();

            //Close database
            databaseDB.close();
        }catch(android.database.sqlite.SQLiteConstraintException ex){
            Toast.makeText(this, getString(R.string.alreadyregistered), Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        /*Homework
        1. Validate: No repetir el correo, Si ya existe, el sistema debe mostrar un mensaje y redireccionar a LoginActivity
        2. Complete fields
        3. Crear una opción que permita al usuario ver una lista de usuarios
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userslist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iteUsersList:
                startActivity(new Intent(getApplicationContext(), UsersListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

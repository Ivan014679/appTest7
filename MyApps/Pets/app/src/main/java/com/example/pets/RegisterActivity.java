package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        //Get data
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String usname = username.getText().toString();
        String onlinemail = email.getText().toString();
        String pass = password.getText().toString();

        if(!firstname.isEmpty() && !lastname.isEmpty() && !usname.isEmpty() && !onlinemail.isEmpty() && !pass.isEmpty()) {
            String[] user = {firstname, lastname, usname, onlinemail, pass};
            PetsDB petsDB = new PetsDB(this,
                    "pets", null, 1);
            if (petsDB.insert(user)) {
                Toast.makeText(this, getString(R.string.toastregistered), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.alreadyregistered), Toast.LENGTH_SHORT).show();
            }
        }else{

        }
    }

    /*Validar el correo con expresión regular @
    Agregar un toggle en el campo contraseña
    Agregar un campo de confirmación de contraseña
    Agregar expresión regular a la contraseña (mayúsculas, minúsculas, números)

    MainMenuActivity

     ___________________:  ------> Opción buscar ---> Nueva actividad que permitirá buscar por código y un update.
    |IMG Name + Lastname|
    |___________________|
    |IMG Name + Lastname|
    |___________________|  -----> Borrar un usuario con un menú contextual

     */
}

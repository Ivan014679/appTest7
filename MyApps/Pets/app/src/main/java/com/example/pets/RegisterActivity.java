package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pets.classes.User;
import com.example.pets.database.PetsDB;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText fname, lname, email, password, cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.editTextFName);
        lname = findViewById(R.id.editTextLName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        cpassword = findViewById(R.id.editTextCPassword);
    }

    public void signUp(View view) {
        //Get data
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String onlinemail = email.getText().toString();
        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();

        if(!firstname.isEmpty() && !lastname.isEmpty() && !onlinemail.isEmpty() && !pass.isEmpty() && !cpass.isEmpty()) {
            String regex_pass = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{5,})";
            String regex_names = "^[\\p{L} .'-]+$";
            if(!Patterns.EMAIL_ADDRESS.matcher(onlinemail.trim()).matches()) {
                Toast.makeText(this, getString(R.string.incorrectemail), Toast.LENGTH_SHORT).show();
            }else if(!Pattern.compile(regex_names).matcher(firstname).matches() || !Pattern.compile(regex_names).matcher(lastname).matches()){
                Toast.makeText(this, getString(R.string.wrongnames), Toast.LENGTH_SHORT).show();
            }else if(!Pattern.compile(regex_pass).matcher(pass).matches() || pass.length() < 5){
                Toast.makeText(this, getString(R.string.wrongpassword), Toast.LENGTH_SHORT).show();
            }else if(!pass.contentEquals(cpass)){
                Toast.makeText(this, getString(R.string.toastpassconfirm), Toast.LENGTH_SHORT).show();
            }else{
                User user = new User(firstname, lastname, onlinemail, pass);
                PetsDB petsDB = new PetsDB(this,
                        "pets", null, 1);
                if (petsDB.insert(user)) {
                    Toast.makeText(this, getString(R.string.toastregistered), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(this, getString(R.string.alreadyregistered), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, getString(R.string.noemptyfields), Toast.LENGTH_SHORT).show();
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

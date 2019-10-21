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

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextE);
        password = findViewById(R.id.editTextP);
    }

    public void signIn(View view){
        //Get data
        String onlinemail = email.getText().toString();
        String pass = password.getText().toString();

        if(!onlinemail.isEmpty() && !pass.isEmpty()) {
            if(Patterns.EMAIL_ADDRESS.matcher(onlinemail.trim()).matches()){
                User user = new User(onlinemail, pass);
                PetsDB petsDB = new PetsDB(this,
                        "pets", null, 1);
                if (petsDB.login(user)) {
                    Toast.makeText(this, getString(R.string.toastlogged), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                } else {
                    Toast.makeText(this, getString(R.string.loginincorrect), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, getString(R.string.incorrectemail), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, getString(R.string.noemptyfields), Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    /* Si el email ya existe, que cargue MainMenu Activity
    Validar email con expresión regular @
    Añadir un toggle en el campo de contraseña
    Colores a los botones
     */
}

package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

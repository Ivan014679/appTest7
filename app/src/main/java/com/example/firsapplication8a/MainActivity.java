package com.example.firsapplication8a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int U1=10;
        int U2=20;
        if(U1==U2){
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() { //Inicia la aplicación
        super.onStart();
        Toast.makeText(this, "La aplicación ha comenzado", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() { //Muestra y va ejecutando la aplicación a la vista del usuario
        super.onResume();
        Toast.makeText(this, "La aplicación es visible ahora", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() { //Cuando el usuario cambia de actividad
        super.onPause();
        Toast.makeText(this, "La aplicación ha sido pausada", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() { //Cuando el usuario cambia de actividad
        super.onStop();
        Toast.makeText(this, "La aplicación ha sido detenida", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "La aplicación ha sido destrozada", Toast.LENGTH_LONG).show();
    }
}

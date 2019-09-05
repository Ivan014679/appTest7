package com.example.firsapplication8a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    int timeout = 5000; // make the activity visible for 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toast.makeText(this, "La aplicación se está creando", Toast.LENGTH_LONG).show(); //Contexto, mensaje, tiempo
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                finish();
                Intent aIntent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(aIntent);
            }
        }, timeout);
    }
}

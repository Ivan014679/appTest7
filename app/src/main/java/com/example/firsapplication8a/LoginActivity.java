package com.example.firsapplication8a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(this, "The App has been created", Toast.LENGTH_LONG).show();
    }

    public void SingIn(View view){
        //redirect depends of login Activity
        String email= "peter@gmail.com";
        String passwd= "123abc";

        a = findViewById(R.id.username);
        b = findViewById(R.id.passw);
        //***************************
        //***************************
        if(passwd != b.toString() && email != a.toString()){
            Toast.makeText(this, "Invalid MainActivity", Toast.LENGTH_LONG).show();
        }else{
            //Redirect to menu Activity
            Intent aIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(aIntent);
        }
    }

    public void SingUp(View view){
        //redirect to SingUp activity
        Intent aIntent = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(aIntent);
    }

}

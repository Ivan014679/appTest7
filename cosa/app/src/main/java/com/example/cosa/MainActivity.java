package com.example.cosa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText a,b,c,d,e;
    private TextView f,g,h,i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=findViewById(R.id.nombre);
        b=findViewById(R.id.apellido);
        c=findViewById(R.id.telefono);
        d=findViewById(R.id.correo);
        e=findViewById(R.id.contrasena);

        f=findViewById(R.id.nombre1);
        g=findViewById(R.id.apellido1);
        h=findViewById(R.id.telefono1);
        i=findViewById(R.id.correo1);
        j=findViewById(R.id.passw);
    }

    public void validate(View view){
        String nombre = a.getText().toString();
        f.setText(nombre);
        String apellido = b.getText().toString();
        g.setText(apellido);
        String telefono = c.getText().toString();
        h.setText(telefono);
        String correo = d.getText().toString();
        i.setText(correo);
        String contrasena = e.getText().toString();
        j.setText(contrasena);
    }
}

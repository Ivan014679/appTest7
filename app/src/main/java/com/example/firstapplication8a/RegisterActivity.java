package com.example.firstapplication8a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText a, b, c, d, e;
    private TextView f, g, h, i, j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        a = findViewById(R.id.firstName);
        b = findViewById(R.id.lastName);
        c = findViewById(R.id.numberPhone);
        d = findViewById(R.id.eMail);
        e = findViewById(R.id.password);

        f = findViewById(R.id.viewFirstName);
        g = findViewById(R.id.viewLastName);
        h = findViewById(R.id.viewPhone);
        i = findViewById(R.id.viewEMail);
        j = findViewById(R.id.viewPassword);
    }

    public void validate(View view){
        String firstName = a.getText().toString();
        String lastName = b.getText().toString();
        String phone = c.getText().toString();
        String eMail = d.getText().toString();
        String password = e.getText().toString();

        f.setText(firstName);
        g.setText(lastName);
        h.setText(phone);
        i.setText(eMail);
        j.setText(password);
    }
}

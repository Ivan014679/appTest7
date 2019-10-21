package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marketapp.classes.Product;
import com.example.marketapp.database.MarketDB;

public class MainActivity extends AppCompatActivity {
    private EditText name, category, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextProduct);
        category = findViewById(R.id.editTextCategory);
        quantity = findViewById(R.id.editTextQuantity);
    }

    public void add(View view){
        //Get data
        String productname = name.getText().toString();
        String productcat = category.getText().toString();
        int amount = Integer.parseInt(quantity.getText().toString());

        Product product = new Product(productname, productcat, amount);
        MarketDB marketDB = new MarketDB(this,
                "pets", null, 1);

        if (marketDB.insert(product)) {
            Toast.makeText(this, "The product has been registered", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            Toast.makeText(this, "The product cannot be registered", Toast.LENGTH_SHORT).show();
        }
    }
}

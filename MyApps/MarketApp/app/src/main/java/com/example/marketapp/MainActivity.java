package com.example.marketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marketapp.classes.Product;
import com.example.marketapp.classes.ProductAdapter;
import com.example.marketapp.database.MarketDB;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText name, category, quantity;
    List<Product> productList;

    private RecyclerView rvProducts;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextProduct);
        category = findViewById(R.id.editTextCategory);
        quantity = findViewById(R.id.editTextQuantity);
        rvProducts = findViewById(R.id.rvProducts);

        MarketDB marketDB = new MarketDB(this,"market", null, 1);
        productList = marketDB.select();

        rvProducts.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        rvProducts.setLayoutManager(lManager);

        adapter = new ProductAdapter(productList, this);

        rvProducts.setAdapter(adapter);
    }

    public void add(View view){
        //Get data
        String productname = name.getText().toString();
        String productcat = category.getText().toString();
        int amount = Integer.parseInt(quantity.getText().toString());

        Product product = new Product(productname, productcat, amount);
        MarketDB marketDB = new MarketDB(this,
                "market", null, 1);

        if (marketDB.insert(product)) {
            Toast.makeText(this, "The product has been registered", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            Toast.makeText(this, "The product cannot be registered", Toast.LENGTH_SHORT).show();
        }
    }
}

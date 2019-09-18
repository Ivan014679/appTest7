package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.id_Txt);
        registerForContextMenu(textView);
    }

    //Show the menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("::: Select a color :::");
        getMenuInflater().inflate(R.menu.colors1, menu);
    }

    //Button actions
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.opt_1 : //Yellow
                Toast.makeText(this, "You've selected yellow color", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt_2 : //Blue
                Toast.makeText(this, "You've selected blue color", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt_3 : //Red
                Toast.makeText(this, "You've selected red color", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt_4 : //Black
                Toast.makeText(this, "You've selected black color", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opt_5 : //White
                Toast.makeText(this, "You've selected white color", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

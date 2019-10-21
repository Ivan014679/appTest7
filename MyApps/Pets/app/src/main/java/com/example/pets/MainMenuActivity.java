package com.example.pets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pets.classes.User;
import com.example.pets.classes.UserAdapter;
import com.example.pets.database.PetsDB;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity {
    List<User> userList;

    private RecyclerView rvUsers;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        rvUsers = findViewById(R.id.rvUsers);

        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        PetsDB petsDB = new PetsDB(this,"pets", null, 1);
        try{
            if(extra.getString("gender") == "M"){
                userList = petsDB.select('M');
            }else if(extra.getString("gender") == "F") {
                userList = petsDB.select('F');
            }
        }catch(java.lang.NullPointerException ex){
            userList = petsDB.select(Character.MIN_VALUE);
        }

        rvUsers.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(lManager);

        adapter = new UserAdapter(userList, this);

        rvUsers.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.iteAllUsers:
                finish();
                startActivity(new Intent(this, MainMenuActivity.class));
                return true;
            case R.id.iteMaleUsers:
                finish();
                intent = new Intent(this, MainMenuActivity.class);
                intent.putExtra("gender", "M");
                startActivity(intent);
                return true;
            case R.id.iteFemaleUsers:
                finish();
                intent = new Intent(this, MainMenuActivity.class);
                intent.putExtra("gender", "F");
                startActivity(intent);
                return true;
            case R.id.iteAboutOf:
                startActivity(new Intent(this, AboutOfActivity.class));
                return true;
            case R.id.iteLogout:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

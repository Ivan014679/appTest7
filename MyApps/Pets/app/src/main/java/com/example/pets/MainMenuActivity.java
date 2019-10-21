package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

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

        PetsDB petsDB = new PetsDB(this,"pets", null, 1);
        userList = petsDB.select(Character.MIN_VALUE);

        rvUsers.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(lManager);

        adapter = new UserAdapter(userList);
        rvUsers.setAdapter(adapter);
    }
}

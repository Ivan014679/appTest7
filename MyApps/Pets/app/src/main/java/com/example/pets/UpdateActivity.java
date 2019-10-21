package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pets.classes.User;
import com.example.pets.database.PetsDB;

import java.util.regex.Pattern;

public class UpdateActivity extends AppCompatActivity {
    private Spinner country, gender;
    private EditText fname, lname, email, password, birth, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ArrayAdapter<CharSequence> adapterc =
                ArrayAdapter.createFromResource(this,
                        R.array.countries_array,
                        android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterg =
                ArrayAdapter.createFromResource(this,
                        R.array.genders_array,
                        android.R.layout.simple_spinner_item);

        fname = findViewById(R.id.etFName);
        lname = findViewById(R.id.etLName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        birth = findViewById(R.id.etBirth);
        phone = findViewById(R.id.etPhone);

        country = findViewById(R.id.sCountry);
        country.setAdapter(adapterc);
        gender = findViewById(R.id.sCountry);
        gender.setAdapter(adapterg);

        Intent intent = this.getIntent();
        Bundle extra = intent.getExtras();
        PetsDB petsDB = new PetsDB(this,
                "pets", null, 1);
        User user = petsDB.selectOne(extra.getString("email"));

        fname.setText(user.getFirstname());
        lname.setText(user.getLastname());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        birth.setText(user.getBirth());
        phone.setText(user.getPhone());

        if(user.getCountry() != -1){
            country.setSelection(user.getCountry());
        }

        if(user.getGender() != '?'){
            gender.setSelection(user.getGender());
        }
    }

    public void update(View view) {
        //Get data
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String onlinemail = email.getText().toString();
        String pass = password.getText().toString();
        String birthdate = birth.getText().toString();
        String phonenumber = phone.getText().toString();
        int countryid = country.getSelectedItemPosition();
        char genderid;
        switch(gender.getSelectedItemPosition()){
            case 0:
                genderid = 'M';
            case 1:
                genderid = 'F';
                default:
                    genderid = '?';
        }

        if(!firstname.isEmpty() && !lastname.isEmpty() && !pass.isEmpty()) {
            String regex_pass = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{5,})";
            String regex_names = "^[\\p{L} .'-]+$";
            if(!Pattern.compile(regex_names).matcher(firstname).matches() || !Pattern.compile(regex_names).matcher(lastname).matches()){
                Toast.makeText(this, getString(R.string.wrongnames), Toast.LENGTH_SHORT).show();
            }else if(!Pattern.compile(regex_pass).matcher(pass).matches() || pass.length() < 5){
                Toast.makeText(this, getString(R.string.wrongpassword), Toast.LENGTH_SHORT).show();
            }else{
                User user = new User(firstname, lastname, onlinemail, pass, birthdate, countryid, phonenumber, genderid);
                PetsDB petsDB = new PetsDB(this,
                        "pets", null, 1);
                petsDB.update(user);
                Toast.makeText(this, getString(R.string.toastupdated), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
            }
        }else{
            Toast.makeText(this, getString(R.string.noemptyfields), Toast.LENGTH_SHORT).show();
        }
    }
}

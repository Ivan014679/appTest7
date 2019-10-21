package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pets.classes.DatePickerFragment;
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

        adapterc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fname = findViewById(R.id.etFName);
        lname = findViewById(R.id.etLName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        birth = findViewById(R.id.etBirth);
        phone = findViewById(R.id.etPhone);

        country = findViewById(R.id.sCountry);
        country.setAdapter(adapterc);
        gender = findViewById(R.id.sGender);
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

        switch(user.getGender()){
            case 'M':
                gender.setSelection(1);
                break;
            case 'F':
                gender.setSelection(2);
                break;
            default:
                gender.setSelection(0);
                break;
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
        if (gender.getSelectedItemPosition() == 1) {
            genderid = 'M';
        }else if(gender.getSelectedItemPosition() == 2){
            genderid = 'F';
        }else{
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etBirth:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
                birth.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}

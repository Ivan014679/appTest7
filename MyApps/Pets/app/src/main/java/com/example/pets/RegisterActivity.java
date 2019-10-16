package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pets.database.PetsDB;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText fname, lname, username, email, password, cpassword;
    private ImageView eyepassword, eyecpassword;
    private String regex_email = "[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.editTextFName);
        lname = findViewById(R.id.editTextLName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        cpassword = findViewById(R.id.editTextCPassword);
        eyepassword = findViewById(R.id.imageEyePassword);
        eyecpassword = findViewById(R.id.imageEyeCPassword);

        eyepassword.setVisibility(View.GONE);
        eyecpassword.setVisibility(View.GONE);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        cpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(password.getText().length() > 0){
                    eyepassword.setVisibility(View.VISIBLE);
                }else{
                    eyepassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(cpassword.getText().length() > 0){
                    eyecpassword.setVisibility(View.VISIBLE);
                }else{
                    eyecpassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        eyepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eyepassword.getBackground() == ResourcesCompat.getDrawable(getResources(), R.drawable.ic_eye_show, null)){
                    eyepassword.setBackgroundResource(R.drawable.ic_eye_hide);
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    password.setSelection(password.length());
                }else{
                    eyepassword.setBackgroundResource(R.drawable.ic_eye_show);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                }
            }
        });

        eyecpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eyecpassword.getBackground() == ResourcesCompat.getDrawable(getResources(), R.drawable.ic_eye_show, null)){
                    eyecpassword.setBackgroundResource(R.drawable.ic_eye_hide);
                    cpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    cpassword.setSelection(cpassword.length());
                }else{
                    eyecpassword.setBackgroundResource(R.drawable.ic_eye_show);
                    cpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    cpassword.setSelection(cpassword.length());
                }
            }
        });
    }

    public void signUp(View view) {
        //Get data
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String onlinemail = email.getText().toString();
        String pass = password.getText().toString();
        String cpass = cpassword.getText().toString();

        if(!firstname.isEmpty() && !lastname.isEmpty() && !onlinemail.isEmpty() && !pass.isEmpty() && !cpass.isEmpty()) {
            Pattern patron = Pattern.compile(regex_email);
            if(!patron.matcher(onlinemail.trim()).matches()) {
                Toast.makeText(this, getString(R.string.incorrectemail), Toast.LENGTH_SHORT).show();
            }else if(pass != cpass){
                Toast.makeText(this, getString(R.string.toastpassconfirm), Toast.LENGTH_SHORT).show();
            }else{
                String[] user = {firstname, lastname, onlinemail, pass};
                PetsDB petsDB = new PetsDB(this,
                        "pets", null, 1);
                if (petsDB.insert(user)) {
                    Toast.makeText(this, getString(R.string.toastregistered), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.alreadyregistered), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(this, getString(R.string.noemptyfields), Toast.LENGTH_SHORT).show();
        }
    }

    /*Validar el correo con expresión regular @
    Agregar un toggle en el campo contraseña
    Agregar un campo de confirmación de contraseña
    Agregar expresión regular a la contraseña (mayúsculas, minúsculas, números)

    MainMenuActivity

     ___________________:  ------> Opción buscar ---> Nueva actividad que permitirá buscar por código y un update.
    |IMG Name + Lastname|
    |___________________|
    |IMG Name + Lastname|
    |___________________|  -----> Borrar un usuario con un menú contextual

     */
}

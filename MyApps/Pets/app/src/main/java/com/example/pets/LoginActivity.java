package com.example.pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private ImageView eyepassword;
    private String regex_email = "[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextE);
        password = findViewById(R.id.editTextP);
        eyepassword = findViewById(R.id.imageEyeP);

        eyepassword.setVisibility(View.GONE);

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

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
    }

    public void signIn(View view){
        //Get data
        String onlinemail = email.getText().toString();
        String pass = password.getText().toString();

        if(!onlinemail.isEmpty() && !pass.isEmpty()) {
            Pattern patron = Pattern.compile(regex_email);
            if(patron.matcher(onlinemail.trim()).matches()){
                String[] user = {onlinemail, pass};
                PetsDB petsDB = new PetsDB(this,
                        "pets", null, 1);
                if (petsDB.login(user)) {
                    Toast.makeText(this, getString(R.string.toastlogged), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));
                } else {
                    Toast.makeText(this, getString(R.string.loginincorrect), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, getString(R.string.incorrectemail), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, getString(R.string.noemptyfields), Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    /* Si el email ya existe, que cargue MainMenu Activity
    Validar email con expresión regular @
    Añadir un toggle en el campo de contraseña
    Colores a los botones
     */
}

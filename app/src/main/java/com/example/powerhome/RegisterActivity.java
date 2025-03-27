package com.example.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstnameInput, lastnameInput, emailInput, passwordInput, numberInput;
    private Spinner numberSpinner;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des champs
        firstnameInput = findViewById(R.id.firstname_input);
        lastnameInput = findViewById(R.id.lastname_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        numberInput = findViewById(R.id.number_input);
        numberSpinner = findViewById(R.id.number_spinner);
        registerButton = findViewById(R.id.register_btn);

        // Configuration du Spinner
        String[] items = {"+1", "+33", "+32", "+41", "+44", "+49",
                "+34", "+39", "+91", "+86", "+81",
                "+212", "+213", "+216", "+221", "+225"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        numberSpinner.setAdapter(adapter);
        numberSpinner.setSelection(1);

        // Ajouter l'événement de validation
        registerButton.setOnClickListener(v -> validateInputs());

    }

    private void validateInputs() {
        String firstname = firstnameInput.getText().toString().trim();
        String lastname = lastnameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();
        String number = numberInput.getText().toString();

        // Regex pour la validation
        Pattern namePattern = Pattern.compile("^[A-Za-z]{2,25}$");
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        boolean erreurs = false;

        if (!namePattern.matcher(firstname).matches()) {
            firstnameInput.setError("Le prénom doit contenir entre 2 et 25 lettres alphabétiques");
            erreurs =true;
        }

        if (!namePattern.matcher(lastname).matches()) {
            lastnameInput.setError("Le nom doit contenir entre 2 et 25 lettres alphabétiques");
            erreurs =true;
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Veuillez entrer un email valide");
            erreurs =true;
        }

        if (!passwordPattern.matcher(password).matches()) {
            passwordInput.setError("Le mot de passe doit contenir au moins 8 caractères, avec une lettre, un chiffre et un symbole.");
            erreurs =true;
        }

        if (TextUtils.isEmpty(number) || number.length() < 6 || number.length() > 15) {
            numberInput.setError("Numéro invalide (6 à 15 chiffres)");
            erreurs =true;
        }
        if(!erreurs){
            Toast toast = Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);

        }
    }
}
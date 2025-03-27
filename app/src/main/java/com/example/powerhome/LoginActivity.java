package com.example.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emailInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        Button b = (Button) findViewById(R.id.create_account_btn);
        registerButton = findViewById(R.id.login_btn);
        registerButton.setOnClickListener(v -> validateInputs());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
    });
}
    public void onForgotPasswordClick(View view) {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }
    public void onClickCreateAccount(View view) {

        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }
    private void validateInputs() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();
        boolean erreurs = false;
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Veuillez entrer un email valide");
            erreurs =true;
        }

        if (!passwordPattern.matcher(password).matches()) {
            passwordInput.setError("Le mot de passe doit contenir au moins 8 caractères, avec une lettre, un chiffre et un symbole.");
            erreurs =true;
        }
        if(!erreurs){
            Toast toast = Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT);
            toast.show();


            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("password", password);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(bundle);

            startActivity(intent);

        }
    }
}
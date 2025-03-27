package com.example.powerhome;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText newPasswordInput, confirmPasswordInput;
    private Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reset_password), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        newPasswordInput = findViewById(R.id.new_password_input);
        confirmPasswordInput = findViewById(R.id.confirm_password_input);
        resetPasswordButton = findViewById(R.id.reset_password_btn);

        // Gérer le clic sur le bouton de réinitialisation
        resetPasswordButton.setOnClickListener(v -> {
            String newPassword = newPasswordInput.getText().toString();
            String confirmPassword = confirmPasswordInput.getText().toString();

            if (isValidPassword(newPassword, confirmPassword)) {
                // Procéder à la réinitialisation du mot de passe
                // Exemple : appeler une API ou afficher un message de succès
                Toast.makeText(ResetPasswordActivity.this, "Votre mot de passe a été réinitialisé.", Toast.LENGTH_SHORT).show();
                // Optionnel : Retour à la page de connexion après succès
                finish();
            } else {
                Toast.makeText(ResetPasswordActivity.this, "Les mots de passe ne correspondent pas ou sont invalides.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean isValidPassword(String newPassword, String confirmPassword) {
        // Vérification de la validité des mots de passe
        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }
        return newPassword.equals(confirmPassword);
    }

    // Méthode pour revenir à la page de connexion
    public void onBackToLoginClick(View view) {
        finish(); // Ferme l'activité actuelle et revient à la précédente
    }
    }

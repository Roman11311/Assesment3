package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    EditText usernameEditText, emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validate input fields
                if (isValidInput(username, email, password)) {
                    // Pass user data to the login activity
                    Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    loginIntent.putExtra("username", username);
                    loginIntent.putExtra("email", email);
                    loginIntent.putExtra("password", password);
                    startActivity(loginIntent);
                    finish(); // Finish the current activity to prevent going back to it
                } else {
                    // Show error message indicating invalid input
                    Toast.makeText(RegistrationActivity.this, "Please fill out all fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to validate input fields
    private boolean isValidInput(String username, String email, String password) {
        // Check if any field is empty
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            return false;
        }
        // Check if email is valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }
        // Add more validation rules as needed
        return true;
    }
}

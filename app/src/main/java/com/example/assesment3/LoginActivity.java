package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText usernameOrEmailEditText, passwordEditText;

    // Registered credentials from RegistrationActivity
    String registeredUsername, registeredEmail, registeredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameOrEmailEditText = findViewById(R.id.usernameOrEmail);
        passwordEditText = findViewById(R.id.password);

        // Get the values passed from RegistrationActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            registeredUsername = extras.getString("username");
            registeredEmail = extras.getString("email");
            registeredPassword = extras.getString("password");
        }

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username/email and password
                String enteredUsernameOrEmail = usernameOrEmailEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Check if entered credentials match the registered credentials
                if ((registeredUsername != null && registeredUsername.equals(enteredUsernameOrEmail)) ||
                        (registeredEmail != null && registeredEmail.equals(enteredUsernameOrEmail))) {
                    if (registeredPassword != null && registeredPassword.equals(enteredPassword)) {
                        // If credentials match, navigate to the home page
                        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                        finish(); // Finish the current activity to prevent going back to it
                    } else {
                        // Show error message for incorrect password
                        Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show error message for incorrect username/email
                    Toast.makeText(LoginActivity.this, "Incorrect username or email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView registerTextView = findViewById(R.id.textViewRegister);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the registration page
                Intent registerIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });
    }
    private void loginSuccess() {
        // Store user session data (e.g., username, email)
        // Redirect user to HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish(); // Close the login activity to prevent going back
    }
}

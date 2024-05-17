package com.example.assesment3;

import android.content.Intent;
import android.content.SharedPreferences;
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

    // Reference to SharedPreferences
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameOrEmailEditText = findViewById(R.id.usernameOrEmail);
        passwordEditText = findViewById(R.id.password);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username/email and password
                String enteredUsernameOrEmail = usernameOrEmailEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Retrieve registered username/email and password from SharedPreferences
                String registeredUsername = sharedPreferences.getString("username", "");
                String registeredEmail = sharedPreferences.getString("email", "");
                String registeredPassword = sharedPreferences.getString("password", "");

                // Debugging messages
                System.out.println("Entered Username/Email: " + enteredUsernameOrEmail);
                System.out.println("Entered Password: " + enteredPassword);
                System.out.println("Registered Username: " + registeredUsername);
                System.out.println("Registered Email: " + registeredEmail);
                System.out.println("Registered Password: " + registeredPassword);

                // Check if entered credentials match the registered credentials
                if ((registeredUsername.equals(enteredUsernameOrEmail) || registeredEmail.equals(enteredUsernameOrEmail))
                        && registeredPassword.equals(enteredPassword)) {
                    // If credentials match, login success
                    loginSuccess(registeredUsername, registeredEmail);
                    } else {
                    // Show error message for incorrect username/email
                    displayErrorMessage("Incorrect username/email or password");

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

    private void loginSuccess(String username, String email) {
        // Store user session data (e.g., username, email)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.apply();

        // Redirect user to HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("email", email);
        startActivity(intent);
        finish(); // Close the login activity to prevent going back
    }

    // Method to display error message
    private void displayErrorMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}

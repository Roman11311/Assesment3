package com.example.assesment3;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.preference.PreferenceManager;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the user is already logged in
        if (userSessionExists()) {
            // Redirect user to HomeActivity
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Close the launcher activity
        } else {
            // Continue with the registration flow
            Button startRegistrationButton = findViewById(R.id.button);
            startRegistrationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start RegistrationActivity when the button is clicked
                    Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private boolean userSessionExists() {
        // Retrieve user session data (e.g., from SharedPreferences)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Check if the user is logged in
        return sharedPreferences.getBoolean("isLoggedIn", false); // "isLoggedIn" is just an example, replace it with your actual key
    }
}

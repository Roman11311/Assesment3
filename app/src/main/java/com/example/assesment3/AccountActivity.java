package com.example.assesment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {
    // Declare TextViews to display username and email
   private TextView usernameTextView, emailTextView;
    private SharedPreferences sharedPref;
    private static final String TAG = "AccountActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Initialize SharedPreferences
        sharedPref = getSharedPreferences("user_info", MODE_PRIVATE);

        // Find the TextViews for username and email
        usernameTextView = findViewById(R.id.profile_name);
        emailTextView = findViewById(R.id.profile_email);

        // Retrieve username and email from Shared Preferences (if saved)
        String username = sharedPref.getString("username", null);
        String email = sharedPref.getString("email", null);

        // Log the retrieved values for debugging purposes
        Log.d(TAG, "Username: " + username);
        Log.d(TAG, "Email: " + email);

        // Set username and email to TextViews (if retrieved)
        if (username != null && email != null) {
            usernameTextView.setText(username);
            emailTextView.setText(email);
        } else {
            Log.d(TAG, "Username or Email not found in SharedPreferences");
        }

        // Find the ImageButton views
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton cartButton = findViewById(R.id.cartButton);
        ImageButton searchButton = findViewById(R.id.searchButton);

        // Find the LinearLayout views for buttons
        LinearLayout purchaseButtonLayout = findViewById(R.id.purchaseButtonLayout);
        LinearLayout logoutButtonLayout = findViewById(R.id.logoutButtonLayout);

        // Set OnClickListener for the purchaseButtonLayout
        purchaseButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to purchase history page
                Intent intent = new Intent(AccountActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the logoutButtonLayout
        logoutButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to login page
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it
            }
        });

        // Set OnClickListener for the homeButton, cartButton, and searchButton
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the HomeActivity
                Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the CartActivity
                Intent intent = new Intent(AccountActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the SearchActivity
                Intent intent = new Intent(AccountActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}

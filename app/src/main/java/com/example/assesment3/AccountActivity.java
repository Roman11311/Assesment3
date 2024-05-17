package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {
    // Declare TextViews to display username and email
    TextView usernameTextView, emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Receive username and email extras from LoginActivity
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");

        // Find the ImageButton views
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton cartButton = findViewById(R.id.cartButton);
        ImageButton searchButton = findViewById(R.id.searchButton);
        ImageButton accountButton = findViewById(R.id.accountButton);

        // Find the LinearLayout views for buttons
        LinearLayout purchaseButtonLayout = findViewById(R.id.purchaseButtonLayout);
        LinearLayout logoutButtonLayout = findViewById(R.id.logoutButtonLayout);


        // Set username and email to TextViews
        TextView usernameTextView = findViewById(R.id.profile_name);
        TextView emailTextView = findViewById(R.id.profile_email);
        usernameTextView.setText(username);
        emailTextView.setText(email);

        // Set OnClickListener for the purchaseButtonLayout
        purchaseButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to purchase history page
                Intent intent = new Intent(AccountActivity.this, PurchaseHistoryActivity.class);
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

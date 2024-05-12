package com.example.assesment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_item);

        // Retrieve the product ID passed from the HomeActivity
        int productId = getIntent().getIntExtra("productId", -1);


        // Set OnClickListener for the menu button

        ImageView menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenuVisibility();
            }
        });
    }

    private void logout() {
        // Clear any session data (e.g., shared preferences, database)
        // Navigate to the login page
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Close the current activity to prevent going back
    }

    private void toggleMenuVisibility() {
        LinearLayout menuLayout = findViewById(R.id.menuLayout);
        View overlay = findViewById(R.id.overlay);

        // Check if the menu is already visible
        if (menuLayout.getVisibility() == View.VISIBLE) {
            menuLayout.setVisibility(View.GONE);
            overlay.setVisibility(View.GONE);
        } else {
            menuLayout.setVisibility(View.VISIBLE);
            overlay.setVisibility(View.VISIBLE);
            // Set background color to light purple when menu becomes visible
            menuLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_purple));
        }
    }
}

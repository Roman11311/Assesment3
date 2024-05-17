package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class HuaweiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huawei);
        Log.d("HuaweiActivity", "onCreate called");

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

        // Retrieve the product ID passed from the HomeActivity
        int productId = getIntent().getIntExtra("productId", -1);



        // Set OnClickListener for the home button
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HuaweiActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the cart button
        ImageView cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HuaweiActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the "Add to Cart" button
        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the product name and price
                String productName = "Huawei Pura 70 Pro 5G"; // product name
                double productPrice = 1530; // product price


                // Create an intent to navigate to the MyCartActivity
                Intent intent = new Intent(HuaweiActivity.this, MyCartActivity.class);
                // Pass the product name and price as extras
                intent.putExtra("huaweiProductName", productName);
                intent.putExtra("huaweiProductPrice", productPrice);
                // Start the MyCartActivity
                startActivity(intent);
            }
        });

    // Set click listener for menu button
    ImageView menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Inflate the popup menu from the layout
            PopupMenu popupMenu = new PopupMenu(HuaweiActivity.this, v);
            popupMenu.getMenuInflater().inflate(R.menu.nav_menu, popupMenu.getMenu());

            // Handle menu item clicks
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.purchaseButton) {
                        // Handle purchase history click
                        return true;
                    } else if (id == R.id.logoutButton) {
                        // Handle logout click
                        logout(); // Call logout method
                        return true;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }
    });
}

    // Logout method
    private void logout() {
        // Navigate to the LoginActivity without clearing session data
        Intent loginIntent = new Intent(HuaweiActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Close HomeActivity after starting LoginActivity
    }
}
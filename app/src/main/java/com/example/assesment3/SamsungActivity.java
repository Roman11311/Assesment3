package com.example.assesment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;

public class SamsungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.samsung);
        Log.d("SamsungActivity", "onCreate called");

        // Initialize the Spinner with quantity options
        Spinner quantitySpinner = findViewById(R.id.quantity_spinner);
        String[] quantityOptions = new String[]{"1", "2", "3", "4", "5"};

        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quantityOptions);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);

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
                Intent intent = new Intent(SamsungActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the cart button
        ImageView cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SamsungActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the "Add to Cart" button
        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the product name, price, and quantity
                String productName = "Samsung Galaxy S24 Ultra 5G";
                double productPrice = 2400.00;
                int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());

                // Create an intent to navigate to the MyCartActivity
                Intent intent = new Intent(SamsungActivity.this, MyCartActivity.class);
                // Pass the product name, price, and quantity as extras
                intent.putExtra("samsungProductName", productName);
                intent.putExtra("samsungProductPrice", productPrice);
                intent.putExtra("samsungProductQuantity", quantity);
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
                PopupMenu popupMenu = new PopupMenu(SamsungActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.nav_menu, popupMenu.getMenu());

                // Handle menu item clicks
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.purchaseButton) {
                            // Navigate to OrderHistoryActivity
                            Intent intent = new Intent(SamsungActivity.this, OrderHistoryActivity.class);
                            startActivity(intent);
                            return true;
                        } else if (id == R.id.logoutButton) {
                            // Handle logout click
                            logout();
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
        Intent loginIntent = new Intent(SamsungActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Close SamsungActivity after starting LoginActivity
    }
}
package com.example.assesment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class SamsungActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.samsung);

        // Initialize the Spinner with quantity options
        Spinner quantitySpinner = findViewById(R.id.quantity_spinner);
        String[] quantityOptions = new String[]{"1", "2", "3", "4", "5"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quantityOptions);
        // Specify the layout to use when the list of choices appears
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
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


        // Set OnClickListener for the menu button
        ImageView menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenuVisibility();
            }
        });

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
                // Get the product name and price
                String productName = "Samsung Galaxy S24 Ultra 5G"; // product name
                double productPrice = 2400.00; // product price
                int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());

                // Create an intent to navigate to the MyCartActivity
                Intent intent = new Intent(SamsungActivity.this, MyCartActivity.class);
                // Pass the product name and price as extras
                intent.putExtra("samsungProductName", productName);
                intent.putExtra("samsungProductPrice", productPrice);
                intent.putExtra("samsungProductQuantity", quantity);
                // Start the MyCartActivity
                startActivity(intent);
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
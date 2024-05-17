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

public class IphoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iphone);

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

        // Set OnClickListener for the home button
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IphoneActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the cart button
        ImageView cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IphoneActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the "Add to Cart" button
        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the product name and price
                String productName = "Iphone 15 Pro Max 5G"; // product name
                double productPrice = 2199.99; // product price
                int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());

                // Create an intent to navigate to the MyCartActivity
                Intent intent = new Intent(IphoneActivity.this, MyCartActivity.class);
                // Pass the product name and price as extras
                intent.putExtra("iphoneProductName", productName);
                intent.putExtra("iphoneProductPrice", productPrice);
                intent.putExtra("iphoneProductQuantity", quantity);
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


}
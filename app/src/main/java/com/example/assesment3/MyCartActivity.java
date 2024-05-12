package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class MyCartActivity extends AppCompatActivity {

    private static ArrayList<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

        Button proceedToCheckoutButton = findViewById(R.id.checkoutButton);
        proceedToCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move to CheckoutActivity
                Intent intent = new Intent(MyCartActivity.this, CheckoutActivity.class);
                intent.putExtra("cartItems", cartItems); // Pass the ArrayList<CartItem> directly
                intent.putExtra("totalPrice", calculateTotalPrice());
                startActivity(intent);
            }
        });


        // Retrieve product details from the intent
        Intent intent = getIntent();

        // Check if the intent has the necessary extras
        if (intent.hasExtra("productName") && intent.hasExtra("productPrice")) {
            // Retrieve the product details
            String productName = intent.getStringExtra("productName");
            double productPrice = intent.getDoubleExtra("productPrice", 0.0);

            // Add the product to the cart items list
            cartItems.add(new CartItem(productName, productPrice));

            // Display the cart items
            displayCartItems();
        } else {
            // Show "No items in the cart" message
            TextView noItemsTextView = findViewById(R.id.noItemsTextView);
            noItemsTextView.setVisibility(View.VISIBLE);
        }

        // Display total price after adding all items
        displayTotalPrice();
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    private void displayCartItems() {
        LinearLayout cartItemsLayout = findViewById(R.id.cartItemsLayout);
        cartItemsLayout.removeAllViews(); // Clear previous items

        for (CartItem item : cartItems) {
            // Create a new LinearLayout for each cart item
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Add TextViews for item name and price to the layout
            TextView itemNameTextView = new TextView(this);
            itemNameTextView.setText(item.getName());
            itemNameTextView.setTextSize(16);
            itemNameTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            TextView itemPriceTextView = new TextView(this);
            itemPriceTextView.setText(String.format("$%.2f", item.getPrice()));
            itemPriceTextView.setTextSize(16);
            itemPriceTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            // Add TextViews to the item layout
            itemLayout.addView(itemNameTextView);
            itemLayout.addView(itemPriceTextView);

            // Add item layout to the cart items layout
            cartItemsLayout.addView(itemLayout);
        }
    }

    private void displayTotalPrice() {
        double totalPrice = calculateTotalPrice();

        // Display total price
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));
    }

    public static class CartItem implements Serializable {
        private String name;
        private double price;

        public CartItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
}

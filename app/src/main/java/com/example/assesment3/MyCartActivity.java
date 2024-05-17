package com.example.assesment3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        ImageView backButton = findViewById(R.id.backButton);
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
        if (intent != null) {
            // Check if the intent has extra for Samsung product
            if (intent.hasExtra("samsungProductName")
                && intent.hasExtra("samsungProductPrice")) {
                String samsungProductName = intent.getStringExtra("samsungProductName");
                double samsungProductPrice = intent.getDoubleExtra("samsungProductPrice", 0.0);
                int samsungProductQuantity = intent.getIntExtra("samsungProductQuantity", 1);
                handleProduct(samsungProductName, samsungProductPrice, samsungProductQuantity);
            }

            // Check if the intent has extra for Huawei product
            if (intent.hasExtra("huaweiProductName") && intent.hasExtra("huaweiProductPrice")) {
                String huaweiProductName = intent.getStringExtra("huaweiProductName");
                double huaweiProductPrice = intent.getDoubleExtra("huaweiProductPrice", 0.0);
                int huaweiProductQuantity = intent.getIntExtra("huaweiProductQuantity", 1);
                handleProduct(huaweiProductName, huaweiProductPrice, huaweiProductQuantity);
            }

            if (intent.hasExtra("iphoneProductName") && intent.hasExtra("iphoneProductPrice")) {
                String iphoneProductName = intent.getStringExtra("iphoneProductName");
                double iphoneProductPrice = intent.getDoubleExtra("iphoneProductPrice", 0.0);
                int iphoneProductQuantity = intent.getIntExtra("iphoneProductQuantity", 1);
                handleProduct(iphoneProductName, iphoneProductPrice, iphoneProductQuantity);
            }

            if (intent.hasExtra("googleProductName") && intent.hasExtra("googleProductPrice")) {
                String googleProductName = intent.getStringExtra("googleProductName");
                double googleProductPrice = intent.getDoubleExtra("googleProductPrice", 0.0);
                int googleProductQuantity = intent.getIntExtra("googleProductQuantity", 1);
                handleProduct(googleProductName, googleProductPrice, googleProductQuantity);
            }
        }

        else {
            // Show "No items in the cart" message
            TextView noItemsTextView = findViewById(R.id.noItemsTextView);
            noItemsTextView.setVisibility(View.VISIBLE);
        }

        displayCartItems();
        // Display total price after adding all items
        displayTotalPrice();
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity(); // Multiply by quantity
        }
        return totalPrice;
    }

    private void displayCartItems() {
        LinearLayout cartItemsLayout = findViewById(R.id.cartItemsLayout);
        cartItemsLayout.removeAllViews(); // Clear previous items

        for (int i = 0; i < cartItems.size(); i++) {
            final CartItem item = cartItems.get(i);

            // Create a new LinearLayout for each cart item
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Add TextViews for item name, quantity, and price to the layout
            TextView itemNameTextView = new TextView(this);
            itemNameTextView.setText(item.getName());
            itemNameTextView.setTextSize(16);
            itemNameTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            TextView itemQuantityTextView = new TextView(this);
            itemQuantityTextView.setText("Quantity: " + item.getQuantity() + " ");
            itemQuantityTextView.setTextSize(16);
            itemQuantityTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            TextView itemPriceTextView = new TextView(this);
            itemPriceTextView.setText(String.format("$%.2f", item.getPrice() * item.getQuantity())+ " "); // Multiply by quantity
            itemPriceTextView.setTextSize(16);
            itemPriceTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemLayout.getLayoutParams();
            layoutParams.topMargin = 16; // Adjust the margin as needed
            itemLayout.setLayoutParams(layoutParams);

            itemPriceTextView.setTypeface(null, Typeface.BOLD); // Set text style to bold

            // Add delete button for each item
            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setBackgroundColor(Color.RED);
            deleteButton.setTextColor(Color.WHITE);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove the item from the cart
                    cartItems.remove(item);
                    // Update the UI
                    displayCartItems();
                    displayTotalPrice();
                }
            });

            // Add TextViews and delete button to the item layout
            itemLayout.addView(itemNameTextView);
            itemLayout.addView(itemQuantityTextView);
            itemLayout.addView(itemPriceTextView);
            itemLayout.addView(deleteButton);

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

    private void handleProduct(String productName, double productPrice, int quantity) {
        // Check if the product is already in cartItems
        boolean productFound = false;
        for (CartItem item : cartItems) {
            if (item.getName().equals(productName)) {
                // Increment quantity
                item.setQuantity(item.getQuantity() + quantity);
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            // Add the product to the cart items list
            cartItems.add(new CartItem(productName, productPrice, quantity));
        }
    }


    public static class CartItem implements Serializable {
        private String name;
        private double price;
        private double quantity;

        public CartItem(String name, double price, double quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }
    }
}

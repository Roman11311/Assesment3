package com.example.assesment3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MyCartActivity.Order order = (MyCartActivity.Order) getIntent().getSerializableExtra("order");

        if (order != null) {
            displayOrderDetails(order);
        }

        // Set click listener for menu button
        ImageView menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the popup menu from the layout
                PopupMenu popupMenu = new PopupMenu(OrderDetailsActivity.this, v);
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

        // Set click listeners for navigation buttons
        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });

        ImageButton cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, MyCartActivity.class));
            }
        });

        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, SearchActivity.class));
            }
        });

        ImageButton accountButton = findViewById(R.id.accountButton);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, AccountActivity.class));
            }
        });
    }

    private void displayOrderDetails(MyCartActivity.Order order) {
        LinearLayout orderDetailsLayout = findViewById(R.id.orderDetailsLayout);

        ArrayList<MyCartActivity.CartItem> items = order.getItems();
        for (int i = 0; i < items.size(); i++) {
            MyCartActivity.CartItem item = items.get(i);
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            itemLayout.setOrientation(LinearLayout.VERTICAL);

            TextView itemNameTextView = new TextView(this);
            itemNameTextView.setText("Name: " + item.getName());
            itemNameTextView.setTextSize(16);
            itemLayout.addView(itemNameTextView);

            TextView itemQuantityTextView = new TextView(this);
            itemQuantityTextView.setText("Quantity: " + item.getQuantity());
            itemQuantityTextView.setTextSize(16);
            itemLayout.addView(itemQuantityTextView);

            TextView itemPriceTextView = new TextView(this);
            itemPriceTextView.setText("Price each item: $" + String.format("%.2f", item.getPrice()));
            itemPriceTextView.setTextSize(16);
            itemLayout.addView(itemPriceTextView);

            itemNameTextView.setTypeface(null, Typeface.BOLD);

            if (i >= 0) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemLayout.getLayoutParams();
                layoutParams.topMargin = 16;
                itemLayout.setLayoutParams(layoutParams);
            }

            orderDetailsLayout.addView(itemLayout);
        }

        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(String.format("Total Price: $%.2f", order.getTotalPrice()));
    }

    // Logout method
    private void logout() {
        // Navigate to the LoginActivity without clearing session data
        Intent loginIntent = new Intent(OrderDetailsActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Close OrderDetailsActivity after starting LoginActivity
    }
}
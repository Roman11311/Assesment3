package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout orderHistoryLayout = findViewById(R.id.orderHistoryLayout);
        ArrayList<MyCartActivity.Order> orderHistory = MyCartActivity.getOrderHistory();

        for (int i = 0; i < orderHistory.size(); i++) {
            final MyCartActivity.Order order = orderHistory.get(i);
            Button orderButton = new Button(this);
            orderButton.setText(getOrderSummaryText(order));
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("order", order);
                    startActivity(intent);
                }
            });
            orderHistoryLayout.addView(orderButton);
        }

        // Set click listener for menu button
        ImageView menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the popup menu from the layout
                PopupMenu popupMenu = new PopupMenu(OrderHistoryActivity.this, v);
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
                startActivity(new Intent(OrderHistoryActivity.this, HomeActivity.class));
            }
        });

        ImageButton cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderHistoryActivity.this, MyCartActivity.class));
            }
        });

        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderHistoryActivity.this, SearchActivity.class));
            }
        });

        ImageButton accountButton = findViewById(R.id.accountButton);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderHistoryActivity.this, AccountActivity.class));
            }
        });
    }

    private String getOrderSummaryText(MyCartActivity.Order order) {
        StringBuilder summaryText = new StringBuilder();
        for (MyCartActivity.CartItem item : order.getItems()) {
            summaryText.append(item.getName()).append(", ");
        }
        if (summaryText.length() > 0) {
            summaryText.setLength(summaryText.length() - 2); // Remove last comma
        }
        return summaryText.toString();
    }

    // Logout method
    private void logout() {
        // Navigate to the LoginActivity without clearing session data
        Intent loginIntent = new Intent(OrderHistoryActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Close OrderHistoryActivity after starting LoginActivity
    }
}
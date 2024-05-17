package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("HomeActivity", "onCreate called");

        // Find the product item layouts
        LinearLayout productItem1 = findViewById(R.id.productItem1);
        LinearLayout productItem2 = findViewById(R.id.productItem2);
        LinearLayout productItem3 = findViewById(R.id.productItem3);
        LinearLayout productItem4 = findViewById(R.id.productItem4);

        // Find the ImageButton views
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton cartButton = findViewById(R.id.cartButton);
        ImageButton searchButton = findViewById(R.id.searchButton);
        ImageButton accountButton = findViewById(R.id.accountButton);
        ImageButton searchBar = findViewById(R.id.searchBar);
        EditText searchText = findViewById(R.id.searchText);
// Set OnClickListener for the cartButton
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the CartActivity
                Intent intent = new Intent(HomeActivity.this, MyCartActivity.class);
                startActivity(intent);
            }
        });

// Set OnClickListener for the searchButton
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the SearchActivity
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchText.getText().toString().trim();
                if (query.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform the search
                    handleSearch(query);
                }
            }
        });

// Set OnClickListener for the accountButton
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the AccountActivity
                Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        // Add click listeners to the product items
        productItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass relevant data to the details activity
                Intent intent = new Intent(HomeActivity.this, SamsungActivity.class);
                intent.putExtra("Samsung", "Samsung Galaxy S24 Ultra 5G");
                startActivity(intent);
            }
        });

        productItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass relevant data to the details activity
                Intent intent = new Intent(HomeActivity.this, HuaweiActivity.class);
                intent.putExtra("Huawei", "Huawei Pura 70 Pro 5G");
                startActivity(intent);
            }
        });

        productItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass relevant data to the details activity
                Intent intent = new Intent(HomeActivity.this, IphoneActivity.class);
                intent.putExtra("Iphone", "Iphone 15 Pro Max 5G");
                startActivity(intent);
            }
        });

        productItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass relevant data to the details activity
                Intent intent = new Intent(HomeActivity.this, GoogleActivity.class);
                intent.putExtra("Google", "Google Pixel Pro 5G");
                startActivity(intent);
            }
        });

        // Set click listener for menu button
        ImageView menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the popup menu from the layout
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, v);
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

    // Method to handle the search query
    private void handleSearch(String query) {
        switch (query.toLowerCase()) {
            case "samsung":
                navigateToDetails("Samsung", "Samsung Galaxy S24 Ultra 5G", SamsungActivity.class);
                break;
            case "huawei":
                navigateToDetails("Huawei", "Huawei Product Details", HuaweiActivity.class);
                break;
            case "iphone":
                navigateToDetails("Iphone", "Iphone Product Details", IphoneActivity.class);
                break;
            case "google":
                navigateToDetails("Google", "Google Product Details", GoogleActivity.class);
                break;
            default:
                // If the search query does not match any known brand, show a toast message
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void navigateToDetails(String brand, String productName,  Class<?> destinationActivity) {
        Intent intent = new Intent(HomeActivity.this, destinationActivity); // Change to the appropriate activity
        intent.putExtra("brand", brand);
        intent.putExtra("productName", productName);
        startActivity(intent);
    }
    // Logout method
    private void logout() {
        // Navigate to the LoginActivity without clearing session data
        Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Close HomeActivity after starting LoginActivity
    }
}

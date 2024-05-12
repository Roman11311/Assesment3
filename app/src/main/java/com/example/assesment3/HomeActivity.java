package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the product item layouts
        LinearLayout productItem1 = findViewById(R.id.productItem1);
        LinearLayout productItem2 = findViewById(R.id.productItem2);

        // Add click listeners to the product items
        productItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass relevant data to the details activity
                Intent intent = new Intent(HomeActivity.this, ProductItemActivity.class);
                intent.putExtra("product_name", "Samsung Galaxy S24 Ultra 5G");
                startActivity(intent);
            }
        });

        productItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass relevant data to the details activity
                Intent intent = new Intent(HomeActivity.this, ProductItemActivity.class);
                intent.putExtra("product_name", "Huawei Pura 70 Pro 5G");
                startActivity(intent);
            }
        });

        // Find the menu button
        ImageView menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenuVisibility();
            }
        });
    }

    private void toggleMenuVisibility() {
        // Find the menu layout and overlay views
        LinearLayout menuLayout = findViewById(R.id.menuLayout);
        View overlay = findViewById(R.id.overlay);

        // Check if the menu is already visible
        if (menuLayout.getVisibility() == View.VISIBLE) {
            // If visible, hide the menu layout and overlay
            menuLayout.setVisibility(View.GONE);
            overlay.setVisibility(View.GONE);
        } else {
            // If hidden, show the menu layout and overlay
            menuLayout.setVisibility(View.VISIBLE);
            overlay.setVisibility(View.VISIBLE);
            // Set background color to light purple when menu becomes visible
            menuLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_purple));
        }
    }
}

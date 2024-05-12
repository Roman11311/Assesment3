package com.example.assesment3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assesment3.MyCartActivity.CartItem;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

        // Retrieve list of items and total price from intent
        ArrayList<CartItem> items = (ArrayList<CartItem>) getIntent().getSerializableExtra("cartItems");
        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);

        // Display items and total price
        if (items != null) {
            displayOrderSummary(items, totalPrice);
        }
    }

    private void displayOrderSummary(ArrayList<CartItem> items, double totalPrice) {
        // Find the layout where items will be displayed
        LinearLayout orderSummaryLayout = findViewById(R.id.orderSummaryLayout);

        // Display each item
        for (CartItem item : items) {
            TextView itemTextView = new TextView(this);
            itemTextView.setText(item.getName() + " - " + item.getPrice());
            orderSummaryLayout.addView(itemTextView);
        }

        // Display total price
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(String.format("Total Price: $%.2f", totalPrice));
    }
}

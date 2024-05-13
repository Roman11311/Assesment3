package com.example.assesment3;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        ImageView backButton = findViewById(R.id.backButton);
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

            orderSummaryLayout.addView(itemLayout);
        }

        // Display total price
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(String.format("Total Price: $%.2f", totalPrice));
    }

}

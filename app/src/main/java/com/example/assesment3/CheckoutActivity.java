package com.example.assesment3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private ArrayList<MyCartActivity.CartItem> items;
    private double totalPrice;
    private EditText nameEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        items = (ArrayList<MyCartActivity.CartItem>) getIntent().getSerializableExtra("cartItems");
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);

        if (items != null) {
            displayOrderSummary(items, totalPrice);
        }
    }

    public void onPlaceOrderButtonClick(View view) {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name) || !isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid name and email", Toast.LENGTH_SHORT).show();
        } else if (totalPrice == 0) {
            Toast.makeText(this, "Please select at least 1 item in the cart", Toast.LENGTH_SHORT).show();
        } else {
            MyCartActivity.Order newOrder = new MyCartActivity.Order(items, totalPrice);
            MyCartActivity.getOrderHistory().add(newOrder);

            // Clear the cart
            MyCartActivity.clearCart();

            Toast.makeText(this, "Order successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(CheckoutActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void displayOrderSummary(ArrayList<MyCartActivity.CartItem> items, double totalPrice) {
        LinearLayout orderSummaryLayout = findViewById(R.id.orderSummaryLayout);

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

            orderSummaryLayout.addView(itemLayout);
        }

        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(String.format("Total Price: $%.2f", totalPrice));
    }
}
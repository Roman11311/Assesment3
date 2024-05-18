package com.example.assesment3;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
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
    }

    private void displayOrderDetails(MyCartActivity.Order order) {
        LinearLayout orderDetailsLayout = findViewById(R.id.orderDetailsLayout);

        // Display each item
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

        // Display total price
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText(String.format("Total Price: $%.2f", order.getTotalPrice()));
    }
}
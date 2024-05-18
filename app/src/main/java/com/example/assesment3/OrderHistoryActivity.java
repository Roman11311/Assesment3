package com.example.assesment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
}
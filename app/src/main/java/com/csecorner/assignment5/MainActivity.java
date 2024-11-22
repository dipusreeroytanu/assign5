package com.csecorner.assignment5;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Data for RecyclerView
        List<String> items = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            items.add("Item " + i);
        }

        // Set adapter
        recyclerView.setAdapter(new MyAdapter(items));
    }

    // Adapter for RecyclerView
    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private final List<String> mData;

        MyAdapter(List<String> data) {
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Dynamically create a LinearLayout for each item
            LinearLayout itemLayout = new LinearLayout(parent.getContext());
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(20, 20, 20, 20);

            // Add a TextView
            TextView textView = new TextView(parent.getContext());
            textView.setTextSize(18);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            itemLayout.addView(textView);

            // Add a horizontal layout for buttons
            LinearLayout buttonLayout = new LinearLayout(parent.getContext());
            buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
            buttonLayout.setGravity(Gravity.CENTER);

            // Create four buttons
            for (int i = 1; i <= 4; i++) {
                Button button = new Button(parent.getContext());
                button.setText("Option " + i);
                button.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                ));
                buttonLayout.addView(button);
                int finalI = i; // Needed for lambda
                button.setOnClickListener(v -> Toast.makeText(
                        parent.getContext(),
                        "Clicked Option " + finalI,
                        Toast.LENGTH_SHORT
                ).show());
            }

            itemLayout.addView(buttonLayout);

            return new ViewHolder(itemLayout, textView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            ViewHolder(View itemView, TextView textView) {
                super(itemView);
                this.textView = textView;
            }
        }
    }
}
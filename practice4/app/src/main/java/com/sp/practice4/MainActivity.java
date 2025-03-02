package com.sp.practice4;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, costInput, savingsGoalInput;
    private RadioGroup categoryGroup, savingsToggleGroup;
    private Button saveButton, clearButton, calculateButton;
    private ListView expensesListView;
    private TextView totalExpensesText;
    private ArrayList<String> expensesList;
    private ArrayAdapter<String> adapter;
    private double totalExpenses = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        nameInput = findViewById(R.id.name);
        costInput = findViewById(R.id.cost);
        savingsGoalInput = findViewById(R.id.savingsGoal);
        categoryGroup = findViewById(R.id.catergory);
        savingsToggleGroup = findViewById(R.id.savingsVisibilityToggle);
        saveButton = findViewById(R.id.Save);
        clearButton = findViewById(R.id.Clear);
        calculateButton = findViewById(R.id.Calculate);
        expensesListView = findViewById(R.id.list);
        totalExpensesText = findViewById(R.id.totalExpenses);

        // Initialize List and Adapter
        expensesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expensesList);
        expensesListView.setAdapter(adapter);

        // Toggle Savings Goal Visibility
        savingsToggleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.visibleGoal) {
                    savingsGoalInput.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.invisibleGoal) {
                    savingsGoalInput.setVisibility(View.GONE);
                }
            }
        });

        // Save Button Functionality
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String costText = costInput.getText().toString().trim();
                int selectedCategoryId = categoryGroup.getCheckedRadioButtonId();

                if (name.isEmpty() || costText.isEmpty() || selectedCategoryId == -1) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double cost = Double.parseDouble(costText);
                RadioButton selectedCategory = findViewById(selectedCategoryId);
                String category = selectedCategory.getText().toString();

                // Add expense to list
                String expense = name + " - " + category + ": $" + cost;
                expensesList.add(expense);
                adapter.notifyDataSetChanged();

                // Update total expenses
                totalExpenses += cost;

                // Show success message
                Toast.makeText(MainActivity.this, "Expense saved successfully", Toast.LENGTH_SHORT).show();

                // Clear inputs
                nameInput.setText("");
                costInput.setText("");
                categoryGroup.clearCheck();
                String message = "Total Expenses: $" + totalExpenses;
                totalExpensesText.setText(message);
            }
        });

        // Calculate Total Expenses
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check savings goal
                if (savingsGoalInput.getVisibility() == View.VISIBLE) {
                    String goalText = savingsGoalInput.getText().toString().trim();
                    if (!goalText.isEmpty()) {
                        double savingsGoal = Double.parseDouble(goalText);
                        if (totalExpenses > savingsGoal) {
                            Toast.makeText(MainActivity.this, "You have exceeded your savings goal!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "You are on track with your savings goal!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        // Clear Button Functionality
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameInput.setText("");
                costInput.setText("");
                categoryGroup.clearCheck();
                expensesList.clear();
                adapter.notifyDataSetChanged();
                totalExpenses = 0.0;
                totalExpensesText.setText("Total Expenses: $0.0");
                savingsGoalInput.setText("");
                Toast.makeText(MainActivity.this, "All inputs cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

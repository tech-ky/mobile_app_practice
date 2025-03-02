package com.sp.practice5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText name,cost,goal;
    private Button cal,save,clear;
    private ListView list;
    private RadioGroup cater,select;

    private TextView total;
    private ArrayList<String> expensesList;
    private ArrayAdapter<String> adapter;
    private double totalExpenses = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        cost = findViewById(R.id.cost);
        goal = findViewById(R.id.goal);
        cal = findViewById(R.id.cal);
        // cal.setOnClickListener(CAL);
        clear = findViewById(R.id.clear);
        save = findViewById(R.id.save);
        // save.setOnClickListener(SAVE);

        cater = findViewById(R.id.catergory);
        select = findViewById(R.id.select);

        list = findViewById(R.id.list);
        total = findViewById(R.id.total);
        total.setText("total Expenses = $" + totalExpenses);

        expensesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expensesList);
        list.setAdapter(adapter);

        select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.visible) {
                    goal.setVisibility(View.VISIBLE);
                } else if (i == R.id.invisible) {
                    goal.setVisibility(View.GONE);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                cost.setText("");
                goal.setText("");
                totalExpenses = 0.0;
                total.setText("total Expenses = $" + totalExpenses);
                select.clearCheck();
                cater.clearCheck();
                expensesList.clear();
                adapter.notifyDataSetChanged();
            }
        });
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double goaldouble = Double.parseDouble(goal.getText().toString().trim());
                if (goal.getVisibility() == View.VISIBLE || !goaldouble.isNaN()) {
                if(goaldouble<totalExpenses){
                    Toast.makeText(MainActivity.this, "Budget exceeded", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Within Budget", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameStr=name.getText().toString().trim();
                String CostStr=cost.getText().toString().trim();
                String CaterStr="";
                int radioId=cater.getCheckedRadioButtonId();
                if(radioId==R.id.Entertainment){
                    CaterStr="Entertainment";
                }else if(radioId==R.id.Food) {
                    CaterStr = "Food";
                }else if(radioId==R.id.Transport) {
                    CaterStr = "Transport";
                }else if(radioId==R.id.Others) {
                    CaterStr = "Others";
                }

                if(NameStr.isEmpty()||CostStr.isEmpty()||CaterStr.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }else{
                    double costdouble= Double.parseDouble(CostStr);
                    totalExpenses= costdouble + totalExpenses;
                    String Text = NameStr + "," + CaterStr + ", $"+ costdouble;
                    adapter.add(Text);
                    Toast.makeText(MainActivity.this, "added "+Text, Toast.LENGTH_SHORT).show();
                    total.setText("Total Expenses = $"+ totalExpenses);
                }
            }
        });
    }
}
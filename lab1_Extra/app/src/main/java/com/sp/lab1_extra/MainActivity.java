package com.sp.lab1_extra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private EditText height,weight;
    private Button calculate;
    private ArrayList<String> model = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        calculate=findViewById(R.id.calcuate);
        calculate.setOnClickListener(Calculate);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,model);
        list.setAdapter(adapter);
    }

    View.OnClickListener Calculate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(height.getText().toString().isEmpty() || weight.getText().toString().isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Edit text cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else
            {
                float w = Float.parseFloat(weight.getText().toString());
                float h = Float.parseFloat(height.getText().toString());
                float BMI = w /( h * h);
                String Weight_status ="";
                if(BMI <18.5){
                    Weight_status="Underweight";
                }else if(BMI <24.9){
                    Weight_status="Healthy";
                }else if(BMI <29.9){
                    Weight_status="Overweight";
                }else{
                    Weight_status="Obese";
                }
                String text = "BMI - "+ BMI + " " + Weight_status;
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                adapter.add(text);
            }
        }
    };
}
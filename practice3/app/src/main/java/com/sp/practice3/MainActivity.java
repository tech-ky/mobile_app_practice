package com.sp.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText Cost,Name;
    private Button Save,Clear,Calculate;
    private RadioGroup Catergory;
    private ListView list;
    private ArrayList<String> model= new ArrayList<>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cost=findViewById(R.id.cost);

        Name=findViewById(R.id.name);

        Save=findViewById(R.id.Save);
        Save.setOnClickListener(SAVE);

        Calculate=findViewById(R.id.Calculate);
        Calculate.setOnClickListener(CAL);

        Clear=findViewById(R.id.Clear);
        Clear.setOnClickListener(CLEAR);

        list=findViewById(R.id.list);

        Catergory=findViewById(R.id.catergory);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,model);
        list.setAdapter(adapter);
        init();
    }
    public void init(){
        adapter.clear();
        Cost.setText("");
        Name.setText("");
        Catergory.clearCheck();
    }
    View.OnClickListener CLEAR = new View.OnClickListener() {
        @Override
        public void onClick(View view) {init();}
    };
    View.OnClickListener SAVE = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String NameStr = Name.getText().toString();
            String CostStr = Cost.getText().toString();
            float cost= Float.parseFloat(CostStr);
            int RadioId =Catergory.getCheckedRadioButtonId();
            String CaterStr="";
            if(RadioId == R.id.Entertainment){
                CaterStr="Entertainment";
            } else if (RadioId == R.id.Food) {
                CaterStr="Food";
            } else if (RadioId==R.id.Other) {
                CaterStr="Others";
            }else if(RadioId==R.id.Transport){
                CaterStr="Transport";
        }
            if(NameStr.isEmpty()||CostStr.isEmpty()||CaterStr.isEmpty()){
                Toast.makeText(getApplicationContext(),"Cannot be empty",Toast.LENGTH_SHORT).show();
            }
            else {
                String text = NameStr +","+ CostStr+ ","+CaterStr;
                adapter.add(text);
            }
        }
    };
    View.OnClickListener CAL = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float totalExpenses = 0;

            for (String item : model) {
                String[] parts = item.split(",");
                if (parts.length >= 2) {
                    float cost = Float.parseFloat(parts[1]);
                    totalExpenses += cost;
                }
            }

            Toast.makeText(getApplicationContext(), "Total Expenses: " + totalExpenses, Toast.LENGTH_LONG).show();
        }
    };

}
package com.sp.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private EditText name,number;
    private RadioGroup rating;

    private Button submit,clear;

    private ArrayList<String> model = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);
        number=findViewById(R.id.number);
        name=findViewById(R.id.name);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(Submit);
        clear=findViewById(R.id.clear);
        clear.setOnClickListener(Clear);
        rating=findViewById(R.id.rating);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,model);
        list.setAdapter(adapter);
        init();
    }
    public void init(){
        adapter.clear();
        number.setText("");
        name.setText("");
        rating.clearCheck();
    }
    View.OnClickListener Clear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            init();
        }
    };

    View.OnClickListener Submit = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            String NameStr = name.getText().toString();
            String NumberStr = number.getText().toString();
            String RateStr ="";
            int RadioId = rating.getCheckedRadioButtonId();
            if (RadioId == R.id.rate1) {
                RateStr = "1 STAR";
            } else if (RadioId == R.id.rate2) {
                RateStr = "2 STAR";
            } else if (RadioId == R.id.rate3) {
                RateStr = "3 STAR";
            } else if (RadioId == R.id.rate4) {
                RateStr = "4 STAR";
            } else if (RadioId == R.id.rate5) {
                RateStr = "5 STAR";
            } else {
                RateStr = ""; // If no rating is selected
            }

            if(NameStr.isEmpty()||NumberStr.isEmpty()||RateStr.isEmpty()){
                Toast.makeText(getApplicationContext(),"Cannot be empty",Toast.LENGTH_SHORT).show();
            }
            else {
                String text = NameStr +","+NameStr+","+RateStr;
                adapter.add(text);
            }
        }
    };


}
package com.sp.mydcpepractice;

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
    private EditText ed1, ed2;
    private Button bt1,bt2;

    private ArrayList<String>model = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=findViewById(R.id.LV);
        ed1=findViewById(R.id.editTextText);
        ed2=findViewById(R.id.editTextText3);
        bt1=findViewById(R.id.button);
        bt1.setOnClickListener(done);
        bt2=findViewById(R.id.button2);
        bt2.setOnClickListener(clear);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,model);
        list.setAdapter(adapter);
        init();
    }

    public void init(){
        adapter.clear();
        ed1.setText("");
        ed2.setText("");
        // do for radio button
    }

    View.OnClickListener clear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            init();
        }
    };

    View.OnClickListener done = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(ed1.getText().toString().isEmpty() || ed2.getText().toString().isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Edit text cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String text = ed1.getText().toString() + ed2.getText().toString();
                adapter.add(text);
            }
        }
    };

}
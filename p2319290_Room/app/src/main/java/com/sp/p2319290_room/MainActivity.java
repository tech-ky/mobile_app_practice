package com.sp.p2319290_room;

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
    private EditText hours,room;
    private RadioGroup time;
    private ListView list;
    private Button add,Default;
    private ArrayList<String> model = new ArrayList<>();
    private ArrayAdapter<String> adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hours=findViewById(R.id.hours);
        room=findViewById(R.id.Room);

        time=findViewById(R.id.time);

        add=findViewById(R.id.ADD);
        Default=findViewById(R.id.DEFAULT);

        list=findViewById(R.id.list);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,model);
        list.setAdapter(adapter);

        Default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours.setText("");
                room.setText("");
                time.clearCheck();
                adapter.clear();
            }
        });

        time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.Full_Day){
                    hours.setVisibility(View.INVISIBLE);

                } else if (i==R.id.ByHour) {
                    hours.setVisibility(View.VISIBLE);
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String RoomStr = room.getText().toString().trim();
                int Hourint = Integer.parseInt(hours.getText().toString().trim());
                int RadioID = time.getCheckedRadioButtonId();
                String text = "";
                if (RoomStr.isEmpty() || RadioID == -1) {
                    Toast.makeText(getApplicationContext()," please fill up all inputs",Toast.LENGTH_LONG).show();
                } else {
                    if (RadioID == R.id.Full_Day) {
                        text = RoomStr + " for 8 hours ";
                    } else if (RadioID == R.id.ByHour) {
                        text = RoomStr + " for " + Hourint + " hours";
                    }
                    adapter.add(text);
                }
            }
        });
    }
}
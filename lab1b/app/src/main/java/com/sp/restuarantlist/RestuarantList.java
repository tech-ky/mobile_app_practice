package com.sp.restuarantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;
public class RestuarantList extends AppCompatActivity
{
    private EditText restaurantName,restaurantAddress,restaurantTel;
    private Button buttonSave;
    private RadioGroup restaurantTypes;

    private List<Restaurant> model = new ArrayList<Restaurant>();
    private ArrayAdapter<Restaurant> adpater =null;
    private ListView list;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        restaurantAddress=findViewById(R.id.restaurant_address);
        restaurantName=findViewById(R.id.restaurant_name);
        restaurantTel=findViewById(R.id.restaurant_tel);
        restaurantTypes=findViewById(R.id.restaurant_types);
        buttonSave=findViewById(R.id.button_save);
        buttonSave.setOnClickListener(onSave);

        list=findViewById(R.id.restaurants);
        adpater = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,model);
        list.setAdapter(adpater);
    }
    private View.OnClickListener onSave = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            String nameStr = restaurantName.getText().toString();
            String addressStr = restaurantAddress.getText().toString();
            String telStr = restaurantTel.getText().toString();
            String restType = "";
            int radioID = restaurantTypes.getCheckedRadioButtonId();
            if(radioID==R.id.chinese){
                restType="Chinese";
            }
            else if(radioID==R.id.western){
                restType="Western";
            }
            else if(radioID==R.id.indian){
                restType="Indian";
            }
            else if(radioID==R.id.indonasian){
                restType="Indonesian";
            }
            else if(radioID==R.id.korean) {
                restType = "Korean";
            }
            else if(radioID==R.id.japanese){
                restType="Japanese";
            }
            else if(radioID==R.id.thai){
                restType="Thai";
            }
            String combineStr = nameStr + "\n" + restType + "\n" + addressStr + "\n" + telStr ;
            Toast.makeText(getApplicationContext(),combineStr,Toast.LENGTH_LONG).show();

            Restaurant restaurant = new Restaurant();
            restaurant.setName(nameStr);
            restaurant.setAddress(addressStr);
            restaurant.setTel(telStr);
            restaurant.setType(restType);
            adpater.add(restaurant);
        }
    };
}


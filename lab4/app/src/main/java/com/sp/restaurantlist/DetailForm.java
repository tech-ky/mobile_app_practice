package com.sp.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DetailForm extends AppCompatActivity {
    private EditText restaurantName;
    private EditText restaurantAddress;
    private EditText restaurantTel;
    private RadioGroup restaurantTypes;
    private Button buttonSave;

    private RestaurantHelper helper =null;
    private String restaurantID ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_form);
        restaurantName =findViewById(R.id.restaurant_name);
        restaurantAddress =findViewById(R.id.restaurant_address);
        restaurantTel =findViewById(R.id.restaurant_tel);
        restaurantTypes =findViewById(R.id.restaurant_types);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(onSave);
        helper =new RestaurantHelper(this);

        restaurantID = getIntent().getStringExtra("ID");
        if (restaurantID != null){
            load();
        }

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        helper.close();
    }

    protected void load(){
        Cursor c =helper.getById(restaurantID);
        c.moveToFirst();
        restaurantName.setText(helper.getRestaurantName(c));
        restaurantAddress.setText(helper.getRestaurantAddress(c));
        restaurantTel.setText(helper.getRestaurantTel(c));

        if(helper.getRestaurantType(c).equals("Chinese")){
            restaurantTypes.check(R.id.chinese);
        } else if (helper.getRestaurantType(c).equals("Western")){
            restaurantTypes.check(R.id.western);
        } else if (helper.getRestaurantType(c).equals("Indian")){
            restaurantTypes.check(R.id.indian);
        } else if (helper.getRestaurantType(c).equals("Indonesian")){
            restaurantTypes.check(R.id.indonesian);
        } else if (helper.getRestaurantType(c).equals("Korean")){
            restaurantTypes.check(R.id.korean);
        } else if (helper.getRestaurantType(c).equals("Japanese")){
            restaurantTypes.check(R.id.japanese);
        } else if (helper.getRestaurantType(c).equals("Thai")){
            restaurantTypes.check(R.id.thai);
        }
    }
    View.OnClickListener onSave = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            String nameStr = restaurantName.getText().toString();
            String addrStr = restaurantAddress.getText().toString();
            String telStr = restaurantTel.getText().toString();
            String typeStr = "";

            int RadioId = restaurantTypes.getCheckedRadioButtonId();
            if (RadioId == R.id.chinese) {
                typeStr = "Chinese";
            } else if (RadioId == R.id.western) {
                typeStr = "Western";
            } else if (RadioId == R.id.indian) {
                typeStr = "Indian";
            } else if (RadioId == R.id.indonesian) {
                typeStr = "Indonesian";
            } else if (RadioId == R.id.korean) {
                typeStr = "Korean";
            } else if (RadioId == R.id.japanese) {
                typeStr = "Japanese";
            } else if (RadioId == R.id.thai) {
                typeStr = "Thai";
            }
            if (restaurantID != null && !restaurantID.isEmpty()) {
                // If restaurantID exists, update the existing record in the database
                helper.update(restaurantID, nameStr, addrStr, telStr, typeStr);
            } else {
                // If restaurantID is null, insert a new record
                helper.insert(nameStr, addrStr, telStr, typeStr);
            }
            finish();
        }
    };
}
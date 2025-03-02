package com.sp.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DetailForm extends AppCompatActivity {
    private EditText restaurantName;
    private EditText restaurantAddress;
    private EditText restaurantTel;
    private RadioGroup restaurantTypes;
    private Button buttonSave;

    private RestaurantHelper helper =null;
    private String restaurantID ="";

    private TextView location = null;
    private GPSTracker gpsTracker;
    private double latitude = 0.0d;
    private double longtitude = 0.0d;
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

        location = findViewById(R.id.location);
        gpsTracker = new GPSTracker(DetailForm.this);

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
        gpsTracker.stopUsingGPS();
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

        latitude = helper.getLatitude(c);
        longtitude = helper.getLongtitude(c);
        location.setText(String.valueOf(latitude)+ ", "+ String.valueOf(longtitude));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        new MenuInflater(this).inflate(R.menu.detail_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.get_location){
            if(gpsTracker.canGetLocation()){
                latitude = gpsTracker.getLatitude();
                longtitude = gpsTracker.getLongitude();
                location.setText(String.valueOf(latitude)+ ","+String.valueOf(longtitude));
                Toast.makeText(getApplicationContext(),"Your Location is - \nLat: " + latitude
                        + "\nLong: " + longtitude,Toast.LENGTH_LONG).show();
            }
            return(true);
        }
        return  super.onOptionsItemSelected(item);
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
                helper.update(restaurantID, nameStr, addrStr, telStr, typeStr,latitude,longtitude);
            } else {
                // If restaurantID is null, insert a new record
                helper.insert(nameStr, addrStr, telStr, typeStr,latitude,longtitude);
            }
            finish();
        }
    };
}
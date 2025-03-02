package com.sp.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    private EditText restaurantName;
    private EditText restaurantAddress;
    private EditText restaurantTel;
    private RadioGroup restaurantTypes;
    private Button buttonSave;
    private ListView list;
    private List<Restaurant> model = new ArrayList<>();
    private RestaurantAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialize UI elements
        restaurantName = findViewById(R.id.restaurant_name);
        restaurantAddress = findViewById(R.id.restaurant_address);
        restaurantTel = findViewById(R.id.restaurant_tel);
        restaurantTypes = findViewById(R.id.restaurant_types);
        buttonSave = findViewById(R.id.button_save);
        list = findViewById(R.id.restaurants);

        // Set up the adapter for the ListView
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);

        // Set the onClickListener for the Save button
        buttonSave.setOnClickListener(onSave);
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get user input
            String nameStr = restaurantName.getText().toString();
            String addressStr = restaurantAddress.getText().toString();
            String telStr = restaurantTel.getText().toString();

            // Determine the selected restaurant type
            int RadioId = restaurantTypes.getCheckedRadioButtonId();
            String typeStr = "";
            if (RadioId ==R.id.chinese) {
                typeStr = "Chinese";
            }else
            if (RadioId ==R.id.western) {
                typeStr = "Western";
            }else
            if (RadioId ==R.id.indian) {
                typeStr = "Indian";
            }else
            if (RadioId ==R.id.indonesian) {
                typeStr = "Indonsian";
            }else
            if (RadioId ==R.id.korean) {
                typeStr = "Korean";
            }else
            if (RadioId ==R.id.japanese) {
                typeStr = "Japanese";
            }else
            if (RadioId ==R.id.thai) {
                typeStr = "Thai";
            }

            // Display a toast with the details
            String message = nameStr + "\n" + addressStr + "\n" + telStr + "\n" + typeStr;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            Restaurant restaurant = new Restaurant();
            restaurant.setName(nameStr);
            restaurant.setAddress(addressStr);
            restaurant.setTel(telStr);
            restaurant.setType(typeStr);

            adapter.add(restaurant);
        }
    };

    static class RestaurantHolder {
        private TextView restName = null;
        private TextView addr = null;
        private ImageView icon = null;

        RestaurantHolder(View row) {
            restName = row.findViewById(R.id.restName);
            addr = row.findViewById(R.id.restAddr);
            icon = row.findViewById(R.id.icon);
        }

        void populateFrom(Restaurant r) {
            restName.setText(r.getname());
            addr.setText(r.getAddress());
            if(r.getType().equals("Chinese")) {
                icon.setImageResource(R.drawable.ball_red);
            }
            else if(r.getType().equals("Western")) {
                    icon.setImageResource(R.drawable.ball_yellow);
                }else{
                    icon.setImageResource(R.drawable.ball_green);
            }
        }
    }

    class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        RestaurantAdapter() {
            super(RestaurantList.this, R.layout.row, model);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RestaurantHolder holder;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
            } else {
                holder = (RestaurantHolder)row.getTag();
            }

            holder.populateFrom(model.get(position));
            return (row);
        }
    }
}

package com.sp.restaurantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
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
    private TabHost host;
    private boolean showMenu = false;

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

        host = findViewById(R.id.tabHost);
        host.setup();

        // Set the onClickListener for the Save button
        buttonSave.setOnClickListener(onSave);

        // Tab1
        TabHost.TabSpec spec = host.newTabSpec("List");
        spec.setContent(R.id.restaurants_tab);
        spec.setIndicator("list");
        host.addTab(spec);

        // Tab2
        spec = host.newTabSpec("Details");
        spec.setContent(R.id.details_tab);
        spec.setIndicator("Details");
        host.addTab(spec);
        host.setCurrentTab(1);
        list.setOnItemClickListener(onListClick);

        // Detect when tab changes to refresh the menu
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
            public void onTabChanged(String tabId){
                invalidateOptionsMenu(); // This triggers onCreateOptionsMenu() to run again
            }
        });
    }

    // This function is overriding the default invalidateOptionsMenu() function
    // Ideally, we don't need to redefine it here; the base function already works
    // To simplify, remove this redefinition, as it could lead to unexpected behavior
    /*public void invalidateOptionsMenu() {
        if (host.getCurrentTab() == 0) {
            showMenu = false;
        } else if (host.getCurrentTab() == 1) {
            showMenu = true;
        }
        super.invalidateOptionsMenu();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (host.getCurrentTab() == 1) { // Only show the menu if on "DETAILS" tab
            getMenuInflater().inflate(R.menu.options, menu); // Inflate menu when showMenu is true
            return true;
        }
        return false; // Return false to hide the menu if not on "DETAILS" tab
    }

    @Override
    protected void onStart() {
        invalidateOptionsMenu(); // Call to refresh menu options
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Toast.makeText(this, "Restaurant List - version 1.0", Toast.LENGTH_LONG).show();
            return true; // Indicate that the item was handled
        }
        return super.onOptionsItemSelected(item);
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

            // Display a toast with the details
            String message = nameStr + "\n" + addressStr + "\n" + telStr + "\n" + typeStr;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            Restaurant restaurant = new Restaurant();
            restaurant.setName(nameStr);
            restaurant.setAddress(addressStr);
            restaurant.setTel(telStr);
            restaurant.setType(typeStr);

            adapter.add(restaurant);
            host.setCurrentTab(0); // Switch to the "LIST" tab after saving
        }
    };

    static class RestaurantHolder {
        private TextView restName = null;
        private TextView addr_adn_Num = null;
        private ImageView icon = null;

        RestaurantHolder(View row) {
            restName = row.findViewById(R.id.restName);
            addr_adn_Num = row.findViewById(R.id.restAddr_and_Num);
            icon = row.findViewById(R.id.icon);
        }

        void populateFrom(Restaurant r) {
            restName.setText(r.getname());
            String message = r.getAddress() + ", " + r.getTel();
            addr_adn_Num.setText(message);

            if (r.getType().equals("Chinese")) {
                icon.setImageResource(R.drawable.ball_red);
            } else if (r.getType().equals("Western")) {
                icon.setImageResource(R.drawable.ball_yellow);
            } else {
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
                holder = (RestaurantHolder) row.getTag();
            }

            holder.populateFrom(model.get(position));
            return row;
        }
    }

    AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Restaurant r = model.get(position);
            restaurantName.setText(r.getname());
            restaurantAddress.setText(r.getAddress());
            restaurantTel.setText(r.getTel());

            if (r.getType().equals("Chinese")) {
                restaurantTypes.check(R.id.chinese);
            } else if (r.getType().equals("Western")) {
                restaurantTypes.check(R.id.western);
            } else if (r.getType().equals("Indian")) {
                restaurantTypes.check(R.id.indian);
            } else if (r.getType().equals("Indonesian")) {
                restaurantTypes.check(R.id.indonesian);
            } else if (r.getType().equals("Korean")) {
                restaurantTypes.check(R.id.korean);
            } else if (r.getType().equals("Japanese")) {
                restaurantTypes.check(R.id.japanese);
            } else if (r.getType().equals("Thai")) {
                restaurantTypes.check(R.id.thai);
            }

            host.setCurrentTab(1); // Switch to the "DETAILS" tab
        }
    };
}

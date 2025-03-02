package com.sp.restuarantlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RestuarantList extends AppCompatActivity {
    private EditText resturantName;
    private EditText resturantAddress;
    private EditText resturantTel;
    private RadioGroup restuarantTypes;
    private Button ButtonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        resturantName = findViewById(R.id.restaurant_name);
        resturantAddress = findViewById(R.id.restaurant_address);
        resturantTel = findViewById(R.id.restaurant_tel);

        restuarantTypes = findViewById(R.id.restaurant_types);

        ButtonSave = findViewById(R.id.button_save);
        ButtonSave.setOnClickListener(onSave);
    }
    private View.OnClickListener onSave = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            String NameStr = resturantName.getText().toString();
            String AddressStr = resturantAddress.getText().toString();
            String TelStr = resturantTel.getText().toString();
            String restType ="";
            int RadioId = restuarantTypes.getCheckedRadioButtonId();
            if (RadioId ==R.id.chinese) {
                restType = "Chinese";
            }else
            if (RadioId ==R.id.western) {
                restType = "Western";
            }else
            if (RadioId ==R.id.indian) {
                restType = "Indian";
            }else
            if (RadioId ==R.id.indonasian) {
                restType = "Indonsian";
            }else
            if (RadioId ==R.id.korean) {
                restType = "Korean";
            }else
            if (RadioId ==R.id.japanese) {
                restType = "Japanese";
            }else
            if (RadioId ==R.id.thai) {
                restType = "Thai";
            }
            String StrCombiner = NameStr + "\n" + AddressStr + "\n" + TelStr+ "\n" + restType;
            Toast.makeText(getApplicationContext(),StrCombiner,Toast.LENGTH_LONG).show();
        }
    };
}


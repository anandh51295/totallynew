package com.intensivedatatech.totally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intensivedatatech.totally.helper.DatabaseHelper;

public class AdduserActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText ed1, ed2, ed3;
    Button btn;
    String type, name, address, number;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        db = new DatabaseHelper(getApplicationContext());
        btn = findViewById(R.id.btn_submit);
        ed1 = findViewById(R.id.et_name);
        ed2 = findViewById(R.id.et_address);
        ed3 = findViewById(R.id.et_number);
        ed2.setVisibility(View.GONE);
        address="nodata";
        try {
            Intent i = getIntent();
            type = i.getStringExtra("type");
            if (type.equals("update")) {
                id=i.getIntExtra("id",0);
                name = i.getStringExtra("name");
//                address = i.getStringExtra("address");
                number = i.getStringExtra("number");
                if(!name.isEmpty()&& !address.isEmpty()&&!number.isEmpty()){
                    ed1.setText(name);
                    ed2.setText(address);
                    ed3.setText(number);
                }
            }
        } catch (Exception r) {
            r.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("insert")){
                    String name, address, number;
                    name = ed1.getText().toString();
//                    address = ed2.getText().toString();
                    address="nodata";
                    number = ed3.getText().toString();
                    if (!name.isEmpty() && !address.isEmpty() && !number.isEmpty()) {
                        boolean ck = db.insertparty(name, address, number);
                        if (ck) {
                            Toast.makeText(getApplicationContext(), "Party Added", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Party not Added", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d("party", "no data");
                    }
                }else if(type.equals("update")){
                    String name, address, number;
                    name = ed1.getText().toString();
//                    address = ed2.getText().toString();
                    address="nodata";
                    number = ed3.getText().toString();
                    if (!name.isEmpty() && !address.isEmpty() && !number.isEmpty()) {
                        boolean ck = db.changeparty(id,name, address, number);
                        if (ck) {
                            Toast.makeText(getApplicationContext(), "Party Updated", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Party not Updated", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d("party", "no data");
                    }
                }else{
                    Log.d("add party","not working");
                }

            }
        });
    }
}

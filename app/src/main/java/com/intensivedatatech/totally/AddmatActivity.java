package com.intensivedatatech.totally;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intensivedatatech.totally.helper.DatabaseHelper;

import java.util.Calendar;

public class AddmatActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText ed1, ed2, ed3, ed4;
    TextView dats;
    Button btn;
    String name, quantity, price, totalprice, type;
    int id;
    String dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_addmat);
        ed1 = findViewById(R.id.et_mad);
        ed2 = findViewById(R.id.et_mquantity);
        ed3 = findViewById(R.id.et_mprice);
        ed4 = findViewById(R.id.et_mtotalprice);
        btn = findViewById(R.id.btn_msubmit);
        dats = findViewById(R.id.tv_date);

        dats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddmatActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth + 1;
                        dt = selectedyear + "-" + selectedmonth + "-" + selectedday;
                        dats.setText("Selected Date: "+dt);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        try {
            Intent i = getIntent();
            type = i.getStringExtra("type");
            if (type.equals("update")) {
                ed4.setVisibility(View.VISIBLE);
                id = i.getIntExtra("id", 0);
                name = i.getStringExtra("name");
                quantity = i.getStringExtra("quantity");
                price = i.getStringExtra("price");
                totalprice = i.getStringExtra("totalprice");

                ed1.setText(name);
                ed2.setText(quantity);
                ed3.setText(price);
                ed4.setText(totalprice);

            } else if (type.equals("insert")) {
                ed4.setVisibility(View.INVISIBLE);
            }

        } catch (Exception r) {
            r.printStackTrace();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_address, price, quantity;
                name_address = ed1.getText().toString();
                price = ed3.getText().toString();
                quantity = ed2.getText().toString();
                if (type.equals("insert")) {
                    if (!name_address.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                        int qu;
                        float totalprice, pr;
                        pr = Integer.parseInt(price);
                        qu = Integer.parseInt(quantity);
                        totalprice = qu * pr;
                        boolean ch = db.insertmat(name_address, qu, pr, totalprice,dt);
                        if (ch) {
                            Toast.makeText(getApplicationContext(), "Mat Details Added", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Mat Details not Added", Toast.LENGTH_LONG).show();
                        }
                    }
                } else if (type.equals("update")) {
                    if (!name_address.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                        int qu;
                        float totalprice, pr;
                        pr = Float.parseFloat(price);
                        qu = Integer.parseInt(quantity);
                        totalprice = qu * pr;
                        boolean ch = db.changemat(id, name_address, qu, pr, totalprice);
                        if (ch) {
                            Toast.makeText(getApplicationContext(), "Mat Details Updated", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Mat Details not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        });
    }
}

package com.intensivedatatech.totally;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.PartyModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddnoolActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText ed1, ed2, ed3, ed4,ed5,ed6,ed7,ed8,ed9;
    TextView dates;
    Button btn;
    Spinner spinner;
    int pid;
    List<String> uId = new ArrayList<String>();
    List<String> uname = new ArrayList<String>();
    String whiteprice, colorprice,whitequantity, colorquantity,cottonmatquantity,cottonmatprice,cottnmattotalprice,colormatquantity,colormatprice,colormattotalprice,description;
    float totalprice;
    String sdateneed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnool);
        spinner = findViewById(R.id.noolspinner);
        ed1 = findViewById(R.id.nwquantity);
        ed2 = findViewById(R.id.nwprice);
        ed3 = findViewById(R.id.ncquantity);
        ed4 = findViewById(R.id.ncprice);
        ed5=findViewById(R.id.nccmq);
        ed6=findViewById(R.id.nccmp);
        ed7=findViewById(R.id.ncclmq);
        ed8=findViewById(R.id.ncclmp);
        ed9=findViewById(R.id.ncdesc);
        btn = findViewById(R.id.noolbutton);
        dates = findViewById(R.id.n_date);
        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddnoolActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth + 1;
                        sdateneed = selectedyear + "-" + selectedmonth + "-" + selectedday;
                        dates.setText("Selected Date: "+sdateneed);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        db = new DatabaseHelper(getApplicationContext());
        uname.add("select party");
        uId.add("0");
        try {
            Cursor cursor = db.getparty();
            while (cursor.moveToNext()) {
                uname.add(cursor.getString(1));
                String a=String.valueOf(cursor.getInt(0));
                uId.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> langAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, uname);
        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(langAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pid = Integer.parseInt(uId.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    whitequantity=ed1.getText().toString();
                    whiteprice= ed2.getText().toString();
                    colorquantity=ed3.getText().toString();
                    colorprice=ed4.getText().toString();
                    cottonmatquantity=ed5.getText().toString();
                    cottonmatprice=ed6.getText().toString();
                    colormatquantity=ed7.getText().toString();
                    colormatprice=ed8.getText().toString();
                    description=ed9.getText().toString();

                    if(!whitequantity.isEmpty()&&!whiteprice.isEmpty()&&!colorquantity.isEmpty()&&!colorprice.isEmpty()){
                        int wq,cq;
                        float wp,cp,t1,t2;
                        wq=Integer.parseInt(whitequantity);
                        cq=Integer.parseInt(whiteprice);
                        wp=Float.parseFloat(whiteprice);
                        cp=Float.parseFloat(colorprice);
                        t1=wq*wp;
                        t2=cq*cp;
                        totalprice=t1+t2;
//                        boolean fl=db.insertnool(pid,wq,wp,cq,cp,totalprice,0,sdateneed);
                        boolean fl=true;
                        if(fl){
                            finish();
                            Log.d("nool","inserted");
                        }else{
                            Toast.makeText(getApplicationContext(),"Try Later",Toast.LENGTH_LONG).show();
                        }
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Please Enter the Values", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception r) {
                    r.printStackTrace();
                }

            }
        });
    }
}

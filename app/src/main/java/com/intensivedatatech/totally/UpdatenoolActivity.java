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
import com.intensivedatatech.totally.model.NoolModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdatenoolActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText ed1, ed2, ed5, ed6, ed10,ued;
    TextView dates;
    Button btn;
    Spinner spinner;
    int pid,nid;
    List<String> uId = new ArrayList<String>();
    List<String> uname = new ArrayList<String>();
    String whiteprice, whitequantity, cottonmatquantity, cottonmatprice, cottnmattotalprice, entryprice1,uentryprices;
    float totalprice, totalwhiteprice;
    String sdateneed;
    String mid;
    NoolModel ndata;
    String dd;
    boolean add,subs,add1,subs1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenool);
        db = new DatabaseHelper(getApplicationContext());
        spinner = findViewById(R.id.unoolspinner);
        ed1 = findViewById(R.id.unwquantity);
        ed2 = findViewById(R.id.unwprice);

        ed5 = findViewById(R.id.unccmq);
        ed6 = findViewById(R.id.unccmp);

        ed10 = findViewById(R.id.uncep1);
        ued = findViewById(R.id.uncep2);

        btn = findViewById(R.id.unoolbutton);
        dates = findViewById(R.id.un_date);
        dates.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        try {
            Intent i=getIntent();
            mid=String.valueOf(i.getIntExtra("mid",0));
            Cursor cursor=db.getnoolbid(mid);
            while (cursor.moveToNext()) {
//                ndata.setId(cursor.getInt(0));
//                ndata.setName(cursor.getString(1));
//                ndata.setAddress(cursor.getString(2));
//                ndata.setNumber(cursor.getString(3));
                ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getString(6), cursor.getFloat(7), cursor.getFloat(8), cursor.getFloat(9),cursor.getFloat(10),cursor.getFloat(11));
                nid=ndata.getId();
                pid=ndata.getPid();
                ed1.setText(String.valueOf(ndata.getWhitequantity()));
                ed2.setText(String.valueOf(ndata.getWhiteprice()));

                ed5.setText(String.valueOf(ndata.getCottonmatquantity()));
                ed6.setText(String.valueOf(ndata.getCottonmatprice()));

                if(String.valueOf(ndata.getEntryprice1()).startsWith("-")){
                    String ttemp=String.valueOf(ndata.getEntryprice1());
                    ttemp=ttemp.replace("-", "");
                    ued.setText(ttemp);
                }else{
                    String ttemp=String.valueOf(ndata.getEntryprice1());
                    ed10.setText(ttemp);
                }
//                ed10.setText(String.valueOf(ndata.getEntryprice1()));
//                ed11.setText(String.valueOf(ndata.getEntryprice2()));

//                dates.setText(String.valueOf(ndata.getDate()));

//                sdateneed=String.valueOf(ndata.getDate());

            }
        } catch (Exception r) {
            r.printStackTrace();
        }
//        dates.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar mcurrentDate = Calendar.getInstance();
//                int mYear = mcurrentDate.get(Calendar.YEAR);
//                int mMonth = mcurrentDate.get(Calendar.MONTH);
//                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog mDatePicker = new DatePickerDialog(UpdatenoolActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
//                        selectedmonth = selectedmonth + 1;
//                        sdateneed = selectedyear + "-" + selectedmonth + "-" + selectedday;
//                        dates.setText("Selected Date: " + sdateneed);
//                    }
//                }, mYear, mMonth, mDay);
//                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
//                mDatePicker.setTitle("Select date");
//                mDatePicker.show();
//            }
//        });

//        uname.add("select party");
//        uId.add("0");
//        try {
//            Cursor cursor = db.getparty();
//            while (cursor.moveToNext()) {
//                uname.add(cursor.getString(1));
//                String a = String.valueOf(cursor.getInt(0));
//                uId.add(a);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ArrayAdapter<String> langAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, uname);
//        langAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(langAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                pid = Integer.parseInt(uId.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    whitequantity = ed1.getText().toString();
                    whiteprice = ed2.getText().toString();

                    cottonmatquantity = ed5.getText().toString();
                    cottonmatprice = ed6.getText().toString();

                    entryprice1 = ed10.getText().toString();
                    uentryprices = ued.getText().toString();

                    if(!entryprice1.isEmpty()){
                        entryprice1="+"+entryprice1;
                    }else if(!uentryprices.isEmpty()){
                        entryprice1="-"+uentryprices;
                    }else{
                        Log.d("newbox","error");
                        Toast.makeText(getApplicationContext(),"Please Check the Given Values",Toast.LENGTH_LONG).show();
                    }

                    if(entryprice1.startsWith("+")){
                        entryprice1.replace("+", "");
                        add=true;
                        subs=false;
                    }
                    if(entryprice1.startsWith("-")){
                        entryprice1.replace("-", "");
                        subs=true;
                        add=false;
                    }



                    if (!whitequantity.isEmpty() && !whiteprice.isEmpty()  && !cottonmatquantity.isEmpty() && !cottonmatprice.isEmpty() ) {
                        float wq, wmateq;
                        float paidst=0;
                        float wp, t1, wmatep, twmatep, ftprice, ftfinalprice, fep1;
                        wq = Float.parseFloat(whitequantity);

                        wp = Float.parseFloat(whiteprice);

                        wmateq = Float.parseFloat(cottonmatquantity);
                        wmatep = Float.parseFloat(cottonmatprice);

                        fep1 = Float.parseFloat(entryprice1);


                        t1 = wq * wp;

                        twmatep = wmateq * wmatep;


                        ftprice = t1 - twmatep;


//                        ftfinalprice = Math.abs(ftprice) + fep1;
//                        sndfinalprice = Math.abs(sndprice) + fep2;

                        ftfinalprice=0;

                        if(add){
                            ftfinalprice=Math.abs(ftprice)+fep1;
                        }else if(subs){
                            ftfinalprice=Math.abs(ftprice)-Math.abs(fep1);
                        }else{
                            Log.d("error","no symbols found");
                        }



                        totalprice = ftfinalprice ;

//                        totalprice=t1+t2;
                        totalwhiteprice = t1;

                        try{
                            Cursor cursor=db.getpaidstatus(nid);

                            while (cursor.moveToNext()) {
                                paidst=cursor.getFloat(5);

                                Log.d("paid",String.valueOf(paidst));

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        boolean fl = db.changenool(nid,pid, wq, wp, totalprice, paidst, wmateq, wmatep, twmatep, fep1, totalwhiteprice);
//                        boolean fl=true;
                        if (fl) {
                            finish();
                            Log.d("nool", "inserted");
                        } else {
                            Toast.makeText(getApplicationContext(), "Try Later", Toast.LENGTH_LONG).show();
                        }
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Enter the Values", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception r) {
                    r.printStackTrace();
                }

            }
        });
    }

}

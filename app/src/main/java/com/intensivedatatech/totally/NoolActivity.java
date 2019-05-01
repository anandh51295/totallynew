package com.intensivedatatech.totally;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intensivedatatech.totally.adopter.NoolAdopter;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.NoolModel;

import java.util.ArrayList;

public class NoolActivity extends AppCompatActivity {
    DatabaseHelper db;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    ArrayList<NoolModel> data = new ArrayList<>();
    NoolModel ndata;
    NoolAdopter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nool);
        recyclerView = findViewById(R.id.noollist);
        fab = findViewById(R.id.noolfab);
        recyclerView.setHasFixedSize(true);
        db = new DatabaseHelper(getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoolActivity.this, AddnoolActivity.class);
                startActivity(intent);
            }
        });

        try {
            Cursor cursor = db.getnool();
            while (cursor.moveToNext()) {
//                ndata.setId(cursor.getInt(0));
//                ndata.setName(cursor.getString(1));
//                ndata.setAddress(cursor.getString(2));
//                ndata.setNumber(cursor.getString(3));
                ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getFloat(3), cursor.getInt(4), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7), cursor.getString(8));
                data.add(ndata);

            }
            adapter = new NoolAdopter(data);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            data.clear();
        } catch (Exception t) {
            t.printStackTrace();
        }
        try {
            Cursor cursor = db.getnool();
            while (cursor.moveToNext()) {
//                ndata.setId(cursor.getInt(0));
//                ndata.setName(cursor.getString(1));
//                ndata.setAddress(cursor.getString(2));
//                ndata.setNumber(cursor.getString(3));
                ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getFloat(3), cursor.getInt(4), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7), cursor.getString(8));
                data.add(ndata);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}

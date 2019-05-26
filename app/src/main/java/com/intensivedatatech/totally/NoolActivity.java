package com.intensivedatatech.totally;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
    String types;
    int ids;

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

        try {
            Intent i = getIntent();
            types = i.getStringExtra("type");
            ids = i.getIntExtra("id", 0);
        } catch (Exception r) {
            r.printStackTrace();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(types.equals("update")){
                        Toast.makeText(getApplicationContext(), "Sorry Can't Add in Group List mode", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(NoolActivity.this, AddnoolActivity.class);
                        startActivity(intent);
                    }
                } catch (Exception r) {
                    r.printStackTrace();
                }

            }
        });

        try {
            if (types.equals("update")) {

                Cursor cursor = db.getnoolbyid(String.valueOf(ids));
                while (cursor.moveToNext()) {
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getFloat(3), cursor.getInt(4), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7), cursor.getString(8), cursor.getInt(9), cursor.getFloat(10), cursor.getFloat(11), cursor.getInt(12), cursor.getFloat(13), cursor.getFloat(14), cursor.getString(15), cursor.getFloat(16), cursor.getFloat(17), cursor.getFloat(18), cursor.getFloat(19));
                    data.add(ndata);

                }
            } else {
                Cursor cursor = db.getnool();
                while (cursor.moveToNext()) {
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getFloat(3), cursor.getInt(4), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7), cursor.getString(8), cursor.getInt(9), cursor.getFloat(10), cursor.getFloat(11), cursor.getInt(12), cursor.getFloat(13), cursor.getFloat(14), cursor.getString(15), cursor.getFloat(16), cursor.getFloat(17), cursor.getFloat(18), cursor.getFloat(19));
                    data.add(ndata);

                }
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
            if (types.equals("update")) {
                Cursor cursor = db.getnoolbyid(String.valueOf(ids));
                while (cursor.moveToNext()) {
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getFloat(3), cursor.getInt(4), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7), cursor.getString(8), cursor.getInt(9), cursor.getFloat(10), cursor.getFloat(11), cursor.getInt(12), cursor.getFloat(13), cursor.getFloat(14), cursor.getString(15), cursor.getFloat(16), cursor.getFloat(17), cursor.getFloat(18), cursor.getFloat(19));
                    data.add(ndata);

                }
            } else {
                Cursor cursor = db.getnool();
                while (cursor.moveToNext()) {
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getFloat(3), cursor.getInt(4), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7), cursor.getString(8), cursor.getInt(9), cursor.getFloat(10), cursor.getFloat(11), cursor.getInt(12), cursor.getFloat(13), cursor.getFloat(14), cursor.getString(15), cursor.getFloat(16), cursor.getFloat(17), cursor.getFloat(18), cursor.getFloat(19));
                    data.add(ndata);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}

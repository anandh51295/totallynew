package com.intensivedatatech.totally;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.intensivedatatech.totally.adopter.GroupAdopter;
import com.intensivedatatech.totally.adopter.UserAdopter;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.PartyModel;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper db;
    ArrayList<PartyModel> data = new ArrayList<>();
    PartyModel ndata;
    GroupAdopter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        db = new DatabaseHelper(getApplicationContext());
        recyclerView = findViewById(R.id.group_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        try {
            Cursor cursor = db.getparty();
            while (cursor.moveToNext()) {
//                ndata.setId(cursor.getInt(0));
//                ndata.setName(cursor.getString(1));
//                ndata.setAddress(cursor.getString(2));
//                ndata.setNumber(cursor.getString(3));
                ndata = new PartyModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                data.add(ndata);

            }
            adapter = new GroupAdopter(data);
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
            Cursor cursor = db.getparty();
            while (cursor.moveToNext()) {
//                ndata.setId(cursor.getInt(0));
//                ndata.setName(cursor.getString(1));
//                ndata.setAddress(cursor.getString(2));
//                ndata.setNumber(cursor.getString(3));
                ndata = new PartyModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                data.add(ndata);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}

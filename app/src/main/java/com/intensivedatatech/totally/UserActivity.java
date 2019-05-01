package com.intensivedatatech.totally;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intensivedatatech.totally.adopter.UserAdopter;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.PartyModel;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    DatabaseHelper db;
    ArrayList<PartyModel> data = new ArrayList<>();
    PartyModel ndata;
    UserAdopter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        db = new DatabaseHelper(getApplicationContext());
        recyclerView = findViewById(R.id.userlist);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, AdduserActivity.class);
                intent.putExtra("type", "insert");
                startActivity(intent);
            }
        });


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
            adapter = new UserAdopter(data);
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

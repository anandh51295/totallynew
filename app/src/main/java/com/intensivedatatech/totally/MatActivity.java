package com.intensivedatatech.totally;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.intensivedatatech.totally.adopter.MatAdopter;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.MatModel;

import java.util.ArrayList;

public class MatActivity extends AppCompatActivity {
    DatabaseHelper db;
    FloatingActionButton btn;
    RecyclerView recyclerView;
    ArrayList<MatModel> data = new ArrayList<>();
    MatModel ndata;
    MatAdopter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat);
        btn = findViewById(R.id.fbaddmat);
        db = new DatabaseHelper(getApplicationContext());
        recyclerView = findViewById(R.id.matrecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MatActivity.this, AddmatActivity.class);
                intent.putExtra("type", "insert");
                startActivity(intent);
            }
        });

        try {
            Cursor cursor = db.getmat();
            while (cursor.moveToNext()) {
//                ndata.setId(cursor.getInt(0));
//                ndata.setName(cursor.getString(1));
//                ndata.setAddress(cursor.getString(2));
//                ndata.setNumber(cursor.getString(3));
                ndata = new MatModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getString(5));
                data.add(ndata);

            }
            adapter = new MatAdopter(data);
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
            Cursor cursor = db.getmat();
            while (cursor.moveToNext()) {
//                ndata.setId(cursor.getInt(0));
//                ndata.setName(cursor.getString(1));
//                ndata.setAddress(cursor.getString(2));
//                ndata.setNumber(cursor.getString(3));
                ndata = new MatModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getString(5));
                data.add(ndata);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}

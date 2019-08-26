package com.intensivedatatech.totally;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.intensivedatatech.totally.adopter.NoolAdopter;
import com.intensivedatatech.totally.helper.DatabaseHelper;
import com.intensivedatatech.totally.model.NoolModel;

import java.util.ArrayList;

public class NoolActivity extends AppCompatActivity {
    DatabaseHelper db;
    RecyclerView recyclerView;
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



        try {
            if (types.equals("update")) {

                Cursor cursor = db.getnoolbyid(String.valueOf(ids));
                while (cursor.moveToNext()) {
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getString(6), cursor.getFloat(7), cursor.getFloat(8), cursor.getFloat(9), cursor.getFloat(10), cursor.getFloat(11));
                    data.add(ndata);

                }
            } else {
                Cursor cursor = db.getnool();
                while (cursor.moveToNext()) {
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getString(6), cursor.getFloat(7), cursor.getFloat(8), cursor.getFloat(9), cursor.getFloat(10), cursor.getFloat(11));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
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
                return true;

            default:
                return super.onOptionsItemSelected(item);
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
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getString(6), cursor.getFloat(7), cursor.getFloat(8), cursor.getFloat(9), cursor.getFloat(10), cursor.getFloat(11));
                    data.add(ndata);

                }
            } else {
                Cursor cursor = db.getnool();
                while (cursor.moveToNext()) {
                    ndata = new NoolModel(cursor.getInt(0), cursor.getInt(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getString(6), cursor.getFloat(7), cursor.getFloat(8), cursor.getFloat(9), cursor.getFloat(10), cursor.getFloat(11));
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

package com.intensivedatatech.totally;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.intensivedatatech.totally.helper.DatabaseHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportActivity extends AppCompatActivity {
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    DatabaseHelper db;
    TextView idea;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        db = new DatabaseHelper(getApplicationContext());
        btn=findViewById(R.id.exp_btn);
        idea=findViewById(R.id.tv_info);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionsAndOpenFilePicker();
            }
        });
    }

    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            exp();
            exp1();
            exp2();
        }
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            exp();
            exp1();
            exp2();

        }
    }

    public boolean exp() {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "/totally/");
        if (!exportDir.exists()) { exportDir.mkdirs(); }

        File file = new File(exportDir, "party.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            Cursor curCSV = db.raw();
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext()) {
                String arrStr[]=null;
                String[] mySecondStringArray = new String[curCSV.getColumnNames().length];
                for(int i=0;i<curCSV.getColumnNames().length;i++)
                {
                    mySecondStringArray[i] =curCSV.getString(i);
                }
                csvWrite.writeNext(mySecondStringArray);
            }
            csvWrite.close();
            curCSV.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean exp1() {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "/totally/");
        if (!exportDir.exists()) { exportDir.mkdirs(); }

        File file = new File(exportDir, "matdetails.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            Cursor curCSV = db.raw1();
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext()) {
                String arrStr[]=null;
                String[] mySecondStringArray = new String[curCSV.getColumnNames().length];
                for(int i=0;i<curCSV.getColumnNames().length;i++)
                {
                    mySecondStringArray[i] =curCSV.getString(i);
                }
                csvWrite.writeNext(mySecondStringArray);
            }
            csvWrite.close();
            curCSV.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean exp2() {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "/totally/");
        if (!exportDir.exists()) { exportDir.mkdirs(); }

        File file = new File(exportDir, "nooldetails.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            Cursor curCSV = db.raw2();
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext()) {
                String arrStr[]=null;
                String[] mySecondStringArray = new String[curCSV.getColumnNames().length];
                for(int i=0;i<curCSV.getColumnNames().length;i++)
                {
                    mySecondStringArray[i] =curCSV.getString(i);
                }
                csvWrite.writeNext(mySecondStringArray);
            }
            csvWrite.close();
            curCSV.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}

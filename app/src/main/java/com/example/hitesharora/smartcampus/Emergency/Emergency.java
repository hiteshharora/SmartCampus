package com.example.hitesharora.smartcampus.Emergency;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hitesharora.smartcampus.MainActivity;
import com.example.hitesharora.smartcampus.R;

import java.util.ArrayList;
import java.util.List;

public class Emergency extends AppCompatActivity {

    private ListView emergencyListView;
    private ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        setTitle("Emergency");


        List<String> emergencyArrayList = new ArrayList<String>();
        emergencyArrayList.add("Emergency");
        emergencyArrayList.add("Jonesboro Police (non-emergency)");
        emergencyArrayList.add("University Police");
        emergencyArrayList.add("Jonesboro Police (non-emergency)");
        emergencyArrayList.add("Safety Concern Email");
        emergencyArrayList.add("Ways to report incidents");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, emergencyArrayList);
        emergencyListView.setAdapter(arrayAdapter);

        emergencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}

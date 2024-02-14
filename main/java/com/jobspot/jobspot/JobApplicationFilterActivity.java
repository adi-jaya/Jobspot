package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class JobApplicationFilterActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> arrayAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application_filter);
        
        setStatusFilter();
        setJobTypeFilter();
        setCityFilter();
    }
    
    public void setStatusFilter() {
        String[] status = {"Dilamar", "Diundang", "Ditolak", "Dibatalkan"};

        autoCompleteTextView = findViewById(R.id.statusAutoCompleteTextView);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_list_item, status);

        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String status = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(JobApplicationFilterActivity.this, status, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setJobTypeFilter() {
        String[] jobTypes = {"Full-Time", "Part-Time", "One-Time", "Contract"};

        autoCompleteTextView = findViewById(R.id.jobTypeAutoCompleteTextView);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_list_item, jobTypes);

        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String jobType = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(JobApplicationFilterActivity.this, jobType, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setCityFilter() {
        String[] cities = {"City 1", "City 2", "City 3", "City 4", "City 5"};

        autoCompleteTextView = findViewById(R.id.cityAutoCompleteTextView);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_list_item, cities);

        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(JobApplicationFilterActivity.this, city, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
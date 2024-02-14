package com.jobspot.jobspot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class JobVacancyActivity extends AppCompatActivity {

    List<JobVacancyModel> jobVacancyModelList;
    JobVacancyRecyclerViewAdapter jobVacancyRecyclerViewAdapter;
    JobVacancyModel jobVacancyModel;

    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    ImageView messageImageView;
    TextView messageTextView;
    SearchView searchView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_vacancy);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayout = findViewById(R.id.linearLayout);
        messageImageView = findViewById(R.id.messageImageView);
        messageTextView = findViewById(R.id.messageTextView);
        searchView = findViewById(R.id.searchView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setBottomNavigationView();
        setRecyclerView();
        setSearch();
    }

    public void setRecyclerView() {
        gridLayoutManager = new GridLayoutManager(JobVacancyActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        jobVacancyModelList = setJobVacancyModelList();

        if (jobVacancyModelList.isEmpty()) {
            showMessageNoDataAvailable();
        } else {
            hideMessage();
            jobVacancyRecyclerViewAdapter = new JobVacancyRecyclerViewAdapter(JobVacancyActivity.this, jobVacancyModelList);
            recyclerView.setAdapter(jobVacancyRecyclerViewAdapter);
        }
    }

    public List<JobVacancyModel> setJobVacancyModelList() {
        String[] companyList = {
                "Lorem Group Inc.",
                "Ipsum Group Inc.",
                "Dolor Group Inc.",
                "Sit Amet Group Inc.",
                "Consectetur Group Inc.",
                "Adipisicing Group Inc.",
                "Corporis Group Inc.",
                "Dignissimos Group Inc."
        };
        String aboutCompany = getString(R.string.lorem30);
        String industry = "IT & Telekomunikasi";
        String city = "California";
        String address = "Mountain View, California, Amerika Serikat";
        String jobDescription = getString(R.string.lorem30);
        String[] positionList = {"Backend Developer", "Frontend Developer", "Fullstack Developer", "UI/UX Design", "Backend Developer", "Frontend Developer", "Fullstack Developer", "UI/UX Design"};
        String jobType = "Full-Time";
        String gender = "Laki-laki";
        String age = "18-30";
        String education = "S1";
        String experience = "3 Tahun";
        String expDate = "12-12-2024";

        jobVacancyModelList = new ArrayList<>();

        for (int i = 0; i < companyList.length; ++i) {
            jobVacancyModel = new JobVacancyModel(
                    companyList[i],
                    industry,
                    city,
                    address,
                    aboutCompany,
                    positionList[i],
                    jobType,
                    gender,
                    age,
                    education,
                    experience,
                    jobDescription,
                    expDate
            );

            jobVacancyModelList.add(jobVacancyModel);
        }

        return jobVacancyModelList;
    }

    public void setSearch() {
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!jobVacancyModelList.isEmpty()) {
                    searchListOnQueryTextSubmit(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!jobVacancyModelList.isEmpty()) {
                    searchListOnQueryTextChange(newText);
                }
                return false;
            }
        });

    }

    public List<JobVacancyModel> searching(String text) {
        List<JobVacancyModel> dataSearchList = new ArrayList<>();

        for (JobVacancyModel jobVacancyModel : jobVacancyModelList) {
            if (jobVacancyModel.getPosition().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(jobVacancyModel);
            } else if (jobVacancyModel.getCompanyName().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(jobVacancyModel);
            }
        }

        return dataSearchList;
    }

    public void searchListOnQueryTextSubmit(String text) {
        List<JobVacancyModel> dataSearchList = searching(text);

        if (dataSearchList.isEmpty()) {
            showMessageNotFound();
        } else {
            jobVacancyRecyclerViewAdapter.setSearchList(dataSearchList);
            hideMessage();
        }
    }

    public void searchListOnQueryTextChange(String text) {
        List<JobVacancyModel> dataSearchList = searching(text);
        jobVacancyRecyclerViewAdapter.setSearchList(dataSearchList);
        hideMessage();
    }

    public void showMessageNoDataAvailable() {
        showMessage();
        messageTextView.setText("Tidak Ada Lowongan Pekerjaan");
    }

    public void showMessageNotFound() {
        showMessage();
        messageTextView.setText("Pencarian Tidak Ditemukan");
    }

    public void showMessage() {
        recyclerView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void hideMessage() {
        recyclerView.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.INVISIBLE);
    }

    public void setBottomNavigationView() {
        bottomNavigationView.setSelectedItemId(R.id.homeMenuItem);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.homeMenuItem:
                    startActivity( new Intent(getApplicationContext(), JobVacancyActivity.class) );
                    finish();
                    return true;
                case R.id.applicationMenuItem:
                    startActivity( new Intent(getApplicationContext(), JobApplicationActivity.class) );
                    finish();
                    return true;
                case R.id.profileMenuItem:
                    startActivity( new Intent(getApplicationContext(), JobseekerProfileActivity.class) );
                    finish();
                    return true;
            }

            return false;
        });
    }

    public void toVacancyFilterActivity(View view) {
        Intent vacancyFilterActivity = new Intent(JobVacancyActivity.this, JobVacancyFilterActivity.class);
        startActivity(vacancyFilterActivity);
    }
}
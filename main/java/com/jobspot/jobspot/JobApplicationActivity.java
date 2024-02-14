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

public class JobApplicationActivity extends AppCompatActivity {

    List<JobApplicationModel> jobApplicationModelList;
    JobApplicationRecyclerViewAdapter jobApplicationRecyclerViewAdapter;
    JobApplicationModel jobApplicationModel;

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
        setContentView(R.layout.activity_job_application);

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
        gridLayoutManager = new GridLayoutManager(JobApplicationActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        jobApplicationModelList = setJobApplicationModelList();

        if (jobApplicationModelList.isEmpty()) {
            showMessageNoDataAvailable();
        } else {
            hideMessage();
            jobApplicationRecyclerViewAdapter = new JobApplicationRecyclerViewAdapter(JobApplicationActivity.this, jobApplicationModelList);
            recyclerView.setAdapter(jobApplicationRecyclerViewAdapter);
        }
    }

    public List<JobApplicationModel> setJobApplicationModelList() {
        // Data Perusahaan
        String[] companyList = {
                "Lorem Group Inc.",
                "Ipsum Group Inc.",
                "Dolor Group Inc.",
                "Sit Amet Group Inc."
        };

        String aboutCompany = getString(R.string.lorem30);
        String industry = "IT & Telekomunikasi";
        String city = "California";
        String address = "Mountain View, California, Amerika Serikat";

        // Data Lowongan
        String[] positionList = {
                "Backend Developer",
                "Frontend Developer",
                "Fullstack Developer",
                "UI/UX Design"
        };

        String jobDescription = getString(R.string.lorem30);
        String jobType = "Full-Time";
        String gender = "Laki-laki";
        String age = "18-30";
        String education = "S1";
        String experience = "3 Tahun";

        // Data Lamaran
        String[] status = {"dilamar", "diundang", "ditolak", "dibatalkan"};
        String[] submissionDate = {"01-01-2024", "02-02-2024", "03-03-2024", "04-04-2024"};
        String[] decisionDate = {null, "05-02-2024", "06-03-2024", null};

        // Notification
        String dateSend = "01-01-2024";
        String timeSend = "09.00";
        String date = "05-01-2024";
        String time = "09.00";
        String location = getString(R.string.lorem10);
        String message = getString(R.string.lorem30);

        jobApplicationModelList = new ArrayList<>();

        for (int i = 0; i < companyList.length; ++i) {
            switch (status[i]) {
                case "diundang":
                    jobApplicationModel = new JobApplicationModel(
                            companyList[i], industry, city, address, aboutCompany, positionList[i], jobType,
                            gender, age, education, experience, jobDescription, status[i], submissionDate[i],
                            decisionDate[i], dateSend, timeSend, date, time, location, message
                    );

                    break;

                case "ditolak":
                    jobApplicationModel = new JobApplicationModel(
                            companyList[i], industry, city, address, aboutCompany, positionList[i], jobType,
                            gender, age, education, experience, jobDescription, status[i], submissionDate[i],
                            decisionDate[i], dateSend, timeSend, "", "", "", ""
                    );

                    break;

                default:
                    jobApplicationModel = new JobApplicationModel(
                            companyList[i], industry, city, address, aboutCompany, positionList[i], jobType,
                            gender, age, education, experience, jobDescription, status[i], submissionDate[i],
                            decisionDate[i], "", "", "", "", "", ""
                    );
            }

            jobApplicationModelList.add(jobApplicationModel);
        }

        return jobApplicationModelList;
    }

    public void setSearch() {
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!jobApplicationModelList.isEmpty()) {
                    searchListOnQueryTextSubmit(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!jobApplicationModelList.isEmpty()) {
                    searchListOnQueryTextChange(newText);
                }
                return false;
            }
        });
    }

    public List<JobApplicationModel> searching(String text) {
        List<JobApplicationModel> dataSearchList = new ArrayList<>();

        for (JobApplicationModel jobApplicationModel : jobApplicationModelList) {
            if (jobApplicationModel.getPosition().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(jobApplicationModel);
            } else if (jobApplicationModel.getCompanyName().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(jobApplicationModel);
            }
        }

        return dataSearchList;
    }

    public void searchListOnQueryTextSubmit(String text) {
        List<JobApplicationModel> dataSearchList = searching(text);

        if (dataSearchList.isEmpty()) {
            showMessageNotFound();
        } else {
            jobApplicationRecyclerViewAdapter.setSearchList(dataSearchList);
            hideMessage();
        }
    }

    public void searchListOnQueryTextChange(String text) {
        List<JobApplicationModel> dataSearchList = searching(text);
        jobApplicationRecyclerViewAdapter.setSearchList(dataSearchList);
        hideMessage();
    }

    public void showMessageNoDataAvailable() {
        showMessage();
        messageImageView.setImageResource(R.drawable.ic_bookmark);
        messageTextView.setText("Tidak Ada lamaran Pekerjaan");
    }

    public void showMessageNotFound() {
        showMessage();
        messageImageView.setImageResource(R.drawable.ic_search_data);
        messageTextView.setText("Pencarian Tidak Ditemukan");
    }

    public void showMessage() {
        recyclerView.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    public void hideMessage() {
        linearLayout.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void setBottomNavigationView() {
        bottomNavigationView.setSelectedItemId(R.id.applicationMenuItem);

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

    public void toApplicationFilterActivity(View view) {
        Intent applicationFilterActivity = new Intent(JobApplicationActivity.this, JobApplicationFilterActivity.class);
        startActivity(applicationFilterActivity);
    }
}
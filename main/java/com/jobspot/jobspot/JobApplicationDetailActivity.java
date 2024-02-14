package com.jobspot.jobspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class JobApplicationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application_detail);

        setActionBar();
        setApplicationDetailViewPagerAdapter();
    }

    public void setApplicationDetailViewPagerAdapter() {
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        JobApplicationDetailViewPagerAdapter jobApplicationDetailViewPagerAdapter = new JobApplicationDetailViewPagerAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        DescriptionFragment descriptionFragment = new DescriptionFragment();
                        descriptionFragment.setArguments(getJobDescriptionData());
                        return descriptionFragment;
                    case 1:
                        CompanyFragment companyFragment = new CompanyFragment();
                        companyFragment.setArguments(getCompanyData());
                        return companyFragment;
                    case 2:
                        NotificationFragment notificationFragment = new NotificationFragment();
                        notificationFragment.setArguments(getNotificationData());
                        return notificationFragment;
                }

                return null;
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        };

        viewPager2.setAdapter(jobApplicationDetailViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    public Bundle getJobDescriptionData() {
        Bundle jobDescriptionData = new Bundle();

        if (getIntent().getExtras() != null) {
            jobDescriptionData.putString("parentActivity", "JobApplicationActivity");
            jobDescriptionData.putString("position", getIntent().getExtras().getString("position"));
            jobDescriptionData.putString("jobType", getIntent().getExtras().getString("jobType"));
            jobDescriptionData.putString("gender", getIntent().getExtras().getString("gender"));
            jobDescriptionData.putString("age", getIntent().getExtras().getString("age"));
            jobDescriptionData.putString("education", getIntent().getExtras().getString("education"));
            jobDescriptionData.putString("experience", getIntent().getExtras().getString("experience"));
            jobDescriptionData.putString("jobDescription", getIntent().getExtras().getString("jobDescription"));
            jobDescriptionData.putString("status", getIntent().getExtras().getString("status"));

            return jobDescriptionData;
        }

        return null;
    }

    public Bundle getCompanyData() {
        Bundle companyData = new Bundle();

        if (getIntent().getExtras() != null) {
            companyData.putString("companyName", getIntent().getExtras().getString("companyName"));
            companyData.putString("aboutCompany", getIntent().getExtras().getString("aboutCompany"));
            companyData.putString("industry", getIntent().getExtras().getString("industry"));
            companyData.putString("city", getIntent().getExtras().getString("city"));
            companyData.putString("address", getIntent().getExtras().getString("address"));

            return companyData;
        }

        return null;
    }

    public Bundle getNotificationData() {
        Bundle notificationData = new Bundle();

        if (getIntent().getExtras() != null) {
            notificationData.putString("status", getIntent().getExtras().getString("status"));
            notificationData.putString("dateSend", getIntent().getExtras().getString("dateSend"));
            notificationData.putString("timeSend", getIntent().getExtras().getString("timeSend"));
            notificationData.putString("date", getIntent().getExtras().getString("date"));
            notificationData.putString("time", getIntent().getExtras().getString("time"));
            notificationData.putString("location", getIntent().getExtras().getString("location"));
            notificationData.putString("message", getIntent().getExtras().getString("message"));

            return notificationData;
        }

        return null;
    }

    public void setActionBar() {
        this.setTitle(getIntent().getStringExtra("position"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
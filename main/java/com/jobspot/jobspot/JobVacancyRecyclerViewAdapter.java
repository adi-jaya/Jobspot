package com.jobspot.jobspot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobVacancyRecyclerViewAdapter extends RecyclerView.Adapter<JobVacancyViewHolder> {

    private final Context context;
    private List<JobVacancyModel> jobVacancyModelList;

    public void setSearchList(List<JobVacancyModel> dataSearchList) {
        this.jobVacancyModelList = dataSearchList;
        notifyDataSetChanged();
    }

    public JobVacancyRecyclerViewAdapter(Context context, List<JobVacancyModel> jobVacancyModelList) {
        this.context = context;
        this.jobVacancyModelList = jobVacancyModelList;
    }

    @NonNull
    @Override
    public JobVacancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_vacancy_item, parent, false);
        return new JobVacancyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobVacancyViewHolder holder, int position) {
        holder.positionTextView.setText(jobVacancyModelList.get(position).getPosition());
        holder.jobTypeTextView.setText(jobVacancyModelList.get(position).getJobType());
        holder.expDateTextView.setText(String.format("<%s", jobVacancyModelList.get(position).getExpDate()));
        holder.companyNameTextView.setText(jobVacancyModelList.get(position).getCompanyName());
        holder.cityTextView.setText(jobVacancyModelList.get(position).getCity());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vacancyDetailActivity = new Intent(context, JobVacancyDetailActivity.class);
                Bundle dataVacancy = new Bundle();

                // Job Description Data
                dataVacancy.putString("position", jobVacancyModelList.get(holder.getAdapterPosition()).getPosition());
                dataVacancy.putString("jobType", jobVacancyModelList.get(holder.getAdapterPosition()).getJobType());
                dataVacancy.putString("gender", jobVacancyModelList.get(holder.getAdapterPosition()).getGender());
                dataVacancy.putString("age", jobVacancyModelList.get(holder.getAdapterPosition()).getAge());
                dataVacancy.putString("education", jobVacancyModelList.get(holder.getAdapterPosition()).getEducation());
                dataVacancy.putString("experience", jobVacancyModelList.get(holder.getAdapterPosition()).getExperience());
                dataVacancy.putString("jobDescription", jobVacancyModelList.get(holder.getAdapterPosition()).getJobDescription());

                // Data Company
                dataVacancy.putString("companyName", jobVacancyModelList.get(holder.getAdapterPosition()).getCompanyName());
                dataVacancy.putString("aboutCompany", jobVacancyModelList.get(holder.getAdapterPosition()).getAboutCompany());
                dataVacancy.putString("industry", jobVacancyModelList.get(holder.getAdapterPosition()).getIndustry());
                dataVacancy.putString("city", jobVacancyModelList.get(holder.getAdapterPosition()).getCity());
                dataVacancy.putString("address", jobVacancyModelList.get(holder.getAdapterPosition()).getAddress());

                vacancyDetailActivity.putExtras(dataVacancy);
                context.startActivity(vacancyDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobVacancyModelList.size();
    }

}

class JobVacancyViewHolder extends RecyclerView.ViewHolder {
    TextView positionTextView, jobTypeTextView, expDateTextView, companyNameTextView, cityTextView;
    CardView cardView;

    public JobVacancyViewHolder(@NonNull View itemView) {
        super(itemView);

        positionTextView = itemView.findViewById(R.id.positionTextView);
        jobTypeTextView = itemView.findViewById(R.id.jobTypeTextView);
        expDateTextView = itemView.findViewById(R.id.statusApplyTextView);
        companyNameTextView = itemView.findViewById(R.id.companyNameTextView);
        cityTextView = itemView.findViewById(R.id.cityTextView);
        cardView = itemView.findViewById(R.id.cardView);
    }
}

package com.jobspot.jobspot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class JobApplicationRecyclerViewAdapter extends RecyclerView.Adapter<JobApplicationViewHolder> {

    private final Context context;
    private List<JobApplicationModel> jobApplicationModelList;

    public void setSearchList(List<JobApplicationModel> dataSearchList) {
        this.jobApplicationModelList = dataSearchList;
        notifyDataSetChanged();
    }

    public JobApplicationRecyclerViewAdapter(Context context, List<JobApplicationModel> jobApplicationModelList) {
        this.context = context;
        this.jobApplicationModelList = jobApplicationModelList;
    }

    @NonNull
    @Override
    public JobApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_application_item, parent, false);
        return new JobApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobApplicationViewHolder holder, int position) {
        holder.positionTextView.setText(jobApplicationModelList.get(position).getPosition());
        holder.jobTypeTextView.setText(jobApplicationModelList.get(position).getJobType());
        holder.companyNameTextView.setText(jobApplicationModelList.get(position).getCompanyName());
        holder.cityTextView.setText(jobApplicationModelList.get(position).getCity());
        holder.statusTextView.setText(jobApplicationModelList.get(position).getStatus());
        holder.submissionDateTextView.setText(jobApplicationModelList.get(position).getSubmissionDate());

        setTimelineApplication(holder, position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent applicationDetailActivity = new Intent(context, JobApplicationDetailActivity.class);
                Bundle applicationData = new Bundle();

                // Data Lowongan
                applicationData.putString("position", jobApplicationModelList.get(holder.getAdapterPosition()).getPosition());
                applicationData.putString("jobType", jobApplicationModelList.get(holder.getAdapterPosition()).getJobType());
                applicationData.putString("gender", jobApplicationModelList.get(holder.getAdapterPosition()).getGender());
                applicationData.putString("age", jobApplicationModelList.get(holder.getAdapterPosition()).getAge());
                applicationData.putString("education", jobApplicationModelList.get(holder.getAdapterPosition()).getEducation());
                applicationData.putString("experience", jobApplicationModelList.get(holder.getAdapterPosition()).getExperience());
                applicationData.putString("jobDescription", jobApplicationModelList.get(holder.getAdapterPosition()).getJobDescription());

                // Data Perusahaan
                applicationData.putString("companyName", jobApplicationModelList.get(holder.getAdapterPosition()).getCompanyName());
                applicationData.putString("industry", jobApplicationModelList.get(holder.getAdapterPosition()).getIndustry());
                applicationData.putString("city", jobApplicationModelList.get(holder.getAdapterPosition()).getCity());
                applicationData.putString("address", jobApplicationModelList.get(holder.getAdapterPosition()).getAddress());
                applicationData.putString("aboutCompany", jobApplicationModelList.get(holder.getAdapterPosition()).getAboutCompany());

                // Data Lamaran
                applicationData.putString("status", jobApplicationModelList.get(holder.getAdapterPosition()).getStatus());
                applicationData.putString("submissionDate", jobApplicationModelList.get(holder.getAdapterPosition()).getSubmissionDate());
                applicationData.putString("decisionDate", jobApplicationModelList.get(holder.getAdapterPosition()).getDecisionDate());

                // Data Undangan Wawancara
                applicationData.putString("dateSend", jobApplicationModelList.get(holder.getAdapterPosition()).getDateSend());
                applicationData.putString("timeSend", jobApplicationModelList.get(holder.getAdapterPosition()).getTimeSend());
                applicationData.putString("date", jobApplicationModelList.get(holder.getAdapterPosition()).getDate());
                applicationData.putString("time", jobApplicationModelList.get(holder.getAdapterPosition()).getTime());
                applicationData.putString("location", jobApplicationModelList.get(holder.getAdapterPosition()).getLocation());
                applicationData.putString("message", jobApplicationModelList.get(holder.getAdapterPosition()).getMessage());

                applicationDetailActivity.putExtras(applicationData);
                context.startActivity(applicationDetailActivity);
            }
        });
    }

    // Mengatur icon untuk submission dan decision status
    public void setTimelineApplication(JobApplicationViewHolder holder, int position) {

        // Mengatur decision date jika nilai nya null
        String decisionDate = jobApplicationModelList.get(position).getDecisionDate();
        if (Objects.equals(decisionDate, "null") || decisionDate == null || decisionDate.isEmpty()) {
            holder.decisionDateTextView.setText("-");
        } else {
            holder.decisionDateTextView.setText(decisionDate);
        }

        // Mengatur icon
        String status = jobApplicationModelList.get(position).getStatus();
        switch (status) {
            case "diundang":
                holder.iconSubmissionImageView.setImageResource(R.drawable.ic_outline_check_circle_24);
                holder.iconDecisionImageView.setImageResource(R.drawable.ic_outline_check_circle_24);
                break;
            case "dilamar":
                holder.iconSubmissionImageView.setImageResource(R.drawable.ic_outline_check_circle_24);
                holder.iconDecisionImageView.setImageResource(R.drawable.ic_outline_access_time_24);
                break;
            case "ditolak":
                holder.iconSubmissionImageView.setImageResource(R.drawable.ic_outline_check_circle_24);
                holder.iconDecisionImageView.setImageResource(R.drawable.ic_outline_cancel_24);
                break;
            case "dibatalkan":
                holder.iconSubmissionImageView.setImageResource(R.drawable.ic_outline_cancel_24);
                holder.iconDecisionImageView.setImageResource(R.drawable.ic_outline_cancel_24);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return jobApplicationModelList.size();
    }
}

class JobApplicationViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView iconSubmissionImageView, iconDecisionImageView;
    TextView positionTextView,
            jobTypeTextView,
            companyNameTextView,
            cityTextView,
            statusTextView,
            submissionDateTextView,
            decisionDateTextView;

    public JobApplicationViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        iconSubmissionImageView = itemView.findViewById(R.id.iconSubmissionImageView);
        iconDecisionImageView = itemView.findViewById(R.id.iconDecisionImageView);
        decisionDateTextView = itemView.findViewById(R.id.decisionDateTextView);
        positionTextView = itemView.findViewById(R.id.positionTextView);
        jobTypeTextView = itemView.findViewById(R.id.jobTypeTextView);
        companyNameTextView = itemView.findViewById(R.id.companyNameTextView);
        cityTextView = itemView.findViewById(R.id.cityTextView);
        statusTextView = itemView.findViewById(R.id.statusTextView);
        submissionDateTextView = itemView.findViewById(R.id.submissionDateTextView);
        decisionDateTextView = itemView.findViewById(R.id.decisionDateTextView);
    }
}

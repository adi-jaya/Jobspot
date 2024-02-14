package com.jobspot.jobspot;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class DescriptionFragment extends Fragment {

    MaterialButton actionButton;
    TextView jobDescriptionTextView, jobPositionTextView, jobTypeTextView, genderTextView, ageTextView, educationTextView, experienceTextView;
    String parentActivity, status;

    public static DescriptionFragment newInstance(Bundle jobDescriptionData) {
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        descriptionFragment.setArguments(jobDescriptionData);
        return descriptionFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        configFragment(view);

        return view;
    }

    public void configFragment(View view) {
        actionButton = view.findViewById(R.id.actionButton);
        jobDescriptionTextView = view.findViewById(R.id.jobDescriptionTextView);
        jobPositionTextView = view.findViewById(R.id.jobPositionTextView);
        jobTypeTextView = view.findViewById(R.id.jobTypeTextView);
        genderTextView = view.findViewById(R.id.genderTextView);
        ageTextView = view.findViewById(R.id.ageTextView);
        educationTextView = view.findViewById(R.id.educationTextView);
        experienceTextView = view.findViewById(R.id.experienceTextView);

        if (getArguments() != null) {
            jobDescriptionTextView.setText(getArguments().getString("jobDescription"));
            jobPositionTextView.setText(getArguments().getString("position"));
            jobTypeTextView.setText(getArguments().getString("jobType"));
            genderTextView.setText(getArguments().getString("gender"));
            ageTextView.setText(getArguments().getString("age"));
            educationTextView.setText(getArguments().getString("education"));
            experienceTextView.setText(getArguments().getString("experience"));

            parentActivity = getArguments().getString("parentActivity");
            status = getArguments().getString("status");

            setActionButtonAndDialogBox(view);
        }
    }

    public void applyVacancy(View view) {
        Toast.makeText(view.getContext(), "Mengajukan lamaran", Toast.LENGTH_SHORT).show();
        toJobApplicationActivity(view);
    }

    public void cancelApplication(View view) {
        Toast.makeText(view.getContext(), "Lamaran telah dibatalkan", Toast.LENGTH_SHORT).show();
        toJobApplicationActivity(view);
    }

    public void setActionButtonAndDialogBox(View view) {
        switch(parentActivity) {
            case "JobVacancyActivity":
                setApplyButton();
                setConfirmDialogBox(view, parentActivity);
                break;
            case "JobApplicationActivity":
                setCancelApplyButton();
                setConfirmDialogBox(view, parentActivity);
                break;
        }
    }

    public void setApplyButton() {
        actionButton.setVisibility(View.VISIBLE);
        actionButton.setText("Lamar");
        actionButton.setBackgroundColor(getResources().getColor(R.color.blue_dark));
    }

    public void setCancelApplyButton() {
        if (!status.equals("dilamar")) {
            actionButton.setVisibility(View.INVISIBLE);
        } else {
            actionButton.setVisibility(View.VISIBLE);
            actionButton.setText("Batalkan");
            actionButton.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    public void setConfirmDialogBox(View view, String parentActivity) {
        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_dialog_box));
        dialog.setCancelable(false);

        TextView titleTextView = dialog.findViewById(R.id.titleTextView);
        TextView subtitleTextView = dialog.findViewById(R.id.subtitleTextView);
        MaterialButton confirmButton = dialog.findViewById(R.id.confirmButton);
        MaterialButton cancelButton = dialog.findViewById(R.id.cancelButton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(view.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        switch(parentActivity) {
            case "JobVacancyActivity":
                setConfirmApplyDialogBox(view, dialog, confirmButton, titleTextView, subtitleTextView);
                break;
            case "JobApplicationActivity":
                setConfirmCancelApplyDialogBox(view, dialog, confirmButton, titleTextView, subtitleTextView);
                break;
        }
    }

    public void setConfirmApplyDialogBox(View view, Dialog dialog, MaterialButton confirmButton,
                                         TextView titleTextView, TextView subtitleTextView) {

        titleTextView.setText("Melamar Lowongan Pekerjaan");
        subtitleTextView.setText("Apakah Anda yakin ingin melamar lowongan pekerjaan ini?");

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyVacancy(view);
                dialog.dismiss();
            }
        });
    }

    public void setConfirmCancelApplyDialogBox(View view, Dialog dialog, MaterialButton confirmButton,
                                               TextView titleTextView, TextView subtitleTextView) {

        titleTextView.setText("Membatalkan Lamaran Pekerjaan");
        subtitleTextView.setText("Apakah Anda yakin ingin membatalkan lamaran untuk lowongan pekerjaan ini?");

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelApplication(view);
                dialog.dismiss();
            }
        });
    }

    public void toJobApplicationActivity(View view) {
        Intent applicationActivity = new Intent(view.getContext(), JobApplicationActivity.class);
        startActivity(applicationActivity);
        getActivity().finish();
    }

}
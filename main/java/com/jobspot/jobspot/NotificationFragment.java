package com.jobspot.jobspot;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotificationFragment extends Fragment {
    LinearLayout notificationLinearLayout, invitedNoticeLinearLayout, rejectedNoticeLinearLayout;
    TextView titleMessageTextView, dateMessageTextView, timeMessageTextView, dateTextView, timeTextView, locationTextView, messageTextView;

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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        configFragment(view);

        return view;
    }

    public void configFragment(View view) {
        notificationLinearLayout = view.findViewById(R.id.notificationLinearLayout);
        invitedNoticeLinearLayout = view.findViewById(R.id.invitedNoticeLinearLayout);
        rejectedNoticeLinearLayout = view.findViewById(R.id.rejectedNoticeLinearLayout);

        titleMessageTextView = view.findViewById(R.id.titleMessageTextView);
        dateMessageTextView = view.findViewById(R.id.dateMessageTextView);
        timeMessageTextView = view.findViewById(R.id.timeMessageTextView);
        dateTextView = view.findViewById(R.id.dateTextView);
        timeTextView = view.findViewById(R.id.timeTextView);
        locationTextView = view.findViewById(R.id.locationTextView);
        messageTextView = view.findViewById(R.id.messageTextView);

        if (getArguments() != null) {
            setNotification(getArguments().getString("status"));
        }
    }

    public void setNotification(String status) {
        /*
        * Mengecek status lamaran:
        * Notification ditampilkan hanya jika status lamaran "diundang" atau "ditolak"
        * - Untuk status lamaran "diundang" invitedNoticeLinearLayout ditampilkan
        * - Untuk status lamaran "ditolak" rejectedNoticeLinearLayout ditampilkan
        *
        * */

        switch(status) {
            case "diundang":
                setInvitedNoticeLinearLayout();
                break;
            case "ditolak":
                setRejectedNoticeLinearLayout();
                break;
            default:
                notificationLinearLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void setInvitedNoticeLinearLayout() {
        notificationLinearLayout.setVisibility(View.VISIBLE);
        invitedNoticeLinearLayout.setVisibility(View.VISIBLE);
        rejectedNoticeLinearLayout.setVisibility(View.GONE);

        titleMessageTextView.setText("Undangan Wawancara");
        dateMessageTextView.setText(getArguments().getString("dateSend"));
        timeMessageTextView.setText(getArguments().getString("timeSend"));

        dateTextView.setText(getArguments().getString("date"));
        timeTextView.setText(getArguments().getString("time"));
        locationTextView.setText(getArguments().getString("location"));
        messageTextView.setText(getArguments().getString("message"));
    }

    public void setRejectedNoticeLinearLayout() {
        notificationLinearLayout.setVisibility(View.VISIBLE);
        invitedNoticeLinearLayout.setVisibility(View.GONE);
        rejectedNoticeLinearLayout.setVisibility(View.VISIBLE);

        titleMessageTextView.setText("Lamaran Ditolak");
        dateMessageTextView.setText(getArguments().getString("dateSend"));
        timeMessageTextView.setText(getArguments().getString("timeSend"));
    }
}
package com.jobspot.jobspot;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CompanyFragment extends Fragment {

    TextView companyNameTextView, aboutCompanyTextView, industryTextView, addressTextView;

    public static CompanyFragment newInstance(Bundle companyData) {
        CompanyFragment companyFragment = new CompanyFragment();
        companyFragment.setArguments(companyData);
        return companyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);

        configFragment(view);

        return view;
    }

    public void configFragment(View view) {
        companyNameTextView = view.findViewById(R.id.companyNameTextView);
        aboutCompanyTextView = view.findViewById(R.id.aboutCompanyTextView);
        industryTextView = view.findViewById(R.id.industryTextView);
        addressTextView = view.findViewById(R.id.addressTextView);

        if (getArguments() != null) {
            companyNameTextView.setText(getArguments().getString("companyName"));
            aboutCompanyTextView.setText(getArguments().getString("aboutCompany"));
            industryTextView.setText(getArguments().getString("industry"));
            addressTextView.setText(getArguments().getString("address"));
        }
    }
}
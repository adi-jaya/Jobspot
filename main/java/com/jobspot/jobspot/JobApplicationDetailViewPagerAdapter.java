package com.jobspot.jobspot;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class JobApplicationDetailViewPagerAdapter extends FragmentStateAdapter {

    public JobApplicationDetailViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new DescriptionFragment();
            case 1:
                return new CompanyFragment();
            case 2:
                return new NotificationFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

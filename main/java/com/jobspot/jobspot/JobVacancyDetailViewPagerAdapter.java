package com.jobspot.jobspot;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class JobVacancyDetailViewPagerAdapter extends FragmentStateAdapter {
    public JobVacancyDetailViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

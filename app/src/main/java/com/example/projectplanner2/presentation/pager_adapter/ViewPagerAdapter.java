package com.example.projectplanner2.presentation.pager_adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projectplanner2.presentation.tabs.HomeFragment;
import com.example.projectplanner2.presentation.tabs.QuoteFragment;
import com.example.projectplanner2.presentation.tabs.ReportFragment;
import com.example.projectplanner2.presentation.tabs.UpdateFragment;

import java.util.ArrayList;
import java.util.List;
//
//public class TabsPagerAdapter extends FragmentPagerAdapter {
//    private final List<Fragment> fragmentList = new ArrayList<>();
//    private final List<String> fragmentTitleList = new ArrayList<>();
//    public TabsPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//    public void addFragment(Fragment fragment, String title) {
//        fragmentList.add(fragment);
//        fragmentTitleList.add(title);
//    }
//
//
//    @Override
//    public Fragment getItem(int i) {
//        return fragmentList.get(i);
//    }
//
//    @Override
//    public int getCount() {
//        return fragmentList.size();
//    }
//}
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
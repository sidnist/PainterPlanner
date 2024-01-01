package com.example.projectplanner2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectplanner2.databinding.FragmentTabBinding;
import com.example.projectplanner2.presentation.pager_adapter.ViewPagerAdapter;
import com.example.projectplanner2.presentation.tabs.HomeFragment;
import com.example.projectplanner2.presentation.tabs.QuoteFragment;
import com.example.projectplanner2.presentation.tabs.ReportFragment;
import com.example.projectplanner2.presentation.tabs.UpdateFragment;

public class TabFragment extends Fragment {
    FragmentTabBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTabBinding.inflate(inflater, container, false);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // Set the adapter to the ViewPager

// Add your fragments and titles
        pagerAdapter.addFragment(new HomeFragment(), "Home");
        pagerAdapter.addFragment(new QuoteFragment(), "Quote");
        pagerAdapter.addFragment(new ReportFragment(), "Report");
        pagerAdapter.addFragment(new UpdateFragment(), "Update");


        binding.viewPager.setAdapter(pagerAdapter);
        // Connect the TabLayout with the ViewPager
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        return binding.getRoot();
    }
}
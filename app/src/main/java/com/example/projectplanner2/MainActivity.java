package com.example.projectplanner2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectplanner2.databinding.ActivityMainBinding;
import com.example.projectplanner2.presentation.pager_adapter.ViewPagerAdapter;
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
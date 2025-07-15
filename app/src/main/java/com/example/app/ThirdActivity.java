package com.example.app;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThirdActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;

    String[] titles = {"Air plane", "Apple", "LapTop"};
    int[] images = {R.drawable.airplane1, R.drawable.apple, R.drawable.laptop};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(titles[position]);
            tab.setIcon(images[position]);

        }).attach();
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(), tab.getText(), Toast.LENGTH_SHORT).show();

                TextView textView = new TextView(getApplicationContext());
                textView.setTextColor(Color.RED);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(getApplicationContext(), tab.getText(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

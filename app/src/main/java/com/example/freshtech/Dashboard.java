package com.example.freshtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class Dashboard extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewpageAdapter viewpageAdapter;

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        viewPager2=findViewById(R.id.viewPager);
        viewpageAdapter=new ViewpageAdapter(this);
        viewPager2.setAdapter(viewpageAdapter);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
             if(id==R.id.home){
                 viewPager2.setCurrentItem(0);
             }
                if(id==R.id.browse){
                    viewPager2.setCurrentItem(1);
                }
                if(id==R.id.cart){
                    viewPager2.setCurrentItem(2);
                }
                if(id==R.id.profile){
                    viewPager2.setCurrentItem(3);
                }


                return false;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.browse).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.cart).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.cart).setChecked(true);
                        break;

                }
                super.onPageSelected(position);
            }
        });

    }
}
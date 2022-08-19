package com.example.paperbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.paperbox.databinding.ActivityMainBinding;
import com.example.paperbox.ui.main.SectionsPagerAdapter;
import com.example.paperbox.ui.main.my_info;
import com.example.paperbox.ui.main.refund;
import com.example.paperbox.ui.main.weighing;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SubMain extends AppCompatActivity {

    // 메인화면

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;
        Intent intent = new Intent(this, MainActivity.class);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "로그아웃", Snackbar.LENGTH_LONG)
                        .setAction("확인", new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                FirebaseAuth.getInstance().signOut();
                                startActivity(intent);
                                finish();
                            }
                        }).show();
            }
        });



    }
}

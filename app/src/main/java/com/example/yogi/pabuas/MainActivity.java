package com.example.yogi.pabuas;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    protected FirstFragment fragment1;
    protected SecondFragment fragment2;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragment1 = FirstFragment.newInstance("New Fragment 1");
        this.fragment2 = SecondFragment.newInstance("New Fragment 2");

    }


}

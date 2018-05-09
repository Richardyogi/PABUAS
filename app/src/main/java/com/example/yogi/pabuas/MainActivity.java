package com.example.yogi.pabuas;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    protected FirstFragment fragment1;
    protected SecondFragment fragment2;
    private android.support.v4.app.FragmentManager fragmentManager;

    private SensorManager mSensorManager;
    protected Sensor mSensorAccelerometer;
    protected Sensor mSensorMagnetometer;

    private Display mDisplay;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragment1 = FirstFragment.newInstance("New Fragment 1");
        this.fragment2 = SecondFragment.newInstance("New Fragment 2");
        this.fragmentManager = this.getSupportFragmentManager();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container,this.fragment1).addToBackStack(null).commit();

//        this.mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        this.mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

//        WindowManager wm = (WindowManager)this.getSystemService(WINDOW_SERVICE);
//        mDisplay = wm.getDefaultDisplay();
    }


    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            if(this.fragment1.isAdded()){
                ft.show(this.fragment1);
            }
            else{
                ft.add(R.id.fragment_container,this.fragment1);
            }
            if(this.fragment2.isAdded()){
                ft.hide(this.fragment2);
            }
        }
        else if(page==2){
            if(this.fragment2.isAdded()){
                ft.show(this.fragment2);
            }
            else{
                ft.add(R.id.fragment_container,this.fragment2).addToBackStack(null);
            }
            if(this.fragment1.isAdded()){
                ft.hide(this.fragment1);
            }
        }
        ft.commit();
    }

}

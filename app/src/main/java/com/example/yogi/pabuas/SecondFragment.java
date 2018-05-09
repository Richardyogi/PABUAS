package com.example.yogi.pabuas;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Yogi on 03/05/2018.
 */

public class SecondFragment extends Fragment implements OnClickListener,SensorEventListener {
    private ImageView ivCanvas;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private ImageView ivPause;

    Handler handler;
    TextView tv_waktu;
    private int minutes, seconds, milliSeconds;
    private long millisecondTime, startTime, timeBuff, updateTime = 0L;

    Ball ball,hole;
    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;

    float[] accelerometerData=new float[9];
    float[] magnetometerData=new float[9];

    private static final float VALUE_DRIFT = 0.05f;

    private Display mDisplay;

    public SecondFragment(){

    }

    public static SecondFragment newInstance(String title){
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("Game",title);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_mainmenu,container,false);

        this.tv_waktu = view.findViewById(R.id.tv_score);
        this.ivCanvas = view.findViewById(R.id.iv_canvas);

        this.handler = new Handler();
        this.startTimer();

        mSensorManager = (SensorManager)this.getContext().getSystemService(SENSOR_SERVICE);

        this.mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        this.ivCanvas.post(new Runnable() {
            @Override
            public void run() {
                mBitmap = Bitmap.createBitmap(ivCanvas.getWidth(), ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
                ivCanvas.setImageBitmap(mBitmap);
                mCanvas = new Canvas(mBitmap);
                drawHole();
                drawBall();
            }
        });

        WindowManager wm = (WindowManager)this.getContext().getSystemService(WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();

        return view;
    }

    public void startTimer(){
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);

    }

    public Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;

            updateTime = timeBuff + millisecondTime;

            seconds = (int) (updateTime / 1000);

//            Minutes = Seconds / 60;

//            Seconds = Seconds % 60;

            milliSeconds = (int) (updateTime % 100);

            tv_waktu.setText("" + seconds + "."
                    + String.format("%02d", milliSeconds));

            handler.postDelayed(this, 0);
        }

    };


    @Override
    public void onResume(){
        super.onResume();
        if(mSensorAccelerometer!=null){
            mSensorManager.registerListener(this, mSensorAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
        if(mSensorMagnetometer!=null){
            mSensorManager.registerListener(this, mSensorMagnetometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void drawHole() {
        int colorbckg = ResourcesCompat.getColor(getResources(), R.color.black, null);
        Random randX =new Random();
        Random randY=new Random();
        int radius=75;
        int x=radius+randX.nextInt(this.ivCanvas.getWidth()-(2*radius));
        int y=radius+randY.nextInt(this.ivCanvas.getHeight()-(2*radius));
        hole=new Ball(x, y, radius, colorbckg);
        hole.draw(mCanvas, ivCanvas);
    }

    public void drawBall() {
        int colorbckg = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        Random randX =new Random();
        Random randY=new Random();
        int radius=75;
        int x=radius+randX.nextInt(ivCanvas.getWidth()-(2*radius));
        int y=radius+randY.nextInt(ivCanvas.getHeight()-(2*radius));
        ball=new Ball(x, y, radius, colorbckg);
        ball.draw(mCanvas, ivCanvas);
    }

    public void redrawBola(float roll, float pitch) {
        Paint paint = new Paint();
        float kecepatanX=ball.getKecepatanX(roll);
        float kecepatanY=ball.getKecepatanY(pitch);
        float x=ball.getX()+kecepatanX;
        float y=ball.getY()-kecepatanY;
        ball.setX(x);
        ball.setY(y);
        paint.setColor(ball.getPaint());
        this.mBitmap=Bitmap.createBitmap(ivCanvas.getWidth(), ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        this.ivCanvas.setImageBitmap(mBitmap);
        this.mCanvas=new Canvas(mBitmap);
        hole.draw(mCanvas, ivCanvas);
        this.mCanvas.drawCircle(ball.getX(), ball.getY(), ball.getRadius(), paint);
        this.ivCanvas.invalidate();
    }



    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType=sensorEvent.sensor.getType();

        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerData = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magnetometerData=sensorEvent.values.clone();
                break;
            default:
                return;
        }

        float[] rotationMatrix=new float[9];
        boolean rotationOK=SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerData, magnetometerData);

        float[] rotationMatrixAdjusted=new float[9];
        switch (mDisplay.getRotation()){
            case Surface.ROTATION_0:
                rotationMatrixAdjusted=rotationMatrix.clone();
                break;
            case Surface.ROTATION_90:
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_180:
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_MINUS_Y, rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_270:
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X, rotationMatrixAdjusted);
                break;
        }

        float[] orietationValues=new float[3];
        if(rotationOK){
            SensorManager.getOrientation(rotationMatrixAdjusted, orietationValues);
        }

        float pitch=orietationValues[1];
        float roll=orietationValues[2];

        if(Math.abs(pitch)<VALUE_DRIFT){
            pitch=0;
        }
        if (Math.abs(roll)<VALUE_DRIFT){
            roll=0;
        }


        if (ball.getX()+ball.getRadius()>=ivCanvas.getWidth()||ball.getX()-ball.getRadius()<=0){
            ball.resetKecX();
            roll=0;
        }
        else if (ball.getY()+ball.getRadius()>=ivCanvas.getHeight()||ball.getY()-ball.getRadius()<=0){
            ball.resetKecY();
            pitch=0;
        }
        else{
            pitch=pitch;
            roll=roll;
        }

        this.redrawBola(roll, pitch);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}

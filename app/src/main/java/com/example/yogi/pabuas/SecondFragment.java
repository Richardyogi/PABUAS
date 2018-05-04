package com.example.yogi.pabuas;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by Yogi on 03/05/2018.
 */

public class SecondFragment extends Fragment implements OnClickListener {
    private ImageView ivCanvas;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Button btnNewGame;
    private Button btnClose;
    private TextView tvWaktu;
    public SecondFragment(){

    }

    public static SecondFragment newInstance(String title){
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("Game",title);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_mainmenu,container,false);
        this.btnNewGame = view.findViewById(R.id.btn_new);
        this.btnClose = view.findViewById(R.id.btn_close);
        this.tvWaktu = view.findViewById(R.id.tv_waktu);
        this.ivCanvas = view.findViewById(R.id.iv_canvas);
        return view;
    }

    public void initiateCanvas(){
        this.mBitmap = Bitmap.createBitmap(ivCanvas.getWidth(), ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        this.mCanvas = new Canvas(mBitmap);
        this.ivCanvas.setImageBitmap(mBitmap);

    }


    @Override
    public void onClick(View view) {

    }

    private class Round{
        int x;
        int y;

        private Round(){
            Random rand =  new Random();
            this.x = rand.nextInt(900);
            this.y = 210+rand.nextInt(690);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void draw(){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(50);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            mCanvas.drawCircle(getX(),getY(),100, paint);
        }
    }
}

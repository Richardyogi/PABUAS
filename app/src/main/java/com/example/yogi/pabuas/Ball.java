package com.example.yogi.pabuas;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * Created by Yogi on 09/05/2018.
 */

public class Ball {
    private float x, y;
    private float kecepatanX, kecepatanY=0;
    private int radius, paint;

    public Ball(float x, float y, int radius, int paint){
        this.x=x;
        this.y=y;
        this.radius=radius;
        this.paint=paint;
    }

    public int getPaint() {
        return paint;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public int getRadius() {
        return radius;
    }

    public void setPaint(int paint) {
        this.paint = paint;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }


    public void draw(Canvas mCanvas, ImageView arenaBola){
        Paint paint=new Paint();
        paint.setColor(this.getPaint());
        mCanvas.drawCircle(this.getX(), this.getY(), this.getRadius(), paint);
        arenaBola.invalidate();
    }

    public float getKecepatanX(float roll) {
        this.kecepatanX=kecepatanX+(roll*0.1f);
        return kecepatanX;
    }

    public float getKecepatanY(float pitch) {
        this.kecepatanY=kecepatanY+(pitch*0.1f);
        return kecepatanY;
    }

    public void resetKecX( float width){
        this.x=width-radius;
        this.kecepatanX=-(kecepatanX/1.3f);
    }

    public void resetKecX2(){
        this.x=radius;
        this.kecepatanX=-(kecepatanX/1.3f);
    }

    public void resetKecY(float height){
        this.y=height-radius;
        this.kecepatanY=-(kecepatanY/1.3f);
    }

    public void resetKecY2(){
        this.y=radius;
        this.kecepatanY=-(kecepatanY/1.3f);
    }

    public boolean validator(Ball otherBall){
        if(this.x>otherBall.getX()-(radius/2)&&this.x<otherBall.getX()+(radius/2)&&this.y>otherBall.getY()-(radius/2)&&this.y<otherBall.getY()+(radius/2)){
            return true;
        }
        else {
            return false;
        }
    }
}

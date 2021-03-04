package com.tenz.tenzmusic.widget.snow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GsonUtil;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SnowView extends RelativeLayout {

    private final String SNOW = "❉";
    private float vX = 2.5f;//风向 >0 右边飘 <0 左边飘
    private float vY = 5f;//下落速度 <0雪花往上飘
    private int snowCount = 50;//雪花个数
    private List<Snow> snowBeanList = new ArrayList<>();
    private int screenWidth = DisplayUtil.getWindowWidth();
    private int screenHeight = DisplayUtil.getWindowHeight();
    private Paint paint = new Paint();
    private boolean isStart;
    private Timer timer;

    public SnowView(Context context) {
        this(context, null);
    }

    public SnowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint.setAntiAlias(true);
        initSnowBeanData();
    }

    private void initSnowBeanData() {
        for (int i=0; i<snowCount; i++){
            Snow snow = new Snow();
            snow.setX((float) (Math.random() * screenWidth));
            snow.setY((float) (Math.random() * screenHeight));
            snow.setSize((int) (Math.random() * 50) + 5);
            snowBeanList.add(snow);
        }
    }

    public void resume(){
        if(timer == null){
            start();
        }
        isStart = true;
    }

    public void pause(){
        isStart = false;
    }

    public void destroy(){
        isStart = false;
        if(snowBeanList != null){
            snowBeanList.clear();
        }
        invalidate();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    public void start(){
        if(timer == null){
            timer = new Timer();
        }
        isStart = true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!isStart){
                    return;
                }
                for (int i=0; i<snowBeanList.size(); i++){
                    snowBeanList.get(i).setX(snowBeanList.get(i).getX() + vX);
                    snowBeanList.get(i).setY(snowBeanList.get(i).getY() + vY);
                    if(snowBeanList.get(i).getX() < 0 || snowBeanList.get(i).getX() > screenWidth){
                        snowBeanList.get(i).setX((float) (Math.random() * screenWidth));
                        snowBeanList.get(i).setY((float) (Math.random() * screenHeight));
                    }
                    if(snowBeanList.get(i).getY() < 0 || snowBeanList.get(i).getY() > screenHeight){
                        snowBeanList.get(i).setY(0);
                    }
                }
                postInvalidate();
            }
        },0,15);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        for (int i=0; i<snowBeanList.size(); i++){
            Snow snow = snowBeanList.get(i);
            paint.setTextSize(snow.getSize());
            paint.setColor(snow.getColor());
            canvas.drawText(SNOW,snow.getX(),snow.getY(),paint);
        }
    }
}

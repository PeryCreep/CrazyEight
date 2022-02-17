package com.example.PeryCreep;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.View;


import java.io.IOException;

public class GameView extends View {

    private MainHero hero;

    static float scaleStatic;

    private Context ctx;
    private float scale;
    private int scrW;
    private int scrH;
    private int touchX;
    private int touchy;
    private int i = 0;
    private int j = 0;
    private int ir = 0;
    private int jr = 0;
    private int il = 0;
    private int jl = 0;
    private Bitmap[] frames = new Bitmap[9];
    private Bitmap[] framesRunRight = new Bitmap[6];
    private Bitmap[] framesRunLeft = new Bitmap[6];
    private Paint paint;


    public GameView(Context context) {
        super(context);
        ctx = context;
        scale = ctx.getResources().getDisplayMetrics().density;
        scaleStatic = scale;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(scale * 15);  
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (hero.getHeroView().equals("right")) {
            if(jr == 4){
                jr =0;
                ir++;
            }
            if(ir == 6){
                ir = 0;
            }
            canvas.drawBitmap(framesRunRight[ir], hero.getX(), hero.getY(), null);
            jr++;
        } else if (hero.getHeroView().equals("left")) {
            if(jl == 4){
                jl =0;
                il++;
            }
            if(il == 6){
                il = 0;
            }
            canvas.drawBitmap(framesRunLeft[il], hero.getX(), hero.getY(), null);
            jl++;
        }

        hero.move(touchX * hero.getSpeed(), touchy * hero.getSpeed());
        if(hero.getHeroView().equals("stay")) {
            if (j == 4) {
                j = 0;
                i++;
            }
            if (i == 9) {
                i = 0;
            }
            canvas.drawBitmap(frames[i], hero.getX(), hero.getY(), null);
            j++;
        }
        invalidate();
        setToFullScreen();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        scrW = w;
        scrH = h;
        hero = new MainHero(BitmapFactory.decodeResource(getResources(), R.drawable.idleall), BitmapFactory.decodeResource(getResources(), R.drawable.runright), BitmapFactory.decodeResource(getResources(), R.drawable.runleft));


        framesRunRight[0] = Bitmap.createBitmap(hero.getBitmapRunRight(),0, 0, (int)(35* scale) , (int)(41* scale) );
        for(int i = 1; i < 6; i++){
            framesRunRight[i] = Bitmap.createBitmap(hero.getBitmapRunRight(),(int)(135*i * scale), 0, (int)(35* scale) , (int)(41* scale) );
        }

        framesRunLeft[5] = Bitmap.createBitmap(hero.getBitmapRunLeft(),0, 0, (int)(35* scale) , (int)(41* scale) );
        for(int i = 4; i >= 0; i--){
            int j = 1;
            framesRunLeft[i] = Bitmap.createBitmap(hero.getBitmapRunLeft(),(int)(135*j * scale), 0, (int)(35* scale) , (int)(41* scale));
            j++;
        }



        frames[0] = Bitmap.createBitmap(hero.getBitmapAll(), 0, 0, (int)(29* scale) , (int)(41* scale) );
        for(int i =1; i < 9; i++ ){
            frames[i] = Bitmap.createBitmap(hero.getBitmapAll(), (int)(135*i * scale ), 0, (int)(29* scale) , (int)(41* scale) );
        }

        hero.setX((((scrW / 2)) / hero.getSpeed()) * hero.getSpeed());
        hero.setY((((scrH / 2)) / hero.getSpeed()) * hero.getSpeed());

        AssetManager manager = ctx.getAssets();
        MediaPlayer player = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = manager.openFd("hotline.mp3");
            player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            player.prepare();
            player.start();

        } catch (Exception e) {
        }


    }


    public boolean onTouchEvent(MotionEvent evt) {
        int eventaction = evt.getAction();
        touchX = (((int) evt.getX()) / hero.getSpeed()) - 10;
        touchy = (((int) evt.getY()) / hero.getSpeed()) - 10;

        switch (eventaction) {

            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        invalidate();
        return true;
    }

    private void setToFullScreen() {
        setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


}
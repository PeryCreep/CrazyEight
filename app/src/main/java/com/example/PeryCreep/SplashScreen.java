package com.example.PeryCreep;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.view.MotionEvent;
import android.view.View;


public class SplashScreen extends View {

    private Bitmap title;
    private int scrW;
    private int scrH;

    private Bitmap playBtnUp;
    private Bitmap playBtnDown;
    private boolean playBtnPressed;

    private Context ctx;

    public SplashScreen(Context context) {
        super(context);
        ctx = context;
        title = BitmapFactory.decodeResource(getResources(), R.drawable.download);
        playBtnDown = BitmapFactory.decodeResource(getResources(), R.drawable.btn_down);
        playBtnUp = BitmapFactory.decodeResource(getResources(), R.drawable.btn_up);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        scrW = w;
        scrH = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int titleGLeftPos = (scrW - title.getWidth()) / 2;
        canvas.drawBitmap(title, titleGLeftPos, 10, null);

        int playBtnLeftPos = (scrW - playBtnUp.getWidth()) / 2;

        if (playBtnPressed) {
            canvas.drawBitmap(playBtnDown, playBtnLeftPos, (int) (scrH * 0.5), null);
        } else {
            canvas.drawBitmap(playBtnUp, playBtnLeftPos, (int) (scrH * 0.5), null);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        int evtAction = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch (evtAction) {
            case MotionEvent.ACTION_DOWN:
                int btnLeft = (scrW - playBtnUp.getWidth()) / 2;
                int btnRight = btnLeft + playBtnUp.getWidth();
                int btnTop = (int) (scrH * 0.5);
                int btnBottom = btnTop + playBtnUp.getHeight();

                boolean withinBtnBounds = X > btnLeft && X < btnRight && Y > btnTop && Y < btnBottom;
                if (withinBtnBounds) {
                    playBtnPressed = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (playBtnPressed) {

                    Intent gameIntent = new Intent(ctx, MainGameActivity.class);
                    ctx.startActivity(gameIntent);


                }
                playBtnPressed = false;
                break;
        }
        invalidate();
        return true;
    }
}

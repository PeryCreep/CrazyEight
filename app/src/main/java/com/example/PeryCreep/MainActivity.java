package com.example.PeryCreep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SplashScreen splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splash = new SplashScreen(this);
        splash.setKeepScreenOn(true);
        splash.setBackgroundColor(Color.BLACK);
        setContentView(splash);
    }

    private void setToFullScreen(){
        splash.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
        |View.SYSTEM_UI_FLAG_FULLSCREEN
        |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }
    @Override
    protected void onResume(){
        super.onResume();
        setToFullScreen();
    }


}
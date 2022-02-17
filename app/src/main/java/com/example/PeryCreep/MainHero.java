package com.example.PeryCreep;

import static com.example.PeryCreep.GameView.scaleStatic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class MainHero implements canMove {

    private Bitmap bitmap;
    private Bitmap bitmapAll;
    private int hp;
    private int exp;
    private int x;
    private int y;
    private int speed = 5;
    private String heroView = "right";
    private Bitmap bitmapRunRight;
    private Bitmap bitmapRunLeft;

    public MainHero(Bitmap bitmap1, Bitmap bitmapright, Bitmap bitleft) {
        bitmapAll = bitmap1;
        bitmapRunRight = bitmapright;
        bitmapRunLeft = bitleft;
    }

    public Bitmap getBitmapAll() {
        return bitmapAll;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getHp() {
        return hp;
    }

    public int getExp() {
        return exp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getHeroView() {
        return heroView;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void move(int curX, int curY) {

        if (x > curX ) {
            if (heroView.equals("right") || heroView.equals("stay")) {
                heroView = "left";
            }
            x -= speed;
        } else if (x < curX) {
            if(heroView.equals("left") || heroView.equals("stay")){
                heroView = "right";
            }
            x += speed;
        }
        if (y > curY ) {
            y -= speed;
        } else if (y < curY) {
            y += speed;
        }
        if(x == curX && y ==curY){
            heroView = "stay";
        }

        if(x == curX && (y > curY || y < curY)){
            heroView = "right";
        }

    }

    public Bitmap getBitmapRunRight() {
        return bitmapRunRight;
    }

    public Bitmap getBitmapRunLeft() {
        return bitmapRunLeft;
    }
}

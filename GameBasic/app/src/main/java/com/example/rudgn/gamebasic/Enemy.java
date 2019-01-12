package com.example.rudgn.gamebasic;

/**
 * Created by rudgn on 2019-01-05.
 */

public class Enemy {

    int x,y;
    int planetSpeed=15;
    Enemy(int x, int y){

        this.x = x; this.y = y;

    }

    public void move(){
        y+=planetSpeed;
    }

}
package com.example.csc.gamebasic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    Bitmap spaceship;
    int spaceship_x, spaceship_y;
    Bitmap leftKey, rightKey;
    int leftKey_x, leftKey_y;
    int rightKey_x, rightKey_y;
    int Width, Height;

    int button_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));    // 이때 this는 현재 activity를 의미한다.

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth();
        Height = display.getHeight();

        spaceship = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
        spaceship_x = Width*1/9;
        spaceship_y = Height*6/9;

         int x = Width/7;
         int y = Height/11;
         spaceship = Bitmap.createScaledBitmap(spaceship, x, y, true);

        leftKey = BitmapFactory.decodeResource(getResources(), R.drawable.leftkey);
        leftKey_x = Width*1/11;
        leftKey_y = Height*7/9;

        button_width = Width/6;


        leftKey = Bitmap.createScaledBitmap(leftKey, button_width, button_width, true);

        rightKey = BitmapFactory.decodeResource(getResources(), R.drawable.rightkey);
        rightKey_x = Width*7/9;
        rightKey_y = Height*7/9;

        rightKey = Bitmap.createScaledBitmap(rightKey, button_width, button_width, true);

    }

    class MyView extends View {


        MyView(Context context) {

            super(context);    //상위클래스의 생성자를 호출해야 한다.
            setBackgroundColor(0xFFB7F0B1);

             mHandler.sendEmptyMessageDelayed(0,1000);

        }

        @Override
        public void onDraw(Canvas canvas) {

            Paint p1 = new Paint();
            p1.setColor(Color.RED);
            p1.setTextSize(Width/22);


            canvas.drawText("SpaceShip", 0, 200, p1);

            canvas.drawBitmap(spaceship, spaceship_x, spaceship_y, p1);

            canvas.drawBitmap(leftKey, leftKey_x, leftKey_y, p1);

            canvas.drawBitmap(rightKey, rightKey_x, rightKey_y, p1);

            //   canvas.drawRect(10,10);


        }


        android.os.Handler mHandler = new android.os.Handler(){

            public void handleMessage(Message msg){
                invalidate();
                mHandler.sendEmptyMessageDelayed(0,30);  //1000 으로 하면 1초에 한번 실행된다.

            }



        };

        @Override
        public boolean onTouchEvent(MotionEvent event) {
           int x=0,y=0;

            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                x = (int) event.getX();
                y = (int) event.getY();  invalidate();
            }


            if((x>leftKey_x) && (x<leftKey_x+button_width)  && (y>leftKey_y) && (x<leftKey_y+button_width))
                spaceship_x-=20;


            if((x>rightKey_x) && (x<rightKey_x+button_width)  && (y>rightKey_y) && (x<rightKey_y+button_width))
                spaceship_x+=20;

            invalidate();
            return true;  //
        }
    }

}

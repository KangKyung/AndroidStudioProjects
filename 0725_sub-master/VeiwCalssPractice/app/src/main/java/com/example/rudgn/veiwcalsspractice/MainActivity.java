package com.example.rudgn.veiwcalsspractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Paint p1 = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView myVeiw = new MyView(this);
        setContentView(R.layout.activity_main);
    }

    public class MyView extends View {

        public MyView(Context context) {
            super(context);
            p1.setColor(Color.RED);
            p1.setTextSize(40);
        }
        public void onDraw(Canvas canvas) {
            canvas.drawCircle(100, 100, 100, p1);
            canvas.drawRect(0, 300, 300, 400, p1);
            canvas.drawText("View 클래스 상속받고 onDraw 활용하기", 0, 500, p1);
        }
    }
}
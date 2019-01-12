package com.example.rudgn.gamebasic;

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

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Bitmap spaceship;   //  우주선 이미지
    int spaceship_x, spaceship_y;   //  우주선 위치
    int spaceshipWidth;                  //우주선 가로 크기
    Bitmap leftKey, rightKey;   //  왼쪽 키 버튼, 오른쪽 키 버튼 이미지
    int leftKey_x, rightKey_x;  //  키 위치
    int leftKey_y, rightKey_y;  //  키 위치
    int Width, Height;  //  사용자 기기 해상도
    int button_width;   //  왼쪽 키 버튼 의 크기
    Bitmap screen;  //  배경 이미지

    int score;
    Bitmap missileButton;
    int missileButton_x, missileButton_y;
    int missileWidth;
    int missile_middle; //미사일 크기 반
    Bitmap missile;
    Bitmap enemyimg;

    int count;
    ArrayList<MyMissile> myM;
    ArrayList<Enemy> enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  이때의 this는 현재 액티비티를 의미함!
        setContentView(new MyView(this));
        /* setContentView(new MyView(this)); 이거는
        MyView m = new MyView(this);
        setContentView(m); 요 두줄의 축약형 !! */

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth(); //  사용 중인 기기의 가로 크기를 구하여 Width 필드에 대입
        Height = display.getHeight();   //  사용 중인 기기의 세로 크기를 구하여 Height 필드에 대입

        myM = new ArrayList<MyMissile>();
        enemy= new ArrayList<Enemy>();

        spaceship = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship); //  우주선 이미지 넣기
        int x = Width/7;
        int y = Height/11;    //  단말기의 해상도가 다르더라도 우주선 이미지는 같은 비율로 화면에 표시 가능!
        spaceship = Bitmap.createScaledBitmap(spaceship, x, y, true);

        spaceshipWidth=spaceship.getWidth();
        //Bitmap클래스의 getWidth메소드를 활용해서 그림크기를 구할 수 있다.
        spaceship_x = Width*1/9;
        spaceship_y = Height*6/9;

        leftKey = BitmapFactory.decodeResource(getResources(), R.drawable.leftkey);
        leftKey_x = Width*5/9;
        leftKey_y = Height*7/9;
        button_width = Width/6;

        leftKey = Bitmap.createScaledBitmap(leftKey, button_width, button_width, true);

        rightKey = BitmapFactory.decodeResource(getResources(), R.drawable.rightkey);
        rightKey_x = Width*7/9;
        rightKey_y = Height*7/9;

        rightKey = Bitmap.createScaledBitmap(rightKey, button_width, button_width, true);

        missileButton = BitmapFactory.decodeResource(getResources(), R.drawable.missilebutton);
        missileButton = Bitmap.createScaledBitmap(missileButton, button_width, button_width, true);
        missileButton_x = Width*1/11;
        missileButton_y = Height*7/9;

        missile = BitmapFactory.decodeResource(getResources(), R.drawable.missile);
        missile = Bitmap.createScaledBitmap(missile, button_width / 4, button_width / 4, true);
        missileWidth=missile.getWidth();

        enemyimg = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
        enemyimg = Bitmap.createScaledBitmap(enemyimg, button_width, button_width, true);

        screen = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        screen = Bitmap.createScaledBitmap(screen, Width, Height, true);
    }

    class MyView extends View {

        public MyView(Context context) {
            super(context); //  상위 클래스의 생성자 호출!!
            setBackgroundColor(Color.BLUE);

            mHandler.sendEmptyMessageDelayed(0,1000);
        }

        @Override
        synchronized  public void onDraw(Canvas canvas) {


            Random r1 = new Random();
            int x = r1.nextInt(Width);

            if(enemy.size()<5)
                enemy.add(new Enemy(x, -100));

            Paint p1 = new Paint(); //  paint - 이미지와 글자를 표현할 때 색상, 선의 스타일, 글자 크기 등 다양한 효과 제공 가능!
            p1.setColor(Color.RED);
            p1.setTextSize(50);
            canvas.drawBitmap(screen, 0, 0, p1);    //게임 배경

            canvas.drawText(Integer.toString(count), 0, 300, p1);

            canvas.drawText("점수 : "+Integer.toString(score), 0, 200, p1);

            canvas.drawBitmap(spaceship, spaceship_x, spaceship_y, p1);

            canvas.drawBitmap(leftKey, leftKey_x, leftKey_y, p1);   //  drawBitmap - 이미지를 화면에 표시

            canvas.drawBitmap(rightKey, rightKey_x, rightKey_y, p1);

            canvas.drawBitmap(missileButton, missileButton_x, missileButton_y, p1);

            for(MyMissile tmp : myM )
                canvas.drawBitmap(missile,tmp.x , tmp.y, p1);

            for(Enemy tmp : enemy )
                canvas.drawBitmap(enemyimg, tmp.x, tmp.y, p1);

            moveMissile();
            movePlanet();

            checkCollision();
            count++;
        }


        public void moveMissile() {

            for (int i = myM.size() - 1; i >= 0; i--) {
                myM.get(i).move();
            }

            for (int i = myM.size() - 1; i >= 0; i--) {      //미사일이 화면을 벗어나게 되면 없애도록 한다.
                if (myM.get(i).y < 0) myM.remove(i);
            }
        }


        public void movePlanet(){

            for(int i = enemy.size()-1;i>=0;i--){
                enemy.get(i).move();
            }

            for(int i = enemy.size()-1;i>=0;i--){      //미사일이 화면을 벗어나게 되면 없애도록 한다.
                if(enemy.get(i).y>Height)   enemy.remove(i);

            }
        }

        public void checkCollision() {

            for (int i = enemy.size() - 1; i >= 0; i--) {
                for (int j = myM.size() - 1; j >= 0; j--) {
                    if (myM.get(j).x+missile_middle > enemy.get(i).x  && myM.get(j).x +missile_middle< enemy.get(i).x+button_width && myM.get(j).y > enemy.get(i).y &&
                            myM.get(j).y < enemy.get(i).y+button_width ) {
                        enemy.remove(i);
                        myM.get(j).y=-30;
                        score+=10;
                    }
                }

            }
        }

        android.os.Handler mHandler = new android.os.Handler(){

            public void handleMessage(Message msg){
                invalidate();
                mHandler.sendEmptyMessageDelayed(0,30);  //1000 으로 하면 1초에 한번 실행된다.

            }



        };

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int x=0, y=0;

            if(event.getAction() == MotionEvent.ACTION_DOWN
                    || event.getAction() == MotionEvent.ACTION_HOVER_MOVE) {
                x = (int) event.getX();
                y = (int) event.getY(); //invalidate();
            }

            //  왼쪽 키 버튼을 터치할 경우
            if((x>leftKey_x) && (x<leftKey_x+button_width)
                    && (y>leftKey_y) && (y<leftKey_y+button_width))
                spaceship_x-=110;

            //  오른 키 버튼을 터치할 경우
            if((x>rightKey_x) && (x<rightKey_x+button_width)
                    && (y>rightKey_y) && (y<rightKey_y+button_width))
                spaceship_x+=110;

            if (event.getAction() == MotionEvent.ACTION_DOWN)
                if((x>missileButton_x) && (x<missileButton_x+button_width)  && (y>missileButton_y) && (x<missileButton_y+button_width))

                    if(myM.size()<1 ){
                        myM.add(new MyMissile(spaceship_x + spaceshipWidth / 2 - missileWidth / 2, spaceship_y));
                    }

            //  invalidate();   //  onDraw 메소드 호출
            return true;    //  true값을 반환하면 제대로 처리되었다는 뜻!
        }
    }
}

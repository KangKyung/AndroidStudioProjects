package com.example.rudgn.practical_exercise;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
class HelloAppActivity extends Activity
        implements RadioGroup.OnCheckedChangeListener
{
    /** Called when the activity is first created. */

    RadioGroup orientation;
    ImageView img;
    Drawable defaultImg;
    Drawable blueImg;
    Drawable redImg;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.imageView1);

        // 이 부분을 몰라서 엄청 헤맷네욤
        defaultImg = getResources().getDrawable(R.drawable.spring);
        blueImg = getResources().getDrawable(R.drawable.spring);
        redImg = getResources().getDrawable(R.drawable.spring);

        orientation = (RadioGroup)findViewById(R.id.radioGroup1);
        orientation.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        if(group == orientation)
        {
            switch(checkedId)
            {
                case R.id.radio0:
                    img.setImageDrawable(defaultImg);
                    break;
                case R.id.radio1:
                    img.setImageDrawable(blueImg);
                    break;
                case R.id.radio2:
                    img.setImageDrawable(redImg);
                    break;
            }
        }
    }
}









































// 다양한 터치 이벤트 인터페이스 구현 방법

// 익명의 내부 클래스 구현

/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);

        // 버튼에 익명의 내부 클래스인 터치 이벤트 리스너를 생성 및 설정한다.
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("superdroid", "Button View  onTouch()>>" + event.getAction());
                return false;
            }
        });
    }
}

*/






////////////////////////////////////////////////////////////////////////////////////////////////////

/* View.OnTouchListener를 액티비티가 상속받아 구현하는 방법
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

// 터치 인터페이스를 액티비티가 View.OnTouchListener를 상속받아 구현한다.
public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    // View.OnTouchListener 클래스의 onTouch 함수를 구현한다.
    // onTouch 함수를 통해 리스너가 설정된 뷰의 각종 터치 이벤트가 전달된다.
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("superoid", "Button View onTouch()>>" + event.getAction());
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 레이아웃을 콘텐트 영역에 설정한다.
        setContentView(R.layout.activity_main);

        // 레이아웃에 포함된 버튼 객체를 참조한다.
        Button button = (Button)findViewById(R.id.button);

        // 버튼에 터치 이벤트 리스너 객체를 생성 및 설정한다.
        button.setOnTouchListener(this);
    }
}*/

////////////////////////////////////////////////////////////////////////////////////////////////////

/* 내부 클래스 구현 방법
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 레이아웃을 콘텐트 영역에 설정한다.
        setContentView(R.layout.activity_main);

        // 레이아웃에 포함된 버튼 객체를 참조한다.
        Button button = (Button)findViewById(R.id.button);

        // 버튼에 터치 이벤트 리스너 객체를 생성 및 설정한다.
        button.setOnTouchListener(new MyTouchListener());
    }

    class MyTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.d("superoid", "Button View onTouch()>>" + event.getAction());

            // 이벤트를 소비하진 않도록 한다.
            return false;
        }
    }
} */

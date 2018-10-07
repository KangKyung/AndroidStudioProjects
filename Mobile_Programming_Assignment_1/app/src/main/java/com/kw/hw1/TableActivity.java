package com.kw.hw1;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class TableActivity extends Activity
        implements RadioGroup.OnCheckedChangeListener
{
    RadioGroup orientation;
    ImageView sky, season;
    ToggleButton tb;
    Drawable spring, summer, fall, winter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        sky = findViewById(R.id.imageView);
        tb = findViewById(R.id.toggleButton);

        sky.setVisibility(View.INVISIBLE); // 초기 화면에는 이미지출력 안됨

        //토글버튼 클릭
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sky.setVisibility(View.VISIBLE);
                if (isChecked == true){
                    //이미지 교체
                    sky.setImageResource(R.drawable.sun);
                } else {
                    //이미지 교체
                    sky.setImageResource(R.drawable.moon);
                }
            }
        });

        season = findViewById(R.id.imageView2);

        spring = getResources().getDrawable(R.drawable.spring);
        summer = getResources().getDrawable(R.drawable.summer);
        fall = getResources().getDrawable(R.drawable.fall);
        winter = getResources().getDrawable(R.drawable.winter);

        orientation = findViewById(R.id.radioGroup);
        orientation.setOnCheckedChangeListener(this);

        season.setVisibility(View.INVISIBLE); // 초기 화면에는 이미지출력 안됨
    }

    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        if(group == orientation)
        {
            season.setVisibility(View.VISIBLE);

            switch(checkedId)
            {
                case R.id.spring:
                    season.setImageDrawable(spring);
                    break;
                case R.id.summer:
                    season.setImageDrawable(summer);
                    break;
                case R.id.fall:
                    season.setImageDrawable(fall);
                    break;
                case R.id.winter:
                    season.setImageDrawable(winter);
                    break;
            }
        }
    }
}
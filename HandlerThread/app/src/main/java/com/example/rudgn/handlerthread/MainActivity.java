package com.example.rudgn.handlerthread;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity
{
    Handler mHandler = null;

    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mHandler = new Handler();
    }

    public void onClick( View v )
    {
        // 1. 3초 동안 긴 작업을 수행하는 Runnable 객체를 생성한다.
        Runnable job = new Runnable()
        {
            @Override
            public void run()
            {
                SystemClock.sleep( 3000 );

                Toast.makeText( MainActivity.this,
                        "3초 작업 끝",
                        Toast.LENGTH_SHORT ).show();
            }};

        // 2. 핸들러를 이용하여 메인 스레드의 메시지 큐에 3초간 작업하는 Runnable 객체를 추가한다.
        mHandler.post( job );
    }
}


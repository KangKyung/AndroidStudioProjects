package com.example.rudgn.a181102;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    CountDownTimer mCountDownTimer = null;
    TextView        mCountDownView  = null;

    class TestCountDownTimer extends CountDownTimer
    {
        public TestCountDownTimer( long millisInFuture, long countDownInterval )
        {
            super( millisInFuture, countDownInterval );
        }
        @Override
        public void onTick( long millisUntilFinished )
        {
            // 매번 틱마다 남은 초를 출력한다.
            mCountDownView.setText( millisUntilFinished/1000 + " 초" );
        }

        @Override
        public void onFinish()
        {
            // 카운트다운이 완료된 경우 카운트다운의 최종 초를 출력한다.
            mCountDownView.setText( "0 초");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountDownView = (TextView)findViewById(R.id.countdown_text);
        // 총 60초 동안 1초씩 카운트다운하도록 객체를 생성한다.
        mCountDownTimer = new TestCountDownTimer(60000, 1000);

        // 카운트다운 초깃값을 출력한다.
        mCountDownView.setText("60 초");
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_countdown_btn:
                // 총 60초 카운트다운을 시작한다.
                mCountDownTimer.start();
                break;
            case R.id.reset_countdown_btn:
                // 카운트다운을 중단하고 초를 리셋한다.
                mCountDownTimer.cancel();
                mCountDownView.setText("60 초");
                break;
        }
    }
}

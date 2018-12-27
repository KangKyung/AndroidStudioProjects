package com.example.rudgn.servicebind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    // MediaPlayer mMediaPlayer; // 음악 재생을 위한 객체
    int pos; // 재생 멈춘 시점
    private Button bStart;
    private Button bPause;
    private Button bRestart;
    private Button bStop;
    SeekBar sb; // 음악 재생위치를 나타내는 시크바
    boolean isPlaying = false; // 재생중인지 확인할 변수


    //액티비티에서 선언.
    private MediaService mService; //서비스 클래스

    //서비스 커넥션 선언.
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            MediaService.MediaServiceBinder binder = (MediaService.MediaServiceBinder) service;
            mService.registerCallback(mCallback); //콜백 등록
        }
    };

    //서비스에서 아래의 콜백 함수를 호출하며, 콜백 함수에서는 액티비티에서 처리할 내용 입력
    private MediaService.ICallback mCallback = new MediaService.ICallback() {
        public void recvData() {
        }
    };

    //서비스 시작.
    public void startServiceMethod() {
        Intent Service = new Intent(this, MediaService.class);
        bindService(Service, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceMethod();
        sb = (SeekBar) findViewById(R.id.seekBar1);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int ttt = seekBar.getProgress();
                mService.seekTo(ttt);
                mService.start();
                new MyThread().start();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                mService.pause();
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getMax() == progress) {
                    isPlaying = false;
                    mService.stop();
                }
            }
        });

        bStart = (Button) findViewById(R.id.button1);
        bPause = (Button) findViewById(R.id.button2);
        bRestart = (Button) findViewById(R.id.button3);
        bStop = (Button) findViewById(R.id.button4);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.start();
                int a = mService.getDuration();
                sb.setMax(a);
                new MyThread().start();
                isPlaying = true;

            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 음악 종료
                isPlaying = false; // 쓰레드 종료
                mService.stop(); // 멈춤
                mService.release(); // 자원 해제
                sb.setProgress(0); // 씨크바 초기화
            }
        });

        bPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 일시중지
                pos = mService.getCurrentPosition();
                mService.pause(); // 일시중지
                isPlaying = false; // 쓰레드 정지
            }
        });
        bRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 멈춘 지점부터 재시작
                mService.seekTo(pos); // 일시정지 시점으로 이동
                mService.start(); // 시작
                isPlaying = true; // 재생하도록 flag 변경
                new MyThread().start(); // 쓰레드 시작
            }
        });
    } // end of onCreate

    @Override
    protected void onPause() {
        super.onPause();
        isPlaying = false; // 프로그레스바 정지
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPlaying = true; // 프로그레스바 재시작
        new MyThread().start(); // 씨크바 그려줄 쓰레드 시작
    }
}
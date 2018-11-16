package com.example.rudgn.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MainActivity extends Activity {
    BroadcastReceiver smsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //  수신 되었을 때 호출되는 콜백 메소드
            //  매개변수 intent의 액션에 방송의 '종류'가 들어있고 필드에는 '추가정보' 가 들어 있음

            //  SMS 메시지 파싱
            Bundle bundle = intent.getExtras();
            String str = "";    //  출력할 문자열 저장
            if (bundle != null) {   //  수신된 내용이 있으면 실제 메시지는 Object타입의 배열에 PDU형식으로 저장

                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] msgs
                        = new SmsMessage[pdus.length];
                for (int i = 0; i < msgs.length; i++) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    str += "SMS가 도착하였습니다. \n" + "<" + msgs[i].getMessageBody().toString() + ">";
                }
                Toast.makeText(context, str, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();  //  퍼미션 체크

        //  방송 수신을 위해 인텐트 필터를 생겅하고 액션을 등록
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);    //  동적 리시버 등록
    }

    @Override
    protected void onDestroy(){
        //  등록된 동적 리시버 해제
        unregisterReceiver(smsReceiver);
        super.onDestroy();
    }

    //  퍼미션 체크 코드
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            //  다시 보지 않기를 체크 한 경우, 아래 대화창은 뜨지 않고 바로 onRequestpermissionsResult()가 실행됨
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 1);
        } else {    //  허용되어 있다면,
            registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED")); //  동적 리시버 등록
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED")); //  동적 리시버 등록
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

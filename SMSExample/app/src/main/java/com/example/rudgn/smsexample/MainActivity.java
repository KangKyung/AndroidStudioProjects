package com.example.rudgn.smsexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //퍼미션 체크 및 추가 코드
    //실제 사용할 위치에서 checkPermission(); 호출
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_SMS)) {
                //다시 보지 않기 체크 하지 않음. 거절 이력 있음
            } else {
                //다시 보기 않기 체크 함, 거절 이력 있음
            }
            //다시 보지 않기 체크 한 경우 아래 대화창은 뜨지 않는다. 바로 onRequestpermissionsResult()가 실행된다.
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, 1);
        } else {//허용되어 있으면
            //refreshSmsList();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode)
        {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_SMS) ==  PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "읽기권한 허용됨", Toast.LENGTH_SHORT).show();
                        //refreshSmsList();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "읽기권한 거부 앱 종료", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}

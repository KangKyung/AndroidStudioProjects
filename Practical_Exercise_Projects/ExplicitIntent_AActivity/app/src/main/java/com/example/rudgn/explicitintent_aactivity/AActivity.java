package com.example.rudgn.explicitintent_aactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                //  단말기에 설치된 앱을 실행했을 때 가장 첫 번째로 실행되는 액티비티를 보여달라는 액션
                intent.setAction(Intent.ACTION_MAIN);

                //  안드로이드 기본 앱 중 지도맵 카테고리
                intent.addCategory(Intent.CATEGORY_APP_MAPS);

                //  지도 기능을 가지는 액티비티의 실행을 요청한다.
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                //  어떤 데이터를 보여달라는 액션
                intent.setAction(Intent.ACTION_VIEW);

                //  다음 위치의 데이터를 보여달라는 의미
                intent.setData(Uri.parse("http://m.naver.com"));

                //  액티비티의 실행을 요청
                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {
        //  추가된 컴포넌트에 대한 intent선언   ->  AndroidManifest에 추가시켜야 실행 가능
        Intent intent = new Intent(this, A2Activity.class);

        //  내부 액티비티 실행을 요청
        startActivity(intent);
    }
}

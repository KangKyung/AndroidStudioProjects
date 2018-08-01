package com.example.secha.a0725_sub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView train;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        train=(ImageView)findViewById(R.id.train);

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast tmsg=Toast.makeText(getApplicationContext(),"앱을 시작합니다.",Toast.LENGTH_SHORT);
                tmsg.show();;//토스트 메세지

                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);//액티비티 전환



            }
        });
    }
}

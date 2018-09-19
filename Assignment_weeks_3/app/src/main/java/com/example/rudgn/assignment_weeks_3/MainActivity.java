package com.example.rudgn.assignment_weeks_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.Input_Text);

        button.setOnClickListener(new View.OnClickListener() { // 익명 클래스로서 클릭 리스너 사용
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString(); // EditText로부터 문자열을 가져오는 객체 생성
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show(); // Tost로 가져온 문자열 출력
            }
        });
    }
}
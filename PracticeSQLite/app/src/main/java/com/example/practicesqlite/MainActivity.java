package com.example.practicesqlite;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ProgressHandler handler;
    String time;
    TextView etDate;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("(yyyy년 MM월 dd일 HH:mm:ss)");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "ChatRecord.db", null, 1);

        // 테이블에 있는 모든 데이터 출력
        final TextView result = (TextView) findViewById(R.id.result);
        etDate = (TextView) findViewById(R.id.date);
        final EditText etItem = (EditText) findViewById(R.id.item);
        final EditText etPrice = (EditText) findViewById(R.id.price);

        // 날짜는 현재 날짜로 고정
        handler = new ProgressHandler();
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        etDate.setText(simpleDateFormat.format(date));

        runTime();

        // DB에 데이터 추가
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String item = etItem.getText().toString();
                String price = etPrice.getText().toString();

                dbHelper.insert(date, item, price);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 삭제    ->  기록 전체 삭제하는 기능으로 다시 수정하자(여유롭다면..)
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString();

                dbHelper.delete(item);
                result.setText(dbHelper.getResult());
            }
        });

        // DB에 있는 데이터 조회    ->  키워드 검색? 기능으로 다시 수정하자(여유롭다면..)
        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbHelper.getResult());
            }
        });
    }

    public void runTime(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        time= simpleDateFormat.format(new Date(System.currentTimeMillis()));

                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);

                        Thread.sleep(1000);
                    }catch (InterruptedException ex) {}
                }
            }
        });
        thread.start();
    }

    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            etDate.setText(time);
        }
    }
}
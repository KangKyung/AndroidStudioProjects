package com.example.rudgn.hw6;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class InputActivity extends Activity {
    TextView questionText;
    EditText inputText;
    EditText resultText;

    private static String getRandomString() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        String chars[] =
                "Android Project,Computer Software,Hello World,Mobile Programming,KwangWoon University".split(",");
        buffer.append(chars[random.nextInt(chars.length)]);
        return buffer.toString();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        inputText = (EditText) findViewById(R.id.inputText);
        questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(getRandomString());
    }

    public void ButtonClick(View v) {
        switch (v.getId()) {
            case R.id.resultButton: {
                if (inputText.getText().toString() .equalsIgnoreCase(questionText.getText().toString())) {
                    Intent serviceIntent = new Intent(InputActivity.this, MyCounterService.class);
                    //stopService(serviceIntent);
                    StartActivity startActivity = new StartActivity();
                    unbindService(startActivity.connection);
                    startActivity.running = false;

                    Intent intent = new Intent(InputActivity.this, StartActivity.class);
                    startActivity(intent);
                    break;
                } else {
                    questionText.setTextColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "틀렸습니다!", 0).show();
                }
            }
        }
    }
}
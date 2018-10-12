package com.example.rudgn.a1810121;
//  파일 탐색기 실행 코드
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    final int FILE_SELECT_VIDEO_FILE = 0;
    final int FILE_SELECT_IMAGE_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.videoFileBtn:
                selectFile(FILE_SELECT_VIDEO_FILE);
                break;
            case R.id.imageFileBtn:
                selectFile(FILE_SELECT_IMAGE_FILE);
                break;
        }
    }

    void selectFile(int code) {
        //  1. 특정한 파일 또는 데이터를 선택할 때 사용하는 ACTION.
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        //  2. "열 수 있는"파일을 보여주거나 반환해 달라고 요청 할 때 사용. 즉 파일 탐색기를 파일을 열고 싶다는 의미를 전달함.
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        startActivityForResult(intent, code);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //  1. 파일 탐색기는 선택 파일의 Uri를 intent를 통해 전달한다.
            Uri uri = data.getData();
            String filename = uri.getPath();
            //  2. 출력 값은 파일 경로가 아니다. 출력값 : ex) /downloads/3245
            Log.d("superdroid", "file name : " + filename);
            //  3. 출력 값은 특이함. 출력값 : ex) content:/com.android.provider.download.documents/document3245
            Log.d("superdroid", "Uri string : " + uri.toString());

            if(requestCode == FILE_SELECT_VIDEO_FILE) {
                TextView videoText = (TextView) findViewById(R.id.videoFilePath);
            } else {
                TextView imageText = (TextView) findViewById(R.id.imageFilePath);
                imageText.setText(filename);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

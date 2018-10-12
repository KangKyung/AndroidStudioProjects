package com.example.rudgn.a181012;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rudgn on 2018-10-12.
 */

public class ActivityB extends Activity {
    private String mResultPicFileUrl = "/data/pics/a.png";

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        //  1. A액티비티로 부터 전달받은 인텐트를 참조한다.
        Intent intent = getIntent();
        //  2. 인텐트에 포함된 엑스트라에서 사진 폴더명을 추출한다.
        Bundle bundle = intent.getExtras();
        String picUrl = bundle.getString("PIC_URL");
        //  3. 텍스트뷰에"전달받은 사진 폴더 : " 와 불러올 폴더명을 출력한다.
        TextView loadPicUrlTextView = (TextView) findViewById(R.id.received_pic_folder_url);
        String picUrlStr = "전달받은 사진 폴더 : " + picUrl;
        loadPicUrlTextView.setText(picUrlStr);
    }

    public void onClick(View v) {
        //  액티비티 A로 보내기 위해, 인텐트를 생성 후에 사진 파일 경로를 인텐트 엑스트라를 담는다.
        Intent intent = new Intent();
        intent.putExtra("RESULT_PIC_FILE_URL", mResultPicFileUrl);

        //  전달할 인텐트를 설정하고 액티비티를 종료한다.
        setResult(RESULT_OK, intent);
        finish();
}
}

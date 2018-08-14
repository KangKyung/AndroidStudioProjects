package com.example.secha.a0725_sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity {

    Button sub_map,restaurant,con_fac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        sub_map=(Button)findViewById(R.id.sub_map);
        restaurant=(Button)findViewById(R.id.restaurant);
        con_fac=(Button)findViewById(R.id.con_fac);

        sub_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast tmsg=Toast.makeText(getApplicationContext(),"지하철 노선도",Toast.LENGTH_SHORT);
                tmsg.show();;//토스트 메세지

                Intent intent=new Intent(getApplicationContext(),SubwayLineActivity.class);
                startActivity(intent);//액티비티 전환
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast tmsg=Toast.makeText(getApplicationContext(),"맛 집",Toast.LENGTH_SHORT);
                tmsg.show();;//토스트 메세지

                Intent intent=new Intent(getApplicationContext(),FamousRestaurant.class);
                startActivity(intent);//액티비티 전환

            }
        });

        con_fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast tmsg=Toast.makeText(getApplicationContext(),"편의 시설",Toast.LENGTH_SHORT);
                tmsg.show();;//토스트 메세지

                Intent intent=new Intent(getApplicationContext(),ConvenientFacilities.class);
                startActivity(intent);//액티비티 전환

            }
        });


    }
}

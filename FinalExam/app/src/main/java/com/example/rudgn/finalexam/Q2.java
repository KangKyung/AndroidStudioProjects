package com.example.rudgn.finalexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by rudgn on 2018-12-07.
 */

public class Q2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_q2);
    }

    public void ButtonClick(View v) {
        switch ((v.getId())) {
            case R.id.imageView1: {
                Toast.makeText(this, "Cake(SweetCake)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView2: {
                Toast.makeText(this, "Coke(SparklingCoke)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView3: {
                Toast.makeText(this, "Doughnut(ChocoFlavor)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView4: {
                Toast.makeText(this, " Hamberger(ByMommy'sTouch)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView5: {
                Toast.makeText(this, "IceCream(IcecreamYouscreamgivemethegivemetheicecream)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView6: {
                Toast.makeText(this, "Mandoo(man~doo~mandoomandoo~man~doo~~?)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView7: {
                Toast.makeText(this, "Muffin(Costcomuffinisthebest!)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView8: {
                Toast.makeText(this, "Noodle(GigigoNoodleisSospicy!)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView9: {
                Toast.makeText(this, "Riceroll(KimbapInHeaven.RIP..)", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.imageView10: {
                Toast.makeText(this, "Sausage(MadeInGermany)", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}

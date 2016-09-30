package com.gihwan.challenge_one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ImageView iv1;
    ImageView iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScrollView sv1 = (ScrollView) findViewById(R.id.sv1);
        ScrollView sv2 = (ScrollView) findViewById(R.id.sv2);
        Button bt1 = (Button) findViewById(R.id.button1);
        Button bt2 = (Button) findViewById(R.id.button2);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);


        // Set scrollable
        sv1.setHorizontalScrollBarEnabled(true);
        sv2.setHorizontalScrollBarEnabled(true);
        sv1.setVerticalScrollBarEnabled(true);
        sv2.setVerticalScrollBarEnabled(true);


        iv1.setImageResource(R.drawable.pic1);
       // iv2.setImageDrawable(null);
        /*
        Resources res = getResources();
        BitmapDrawable bitmap = (BitmapDrawable)res.getDrawable(R.drawable.pic1);
        iv1.getLayoutParams().width = bitmap.getIntrinsicWidth();
        iv1.getLayoutParams().height = bitmap.getIntrinsicHeight();

        iv2.getLayoutParams().width = bitmap.getIntrinsicWidth();
        iv2.getLayoutParams().height = bitmap.getIntrinsicHeight();
        */

        bt1.setOnClickListener(new myOnClick());
        bt2.setOnClickListener(new myOnClick());
    }
    private class myOnClick implements View.OnClickListener {
        public void onClick(View v) {
            if (v.getId() == R.id.button1) {
                if (iv2.getDrawable() != null)
                    iv1.setImageDrawable(iv2.getDrawable());
                //Use appropriate color
                iv2.setImageDrawable(null);
            } else {
                if (iv1.getDrawable() != null)
                    iv2.setImageDrawable(iv1.getDrawable());
                //Use appropriate color
                iv1.setImageDrawable(null);
            }
        }
    }
}

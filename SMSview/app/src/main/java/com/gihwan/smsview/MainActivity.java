package com.gihwan.smsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1;
    TextView tv1, tv2;
    Button bt1, bt2;
    int etWidth;
    boolean init;
    MainActivity() {
        init = false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get widgets
        et1 = (EditText) findViewById(R.id.et1);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et1.getText().toString();
                if (str.length() != 0)
                    Toast.makeText(getApplicationContext(),
                            "You sent \n" + str, Toast.LENGTH_LONG).show();
                et1.setText(""); // clear
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // limit to 80 characters
        et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(80)});
        et1.addTextChangedListener(new myTextWatcher());
    }

    private class myTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            tv1.setText(s.length() + " / 80 바이트");
            tv2.setText(s.length() + " / 80 바이트");
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!init) {  // 가장 처음에만 세팅 하기
            etWidth = et1.getWidth(); // 전체 너비 구하기
            et1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100); // 한글 설정을 위한 테스트
            float korSize = et1.getPaint().measureText("한"); // px 단위의 한글 비율 구하기

            /* 한줄에 한글로 8글자가 들어가도록 텍스트 사이즈 조정 */
            et1.setTextSize(TypedValue.COMPLEX_UNIT_PX, etWidth / 8 / (korSize/100));
            init = true;
            super.onWindowFocusChanged(hasFocus);
        }
    }
}

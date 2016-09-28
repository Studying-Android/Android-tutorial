package com.gihwan.textviewedittext;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById (R.id.textView);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_main);

        // hide soft keyboard when user clicks outside of any of EditText
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =
                    (InputMethodManager) Main.super.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        Main.super.getCurrentFocus().getWindowToken(), 0);
            }
        });

        EditText et1 = (EditText) findViewById(R.id.editText1); // get EditText 1
        EditText et2 = (EditText) findViewById(R.id.editText2); // get EditText 2
        EditText et3 = (EditText) findViewById(R.id.editText3); // get EditText 3
        et3.setVisibility(View.VISIBLE);

        et1.setSelectAllOnFocus(true); // set select all on focus
        et2.setSelectAllOnFocus(false); // unset select all on focus
        et1.setAutoLinkMask(0xf); // 0x1 for web_urls, 0x2 for email, 0x4 for phone, 0x8 for map
        et2.setAutoLinkMask(15);

        // set on focus listener
        et1.setOnFocusChangeListener(new OnEditTextFocusChangeListener());
        et2.setOnFocusChangeListener(new OnEditTextFocusChangeListener());

        // Set all inputs to be capitalized
        et1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

    }

    public class OnEditTextFocusChangeListener implements View.OnFocusChangeListener {

        public OnEditTextFocusChangeListener() { };
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText et = (EditText) v;
            int start, end;
            if (hasFocus == true) {
                start = et.getSelectionStart(); // get start offset of selected range
                end = et.getSelectionEnd();     // get end offset of selected range
                if (v.getId() == R.id.editText1) {
                    Toast.makeText(getApplicationContext(),
                            "First EditText was focused",
                            Toast.LENGTH_SHORT).show();
                    // change text
                    et.setText("You select text from " + start + " to " + end);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Second EditText was focused",
                            Toast.LENGTH_SHORT).show();
                    et.setText("You select second text edit from" + start + " to " + end);
                }
            }
        }
    }
}

package com.gihwan.textviewedittext;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
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
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_main);

        // hide soft keyboard when user clicks outside of any of EditText
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout layout = (RelativeLayout)v;

                InputMethodManager inputMethodManager =
                    (InputMethodManager) Main.super.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        Main.super.getCurrentFocus().getWindowToken(), 0);

            }
        });

        /*
         * public int getSelectionStart();
         * public int getSelectionEnd();
         * public void setSelection(int start, int stop), (int index)
         * public void selectAll()
         * public void extendSelection(int index);
         *
         */

        EditText et1 = (EditText) findViewById(R.id.editText1); // get EditText 1
        EditText et2 = (EditText) findViewById(R.id.editText2); // get EditText 2
        final EditText et3 = (EditText) findViewById(R.id.editText3); // get EditText 3
        TextView tv1 = (TextView) findViewById(R.id.textView); // get TextView 1

        // Ellipsize means shorten the text for little textview
        // Default: End,
        // Property: start, middle, end
        tv1.setEllipsize(TextUtils.TruncateAt.MIDDLE); // Ellipsize a text
        tv1.setSingleLine();                        // show text in one line

        // EditText2 cannot exceed 15 chars.
        et2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15)});

        et3.setVisibility(View.VISIBLE); // show edit box
        et3. setLineSpacing(10,1); // add linspacing

        // control the text change
        et3.addTextChangedListener(new myTextWatcher()); // set TextWatcher

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
    public class myTextWatcher implements TextWatcher {
        String bText, cText; // store before text
        int bStart, cStart;  // store idx
        boolean pin; // used to prevent infinite loop of afterTextChanged;
        TextView tv;

        myTextWatcher() {
            pin = false;
            tv = (TextView)findViewById(R.id.textView);
        } // initialize

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            EditText et3 = (EditText) findViewById(R.id.editText3); // get EditText 3
            bText = s.toString();
            bStart = start;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*
                Toast.makeText(getApplicationContext(),
                        "Text has changed from " + bText + "(" + bStart + ", " +
                                (bStart+before) + ") to " + s +
                                "(" + start + ", " + (start + count) + ")",
                        Toast.LENGTH_SHORT).show();
                        */
            cStart = start + count;
            cText = s.subSequence(start, start + count).toString();
            if (count < before)
                pin = true;
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!pin) {
                pin = true;
                s.insert(cStart, cText); // double the input text
            }
            pin = false;
            tv.setText(s.length() + " characters");
        }
    }
    // Define custom OnFocusChangeListener
    public class OnEditTextFocusChangeListener implements View.OnFocusChangeListener {

        void OnEditTextFocusChangeListener() { };
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText et = (EditText) v;
            int start, end;
            if (hasFocus) {
                start = et.getSelectionStart(); // get start offset of selected range
                end = et.getSelectionEnd();     // get end offset of selected range
                if (v.getId() == R.id.editText1) {
                    Toast.makeText(getApplicationContext(),
                            "First EditText was focused",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Second EditText was focused",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

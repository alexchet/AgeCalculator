package com.razornovus.agecalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //UI References
    private EditText editToday;
    private EditText editBirthday;

    private DatePickerDialog editTodayPickerDialog;

    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setClickEventListeners();
    }

    private void setClickEventListeners() {
        editToday = (EditText) findViewById(R.id.edit_today);
        editToday.setInputType(InputType.TYPE_NULL);
        editToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate toDate = new setDate(editToday);
            }
        });

        editBirthday = (EditText) findViewById(R.id.edit_birthday);
        editBirthday.setInputType(InputType.TYPE_NULL);
        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate fromDate = new setDate(editBirthday);
            }
        });
    }

    public void onClick(View v) {
        setDate fromDate = new setDate(editToday);
    }
}

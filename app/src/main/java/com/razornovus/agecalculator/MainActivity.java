package com.razornovus.agecalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //UI References
    private EditText editToday;
    private EditText editBirthday;
    private Button  buttonCalculate;

    private DatePickerDialog editTodayPickerDialog;

    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_layout).requestFocus();

        setEditEventListeners();

        //set current date as default for editToday
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        editToday.setText(df.format(c.getTime()));
    }

    private void setEditEventListeners() {
        editToday = (EditText) findViewById(R.id.edit_today);
        editToday.setInputType(InputType.TYPE_NULL);
        setDate toDate = new setDate(editToday);

        editBirthday = (EditText) findViewById(R.id.edit_birthday);
        editBirthday.setInputType(InputType.TYPE_NULL);
        setDate fromDate = new setDate(editBirthday);
    }

    public void setCalculateEventListener() {
        buttonCalculate = (Button) findViewById(R.id.button_calculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
}

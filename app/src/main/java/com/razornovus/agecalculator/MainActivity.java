package com.razornovus.agecalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //UI References
    private EditText editToday;
    private EditText editBirthday;
    private TextView infoYear;
    private TextView infoMonth;
    private TextView infoDay;
    private Button  buttonCalculate;
    private SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");

    private DatePickerDialog editTodayPickerDialog;

    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_layout).requestFocus();

        setEditEventListeners();
        setCalculateEventListener();

        //set current date as default for editToday
        Calendar c = Calendar.getInstance();
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
                try {
                    Date fromDate = df.parse(editBirthday.getText().toString());
                    Date toDate = df.parse(editToday.getText().toString());
                    long milliseconds = getDiffMilliseconds(fromDate, toDate);
                    Calendar c = Calendar.getInstance();

                    c.setTimeInMillis(milliseconds);
                    int mYear = c.get(Calendar.YEAR)-1970;
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH)-1;

                    infoYear = (TextView) findViewById(R.id.info_age_year);
                    infoYear.setText(Integer.toString(mYear));

                    infoMonth = (TextView) findViewById(R.id.info_age_month);
                    infoMonth.setText(Integer.toString(mMonth));

                    infoDay = (TextView) findViewById(R.id.info_age_day);
                    infoDay.setText(Integer.toString(mDay));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static long getDiffMilliseconds(Date fromDate, Date toDate) {
        long diff =  toDate.getTime() - fromDate.getTime();
        return diff;
    }
}

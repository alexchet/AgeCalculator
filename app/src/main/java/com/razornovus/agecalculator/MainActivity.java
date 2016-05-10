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

                    TextView years = (TextView) findViewById(R.id.info_age_year);
                    years.setText(Integer.toString(getDiffYears(fromDate, toDate)));

                    TextView month = (TextView) findViewById(R.id.info_age_month);
                    month.setText(Integer.toString(getDiffMonth(fromDate, toDate)));

                    TextView day = (TextView) findViewById(R.id.info_age_day);
                    day.setText(Integer.toString(getDiffDay(fromDate, toDate)));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static int getDiffMonth(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.MONTH) - a.get(Calendar.MONTH);
        if (a.get(Calendar.DATE) > b.get(Calendar.DATE)) {
            diff--;
        }
        return diff;
    }

    public static int getDiffDay(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.DATE) - a.get(Calendar.DATE);

        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}

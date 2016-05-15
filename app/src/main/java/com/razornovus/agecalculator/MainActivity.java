package com.razornovus.agecalculator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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
    private TextView infoNextBirthdayMonth;
    private TextView infoNextBirthdayDay;
    private Button  buttonCalculate;
    private SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");

    private static final int RESULT_SETTINGS = 1;

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

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
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


                    long millisecondsNext = getDiffMilliseconds(toDate, fromDate);
                    c.setTimeInMillis(millisecondsNext);
                    int mNextMonth = c.get(Calendar.MONTH);
                    int mNextDay = c.get(Calendar.DAY_OF_MONTH)-1;

                    infoNextBirthdayMonth = (TextView) findViewById(R.id.info_next_birthday_month);
                    infoNextBirthdayMonth.setText(Integer.toString(mNextMonth));

                    infoNextBirthdayDay = (TextView) findViewById(R.id.info_next_birthday_day);
                    infoNextBirthdayDay.setText(Integer.toString(mNextDay));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;

        }

        return true;
    }

    public static long getDiffMilliseconds(Date fromDate, Date toDate) {
        long diff =  toDate.getTime() - fromDate.getTime();
        return diff;
    }
}

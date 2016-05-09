package com.razornovus.agecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editToday = (EditText) findViewById(R.id.edit_today);
        editToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate fromDate = new setDate(editToday, v.getContext());
            }
        });
    }
}

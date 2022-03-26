package com.example.jamn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private TextInputEditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //createDatePicker();
        //dateButton.findViewById(R.id.calendar);
        //dateText.findViewById(R.id.dateText);
        //dateText.setText(getSelectedDate());
    }

    /*private String getSelectedDate() {
        Calendar cal = Calendar.getInstance();
        return convertMonthToString(cal.get(Calendar.DAY_OF_MONTH)) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH);
    }

    public void createDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = convertMonthToString(month) + " " + day + " " + year;
                dateText.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                this,
                AlertDialog.THEME_HOLO_LIGHT,
                dateSetListener,
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String convertMonthToString(int month) {
        if(month == 0) {
            return "JAN";
        } else if(month == 1) {
            return "FEB";
        } else if(month == 2) {
            return "MAR";
        } else if(month == 3) {
            return "APR";
        } else if(month == 4) {
            return "MAY";
        } else if(month == 5) {
            return "JUN";
        } else if(month == 6) {
            return "JUL";
        } else if(month == 7) {
            return "AUG";
        } else if(month == 8) {
            return "SEP";
        } else if(month == 9) {
            return "OCT";
        } else if(month == 10) {
            return "NOV";
        } else if(month == 11) {
            return "DEC";
        }

        return "NULL";

    }

    public void datePicker(View view) {

    }*/
}
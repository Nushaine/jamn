package com.example.jamn;

import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.*;

public class MainActivity4 extends AppCompatActivity  {

    private DatePickerDialog datePickerDialog;
    private TextView dateText;
    private TextInputEditText inputText;
    private ImageView veryHappy;
    private ImageView happy;
    private ImageView normal;
    private ImageView sad;
    private ImageView verySad;
    private HashMap<String, String[]> notes = new HashMap<String, String[]>();
    private String date;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        // initialize datapicker instance
        createDatePicker();

        // getting all required elements
        dateText = findViewById(R.id.dateText);
        inputText = findViewById(R.id.notesInput);
        veryHappy = findViewById(R.id.veryHappy);
        happy = findViewById(R.id.happy);
        normal = findViewById(R.id.normal);
        sad = findViewById(R.id.sad);
        verySad = findViewById(R.id.verySad);

        // initialize SharePreferences to variable sp
        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        // adding a function to listen for changes in focus, then update the HashMap
        inputText.setOnFocusChangeListener(new TextInputEditText.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // hide keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputText.getWindowToken(), 0);

                    // update hashmap at current key
                    String[] currentKey = notes.get(date);
                    String currentText = inputText.getText().toString();
                    currentKey[0] = currentText;
                    notes.put(date, currentKey);

                    // set Shared Preferences db
                    String inputTextStr = inputText.getText().toString();
                    SharedPreferences.Editor editor = sp.edit();

                    // update SharedPreferences db
                    editor.putString("notes", inputTextStr);
                    editor.commit();
                    Toast.makeText(MainActivity4.this, "Data Saved", Toast.LENGTH_LONG).show();
                }
            }
        });

        // set initial date
        date = getSelectedDate();
        dateText.setText(date);

        // add a key into notes when app open
        if(!(notes.containsKey(date))) {
            String[] empty = {"", ""};
            notes.put(date, empty);
        }

        // initializing SharePreferences from FocusListener focusing
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String note = sp.getString("notes", "");

        // store note written
        inputText.setText(note);
    }

    // returns whatever value was selected in datepicker
    private String getSelectedDate() {
        Calendar cal = Calendar.getInstance();
        return convertMonthToString(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.YEAR);
    }

    // initializes datePicker instance
    public void createDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // update date
                date = convertMonthToString(month) + " " + day + " " + year;
                dateText.setText(date);

                // create a new key in the hashmap if not already exists
                if(!(notes.containsKey(date))) {
                    String[] empty = {"", ""};
                    notes.put(date, empty);
                    inputText.setText("");
                    veryHappy.setAlpha(1f);
                    happy.setAlpha(1f);
                    normal.setAlpha(1f);
                    sad.setAlpha(1f);
                    verySad.setAlpha(1f);
                } else {
                    String[] dateContent = notes.get(date);
                    inputText.setText(dateContent[0]);
                    if(!dateContent[1].equals("")) {
                        int emoji = Integer.parseInt(dateContent[1]);
                        updateEmoji(emoji, false);
                    }
                }
            }
        };

        Calendar cal = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
                this,
                AlertDialog.THEME_HOLO_LIGHT,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    // formats month number into month name
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

    // lose focus from editText
    public void onClick(View view) {
        int clickedID = view.getId();
        switch (clickedID){
            case R.id.calendarContainer:
                inputText.clearFocus();
                break;
            case R.id.calendar:
                // update hashmap
                String[] currentKey = notes.get(date);
                String currentText = inputText.getText().toString();
                currentKey[0] = currentText;
                notes.put(date, currentKey);
                // show datepicker
                datePickerDialog.setTitle("");
                datePickerDialog.show();
                break;
            case R.id.veryHappy:
            case R.id.happy:
            case R.id.normal:
            case R.id.sad:
            case R.id.verySad:
                updateEmoji(clickedID, true);
                break;
            default:
                break;
        }
    }

    private void updateNotesEmoji(int id) {
        // update hashmap at current key
        String[] currentKey = notes.get(date);
        currentKey[1] = Integer.toString(id);
        notes.put(date, currentKey);
    }

    // updates emoji backgrounds based on clicked emoji
    private void updateEmoji(int clickedID, boolean updated) {
        if(!(clickedID == R.id.veryHappy)) {
            veryHappy.setAlpha(0.2f);
        } else {
            veryHappy.setAlpha(1f);
            if(updated) {
                updateNotesEmoji(R.id.veryHappy);
            }
        }
        if(!(clickedID == R.id.happy)) {
            happy.setAlpha(0.2f);
        } else {
            happy.setAlpha(1f);
            if(updated) {
                updateNotesEmoji(R.id.happy);
            }
        }
        if(!(clickedID == R.id.normal)) {
            normal.setAlpha(0.2f);
        } else {
            normal.setAlpha(1f);
            if(updated) {
                updateNotesEmoji(R.id.normal);
            }
        }
        if(!(clickedID == R.id.sad)) {
            sad.setAlpha(0.2f);
        } else {
            sad.setAlpha(1f);
            if(updated) {
                updateNotesEmoji(R.id.sad);
            }
        }
        if(!(clickedID == R.id.verySad)) {
            verySad.setAlpha(0.2f);
        } else {
            verySad.setAlpha(1f);
            if(updated) {
                updateNotesEmoji(R.id.verySad);
            }
        }
    }
}
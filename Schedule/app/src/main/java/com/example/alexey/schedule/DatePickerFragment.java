package com.example.alexey.schedule;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Alexey on 09.04.2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int year, month, day;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        Toast.makeText(getContext(), "Вы выбрали дату", Toast.LENGTH_LONG).show();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int myYear = calendar.get(calendar.YEAR);
        int myMonth = calendar.get(calendar.MONTH);
        int myDay = calendar.get(calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, myYear, myMonth, myDay);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}

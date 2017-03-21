package com.hp.expensemanager;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewTripActivity extends AppCompatActivity implements View.OnClickListener
{   EditText tripId,from,to,startDate,endDate,budget;
    static int  balance;
    SQLiteDatabase db;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
   // TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // tv= (TextView) findViewById(R.id.textView14);
        tripId = (EditText) findViewById(R.id.editText1);
        from = (EditText) findViewById(R.id.editText2);
        to = (EditText) findViewById(R.id.editText3);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        startDate = (EditText) findViewById(R.id.editText4);
        endDate = (EditText) findViewById(R.id.editText5);
        budget = (EditText) findViewById(R.id.editText6);
        startDate.setInputType(InputType.TYPE_NULL);
        startDate.requestFocus();
        endDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                startDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        db=openOrCreateDatabase("Trip", MODE_APPEND, null);
       db.execSQL("create table if not exists TRIP_DETAILS (TRIP_ID varchar primary key , FROM_PLACE varchar,TO_PLACE varchar,START_DATE varchar,END_DATE varchar,BUDGET integer,BALANCE integer) ");
         //db.execSQL("drop table EXPENSE_DETAILS");

    }

    public void saveTrip(View v)
    {
       // String s="insert into TRIP_DETAILS values('katrina','645')"
        ContentValues cv=new ContentValues();
        cv.put("TRIP_ID", tripId.getText().toString());
        cv.put("FROM_PLACE", from.getText().toString());
        cv.put("TO_PLACE", to.getText().toString());
        cv.put("START_DATE",startDate.getText().toString());
        cv.put("END_DATE", endDate.getText().toString());
        cv.put("BUDGET",Integer.valueOf(budget.getText().toString()));
       // balance=Integer.parseInt(String.valueOf(budget));
        cv.put("BALANCE",Integer.valueOf(budget.getText().toString()));
        db.insert("TRIP_DETAILS", null, cv);
        db.close();
        Toast.makeText(NewTripActivity.this, "Trip Saved", Toast.LENGTH_SHORT).show();
        finish();
       // tv.setText(tripId.getText().toString());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {

        if(view == startDate) {
            fromDatePickerDialog.show();
        } else if(view == endDate) {
            toDatePickerDialog.show();
        }

    }
}

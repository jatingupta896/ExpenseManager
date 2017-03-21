package com.hp.expensemanager;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ExpenseActivity extends AppCompatActivity implements View.OnClickListener { Spinner s1,s2;
    String categoryarr[]={"Traveling","Meal","Lodging","Miscellaneous"};
    String cat,tId;
    SQLiteDatabase db,db1;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    String id1[];
   EditText amount,date;
    //int mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // expenseId= (EditText) findViewById(R.id.editText1);
        amount= (EditText) findViewById(R.id.editText3);
      //  int p = Integer.parseInt(amount.getText().toString());
      // mon = Integer.valueOf(amount.getText().toString());
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        date = (EditText) findViewById(R.id.editText4);
        date.setInputType(InputType.TYPE_NULL);
        date.requestFocus();
        date.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        s1=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,categoryarr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = categoryarr[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cat = "";
            }
        });//end of Onitemselect
         db=openOrCreateDatabase("Trip",MODE_APPEND,null);
        db1=openOrCreateDatabase("Trip",MODE_APPEND,null);
        String q="select * from TRIP_DETAILS";
        Cursor c=db.rawQuery(q,null);
        String tripId,fromPlace,toPlace;
       ArrayList id=new ArrayList();

        while(c.moveToNext()) {

            tripId = c.getString(0);
            id.add(tripId);
        }
        id1 = new String[id.size()];
        id.toArray(id1);



        //ViewTrip2Activity m=new ViewTrip2Activity();
        s2=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,id1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter1);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tId = id1[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tId = "";
            }
        });//end of Onitemselect
       // db1.execSQL("create table if not exists EXPENSE_DETAILS (EXPENSE_ID integer primary key autoincrement , CATEGORY varchar,AMOUNT integer,EDATE varchar,TRIP_ID varchar) ");
        //db.execSQL("drop table EXPENSE_DETAILS");
        db.close();
        db=openOrCreateDatabase("Trip", MODE_APPEND, null);
        db.execSQL("create table if not exists EXPENSE_DETAILS (EXPENSE_ID integer primary key autoincrement,CATEGORY varchar, AMOUNT integer,EDATE varchar,TRIP_ID varchar) ");
         }

    public void saveExpense(View v)
    {db.execSQL("insert into EXPENSE_DETAILS(CATEGORY,AMOUNT,EDATE,TRIP_ID) values(' "+cat+" ',' "+Integer.parseInt(String.valueOf(amount.getText()))+" ',' "+date.getText().toString()+" ',' "+tId+" ')");
      int i=Integer.parseInt(String.valueOf(amount.getText()));
           //   db.execSQL("UPDATE TRIP_DETAILS SET BALANCE=BALANCE-"+i+" WHERE TRIP_ID IN" +
                   //   " (SELECT TRIP_ID FROM EXPENSE_DETAILS WHERE TRIP_ID = '" + tId + "')");
      //  date.setText(""+i);
        db.execSQL("UPDATE TRIP_DETAILS SET BALANCE=BALANCE-"+i+" WHERE TRIP_ID='"+tId+"'");
        //db=openOrCreateDatabase("Trip",MODE_APPEND,null);

       //  db1.execSQL("insert into TRIP_DETAILS (CATEGORY,AMOUNT,EDATE,TRIP_ID)" +"values('hgfhg','200','hgfg','hvh')");
              //   " values('" + cat + "','" + Integer.parseInt(amount.getText().toString()) + "','" + date.getText().toString()+"'" +
                // ",'"+tId+"')");
        // String s="insert into TRIP_DETAILS values('katrina','645')"
      /*  ContentValues cv=new ContentValues();

        cv.put("CATEGORY", cat);
        cv.put("AMOUNT", amount.getText().toString());
        cv.put("DATE", date.getText().toString());
        cv.put("TRIP_ID", tId);
        db1.insert("TRIP_DETAILS", null, cv);
        db1.close();*/
        Toast.makeText(ExpenseActivity.this, "Data saved", Toast.LENGTH_SHORT).show();

        // tv.setText(tripId.getText().toString());
         finish();
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
        if(view == date) {
            datePickerDialog.show();
        }

    }
}

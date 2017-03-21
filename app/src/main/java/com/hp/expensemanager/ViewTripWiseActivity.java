package com.hp.expensemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewTripWiseActivity extends AppCompatActivity
{
    TableLayout tableLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_wise);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tableLayout1= (TableLayout) findViewById(R.id.tableLayout1);
        //  linearLayout1= (LinearLayout) findViewById(R.id.L1);
        // linearLayout2= (LinearLayout) findViewById(R.id.L2);

        SQLiteDatabase db = openOrCreateDatabase("Trip", MODE_APPEND, null);

        TableRow tableRow1;
        String q = "select * from TRIP_DETAILS";
        Cursor c =db.rawQuery(q, null);
        String id;
        String fromplace;
        String toplace;
        String sdate;
        String edate;
        int Balance;
        int total;
        int abudget;

        while(c.moveToNext())
        {
            id = c.getString(0);
            fromplace = c.getString(1);
            toplace=c.getString(2);
            sdate=c.getString(3);
            edate=c.getString(4);
            abudget=Integer.parseInt(c.getString(5));
            Balance=Integer.parseInt(c.getString(6));
           total=abudget;//(abudget-Balance);
            tableRow1 = new TableRow(this);
            TextView textView1 = new TextView(this);  textView1.setText(id);
            TextView textView2 = new TextView(this);  textView2.setText(fromplace);
            TextView textView3 = new TextView(this);  textView3.setText(toplace);
            TextView textView4 = new TextView(this);  textView4.setText(sdate);
            TextView textView5 = new TextView(this);  textView5.setText(edate);
            TextView textView6 = new TextView(this);  textView6.setText(""+total);
            TextView textView7 = new TextView(this);  textView7.setText(""+Balance);

            tableRow1.addView(textView1);
            tableRow1.addView(textView2);
            tableRow1.addView(textView3);
            tableRow1.addView(textView4);
            tableRow1.addView(textView5);
            tableRow1.addView(textView6);
            tableRow1.addView(textView7);
            tableLayout1.addView(tableRow1);

        }//end of while loop
        db.close();
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
}

package com.hp.expensemanager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUpdateActivity extends AppCompatActivity {
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
  Button update,delete;
    SQLiteDatabase db;
    String s,ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        editText1= (EditText) findViewById(R.id.editText1);
        editText2= (EditText) findViewById(R.id.editText2);
        editText3= (EditText) findViewById(R.id.editText3);
        editText4= (EditText) findViewById(R.id.editText4);
        editText5= (EditText) findViewById(R.id.editText5);
        editText6= (EditText) findViewById(R.id.editText6);
        update= (Button) findViewById(R.id.button1);
        delete= (Button) findViewById(R.id.button2);
        Intent intent=getIntent();
         s=intent.getStringExtra("S");
        ss="select * from TRIP_DETAILS where TRIP_ID='"+s+"' ";
        db=openOrCreateDatabase("Trip",MODE_APPEND,null);
       // String q="select * from TRIP_DETAILS ";
        Cursor c=db.rawQuery(ss, null);
        while(c.moveToNext()) {
            editText1.setText(c.getString(0));
            editText2.setText(c.getString(1));
            editText3.setText(c.getString(2));
            editText4.setText(c.getString(3));
            editText5.setText(c.getString(4));
            editText6.setText(c.getString(5));
        }



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("TRIP_ID", editText1.getText().toString());
                cv.put("FROM_PLACE", editText2.getText().toString());
                cv.put("TO_PLACE", editText3.getText().toString());
                cv.put("START_DATE", editText4.getText().toString());
                cv.put("END_DATE", editText5.getText().toString());
                cv.put("BUDGET", editText6.getText().toString());
                cv.put("BALANCE", editText6.getText().toString());
                db.update("TRIP_DETAILS", cv, "TRIP_ID=?", new String[]{s});
                db.close();
                Intent intent=new Intent(DeleteUpdateActivity.this,ViewTrip2Activity.class);
                intent.putExtra("msg", "Trip Updated");
                startActivity(intent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("delete from TRIP_DETAILS where TRIP_ID like'" + s + "'");
                db.execSQL("delete  from EXPENSE_DETAILS where TRIP_ID like'" + s + "'");
                db.close();
                Intent intent=new Intent(DeleteUpdateActivity.this,ViewTrip2Activity.class);
                intent.putExtra("msg", "Trip Deleted");
                startActivity(intent);
                finish();
            }
        });


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

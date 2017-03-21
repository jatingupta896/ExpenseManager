package com.hp.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ReportViewctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewctivity);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void tripwise(View v)
    {
        Intent i=new Intent(this,ViewTripWiseActivity.class);
        startActivity(i);
    }
    public void catwise(View v)
    {Intent i=new Intent(this,ViewCategoryWiseActivity.class);
        startActivity(i);}
    public void daywise(View v)
    {Intent i=new Intent(this,ViewDateWiseActivity.class);
        startActivity(i);}
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

package com.hp.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void newTrip(View view)
    {Intent i=new Intent(this,NewTripActivity.class);
    startActivity(i);}
    public void viewTrip(View view)
    {Intent i=new Intent(this,ViewTrip2Activity.class);
        startActivity(i);}
    public void addExpense(View view)
    {Intent i=new Intent(this,ExpenseActivity.class);
        startActivity(i);}
    public void balance(View view)
    {Intent i=new Intent(this,BalanceActivity.class);
        startActivity(i);}
  public void report(View view)
    {Intent i=new Intent(this,ReportViewctivity.class);
        startActivity(i);}
   /* public void report(View view)
    {Intent i=new Intent(this,BalanceActivity.class);
        startActivity(i);}*/
}

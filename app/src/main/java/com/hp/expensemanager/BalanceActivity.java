package com.hp.expensemanager;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BalanceActivity extends ListActivity {
    ArrayList id,from,to;
    ArrayList<Integer> budget  = new ArrayList<>();
    ArrayList<Integer> balance  = new ArrayList<>();
    String [] id1,from1,to1;
    int [] budget1,balance1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SQLiteDatabase db=openOrCreateDatabase("Trip", MODE_APPEND, null);
        String q="select * from TRIP_DETAILS";
        Cursor c=db.rawQuery(q,null);
        String tripId,fromPlace,toPlace; int tbudget,tbalance;
        id=new ArrayList();
        from=new ArrayList();
        to=new ArrayList();
        while(c.moveToNext()) {

            tripId = c.getString(0);
            id.add(tripId);
            fromPlace = c.getString(1);
            from.add(fromPlace);
            toPlace = c.getString(2);
            to.add(toPlace);
          tbudget= Integer.parseInt(c.getString(5));
            budget.add(tbudget);
            tbalance= Integer.parseInt(c.getString(6));
           balance.add(tbalance);

        }
        id1 = new String[id.size()];
        id.toArray(id1);
        from1 = new String[from.size()];
        from.toArray(from1);
        to1 = new String[to.size()];
        to.toArray(to1);
       budget1=new int[budget.size()];
        int i = 0;

        for (Integer integer : budget)
            budget1[i++] = integer;
        balance1=new int[balance.size()];
        int j = 0;

        for (Integer integer1 : balance)
            balance1[j++] = integer1;

        // budget1 = new int[integerList.size()];
     //   budget.toArray(budget1);
       // balance1=new String[balance.size()];
       // balance.toArray(balance1);
      //  int[] budget1= (int[])budget.toArray(int[budget.size()]);


        db.close();
        ArrayAdapter adapter=new MyAdapter(this,android.R.layout.simple_list_item_1,id1);
        setListAdapter(adapter);

       /* Intent i=getIntent();
        String result=i.getStringExtra("msg");*/
        // if(!(i.getStringExtra("msg").equals("")))
        //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    class MyAdapter extends ArrayAdapter
    {
        public MyAdapter(Context context, int resource, Object[] objects)
        {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = li.inflate(R.layout.activity_balance, parent, false);

            TextView tv1 = (TextView) row.findViewById(R.id.textView1);
            TextView tv2 = (TextView) row.findViewById(R.id.textView2);
            TextView tv4 = (TextView) row.findViewById(R.id.textView4);
           TextView tv5 = (TextView) row.findViewById(R.id.textView5);
           TextView tv6 = (TextView) row.findViewById(R.id.textView6);
            TextView tv7 = (TextView) row.findViewById(R.id.textView7);
            TextView tv8 = (TextView) row.findViewById(R.id.textView8);
            tv1.setText(id1[position]);
            if(position%2==0)
            { tv1.setBackgroundColor(Color.argb(255,3,169,244));
                tv7.setBackgroundColor(Color.argb(255,3,169,244));
                tv8.setBackgroundColor(Color.argb(255,3,169,244));}
            tv2.setText(from1[position]);
            tv4.setText(to1[position]);
            tv5.setText(""+budget1[position]);
            tv6.setText(""+balance1[position]);
            return row;}
    }

/*    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        SQLiteDatabase db=openOrCreateDatabase("Trip",MODE_APPEND,null);
        // String q="select * from TRIP_DETAILS where TRIP_ID='"+id1[position]+"' ";
        Intent intent=new Intent(this,DeleteUpdateActivity.class);
        intent.putExtra("S",id1[position]);
        startActivity(intent);
        finish();
    }*/

}


package com.example.leftshift.mydbcode;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class DataListActivity extends AppCompatActivity {


    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
      //  Intent intent=getIntent();

        listView= (ListView) findViewById(R.id.listView);
        listDataAdapter=new ListDataAdapter(getApplicationContext(), 0);
        listView.setAdapter(listDataAdapter);
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        cursor=userDbHelper.getInformation(sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            do
            {

                String name,mob,email;
                name= cursor.getString(0);
                mob=cursor.getString(1);
                email=cursor.getString(2);
                DataProvider dataProvider=new DataProvider(name,mob,email);
                listDataAdapter.add(dataProvider);


            }
            while (cursor.moveToNext());
        }


    }


}

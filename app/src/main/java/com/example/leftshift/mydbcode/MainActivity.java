package com.example.leftshift.mydbcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void newContact(View view)
    {
        Intent intent= new Intent(this,NewContactActivity.class);
        startActivity(intent);
    }

    public void viewContact(View view)
    {
        Intent intent=new Intent(this,DataListActivity.class);
        startActivity(intent);
    }

    public void Search(View view)
    {
        Intent intent=new Intent(this,SearchContactActivity.class);
        startActivity(intent);
    }

    public void Update(View view)
    {
        Intent intent=new Intent(this,UpdateContactActivity.class);
        startActivity(intent);
    }

    public  void deleteContact(View view)
    {
        Intent intent=new Intent(this,SearchContactActivity.class);
        startActivity(intent);
    }
}

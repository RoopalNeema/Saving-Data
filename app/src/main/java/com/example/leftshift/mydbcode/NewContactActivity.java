package com.example.leftshift.mydbcode;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewContactActivity extends AppCompatActivity {

    EditText ContactName,ContactMobile,ContactEmail;
    Context context=this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        Intent intent=getIntent();
        ContactName = (EditText) findViewById(R.id.contact_name);
        ContactMobile= (EditText) findViewById(R.id.contact_mob);
        ContactEmail= (EditText) findViewById(R.id.contact_email);
    }

    public void addContact(View view)
    {
        String name=ContactName.getText().toString();
        String mob=ContactMobile.getText().toString();
        String email=ContactEmail.getText().toString();
        userDbHelper=new UserDbHelper(context);
        sqLiteDatabase=userDbHelper.getWritableDatabase();
        userDbHelper.addInformation(name, mob, email, sqLiteDatabase);
        Toast.makeText(getBaseContext(),"DATA SAVED",Toast.LENGTH_LONG).show();
        userDbHelper.close();
        ContactName.setText("");
        ContactMobile.setText("");
        ContactEmail.setText("");
        finishActivity();
    }

    private void finishActivity()
    {
      Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}

package com.example.leftshift.mydbcode;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContactActivity extends AppCompatActivity {

    TextView Display_email,Display_mob;
    EditText Search_name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        Search_name= (EditText) findViewById(R.id.search_name);
        Display_mob= (TextView) findViewById(R.id.text_mob);
        Display_email= (TextView) findViewById(R.id.text_email);
        Display_mob.setVisibility(View.GONE);
        Display_email.setVisibility(View.GONE);
    }

   public void searchContact(View view)
   {

       search_name=Search_name.getText().toString();
       userDbHelper=new UserDbHelper(getApplicationContext());
       sqLiteDatabase=userDbHelper.getReadableDatabase();
       Cursor cursor=userDbHelper.getContact(search_name,sqLiteDatabase);
       if (cursor.moveToFirst())
       {

           String mob=cursor.getString(0);
           String  email=cursor.getString(1);
           Display_mob.setText(mob);
           Display_email.setText(email);
           Display_mob.setVisibility(View.VISIBLE);
           Display_email.setVisibility(View.VISIBLE);

       }


   }

    public void deleteContact(View view)
    {
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        userDbHelper.deleteInformatiom(search_name,sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Contact Deleted",Toast.LENGTH_LONG).show();
        Display_email.setText("");
        Display_mob.setText("");
        Search_name.setText("");
    }


}

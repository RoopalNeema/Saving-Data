package com.example.leftshift.mydbcode;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateContactActivity extends AppCompatActivity {

    EditText Name_Search,Up_Name,Up_Mob,Up_Email;
    TextView Tittle;
    Button Up_button;
    String SearchName,Newname,Newmob,NewEmail;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        Name_Search= (EditText) findViewById(R.id.sname);
        Tittle= (TextView) findViewById(R.id.Up_text);
        Up_Name= (EditText) findViewById(R.id.Upname);
        Up_Mob= (EditText) findViewById(R.id.Upmob);
        Up_Email= (EditText) findViewById(R.id.Upemail);
        Up_button= (Button) findViewById(R.id.Up_Button);

        Tittle.setVisibility(View.GONE);
        Up_Name.setVisibility(View.GONE);
        Up_Mob.setVisibility(View.GONE);
        Up_Email.setVisibility(View.GONE);
        Up_button.setVisibility(View.GONE);



    }


    public void UpSearch(View view)
    {
        SearchName=Name_Search.getText().toString();
        userDbHelper= new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        Cursor cursor=userDbHelper.getContact(SearchName,sqLiteDatabase);
        if (cursor.moveToFirst())
        {
            Newmob=cursor.getString(0);
            NewEmail=cursor.getString(1);
            Newname=SearchName;
            Up_Name.setText(Newname);
            Up_Mob.setText(Newmob);
            Up_Email.setText(NewEmail);

            Tittle.setVisibility(View.VISIBLE);
            Up_Name.setVisibility(View.VISIBLE);
            Up_Mob.setVisibility(View.VISIBLE);
            Up_Email.setVisibility(View.VISIBLE);
            Up_button.setVisibility(View.VISIBLE);

        }



    }

    public void UpdateContact(View view)
    {
        userDbHelper=new UserDbHelper(getApplicationContext());
        sqLiteDatabase=userDbHelper.getWritableDatabase();
        String Name,Mob,Email;
        Name=Up_Name.getText().toString();
        Mob=Up_Mob.getText().toString();
        Email=Up_Email.getText().toString();
        int count=  userDbHelper.updateInformation(SearchName,Name,Mob,Email,sqLiteDatabase);
        Toast.makeText(getApplicationContext(),count+"Contact Updated....",Toast.LENGTH_LONG).show();
        finish();
    }



}

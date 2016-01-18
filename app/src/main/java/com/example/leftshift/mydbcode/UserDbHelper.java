package com.example.leftshift.mydbcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by vikassingh on 11/01/16.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " + UserContract.NewUserInfo.TABLE_NAME +
                    "(" + UserContract.NewUserInfo.USER_NAME + " TEXT," +
                    UserContract.NewUserInfo.USER_MOB + " TEXT," +
                    UserContract.NewUserInfo.USER_EMAIL + " TEXT);";

    public UserDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE_OPERATIONS", "Database created/ opened.....");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE_OPERATIONS", "Table created .....");

    }

    public void addInformation(String name, String mob, String email, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME, name);
        contentValues.put(UserContract.NewUserInfo.USER_MOB, mob);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL, email);
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE_OPERATIONS", "1 row inserted.....");


    }


    public Cursor getInformation(SQLiteDatabase db)
    {
        Cursor cursor;
        String[]projections={UserContract.NewUserInfo.USER_NAME,UserContract.NewUserInfo.USER_MOB,UserContract.NewUserInfo.USER_EMAIL};
       cursor = db.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getContact(String user_name , SQLiteDatabase sqLiteDatabase)
    {
        String[] projections= {UserContract.NewUserInfo.USER_MOB, UserContract.NewUserInfo.USER_EMAIL};
        String selection=UserContract.NewUserInfo.USER_NAME+" Like ?";
        String[] selection_args={user_name};
        Cursor cursor=sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;

    }


    public int updateInformation(String oldname,String newname, String newmob,String newemail ,SQLiteDatabase db)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,newname);
        contentValues.put(UserContract.NewUserInfo.USER_MOB,newmob);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,newemail);
        String selection= UserContract.NewUserInfo.USER_NAME+"  Like ?";
        String[] selectionargs={oldname};
        int count=db.update(UserContract.NewUserInfo.TABLE_NAME, contentValues, selection, selectionargs);
        return count;
    }

    public  void deleteInformatiom(String user_name,SQLiteDatabase sqLiteDatabase)
    {
        String selection= UserContract.NewUserInfo.USER_NAME+"  LIKE ?";
        String[] selectionargs ={user_name};
        sqLiteDatabase.delete(UserContract.NewUserInfo.TABLE_NAME,selection,selectionargs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}

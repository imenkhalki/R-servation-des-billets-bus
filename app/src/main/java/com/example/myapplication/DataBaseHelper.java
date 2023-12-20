package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

 public static final String databaseName="signup_db" ;

   public DataBaseHelper(@Nullable Context context) {
      super(context ,"signup.db", null,  1 );
   }

   @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
       MyDatabase.execSQL("create table allusers (nom TEXT, prenom TEXT , num TEXT, email TEXT primary key, password TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i , int i1) {
        MyDatabase.execSQL("drop table if exists allusers");
    }

    public boolean insertData(String nom , String prenom , String num , String Email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "nom", nom );
        contentValues.put( "prenom", prenom );
        contentValues.put( "num", num );
        contentValues.put( "Email", Email);
        contentValues.put( "password", password );
        long result = MyDatabase.insert( "allusers", null, contentValues );
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

       public boolean checkemail(String email) {
           SQLiteDatabase MyDatabase = this.getWritableDatabase();
           Cursor cursor = MyDatabase.rawQuery( "select*from allusers where email= ?", new String[]{email} );
           if (cursor.getCount() > 0) {
               return true;
           } else {
               return false;
           }
       }
           public Boolean checkEmailPassword(String email, String password){
               SQLiteDatabase MyDatabase = this.getWritableDatabase();
               Cursor cursor = MyDatabase.rawQuery( "select*from allusers where email= ? and password =?",new String[] {email , password});

             if (cursor.getCount()>0)  {
                 return true;
             }else{
                 return false;
             }

           }

       }







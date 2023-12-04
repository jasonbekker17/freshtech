package com.example.freshtech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {

    Context context;
    public static final String DBNAME = "App.db";

    final String secretKey="donottouch";

    public DBHelper(@Nullable Context context) {

        super(context, "App.db", null, 1);
        this.context = context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(id INTEGER PRIMARY KEY AUTOINCREMENT, fullname TEXT, email TEXT, password VARCHAR)");
        db.execSQL("create Table meals(id INTEGER PRIMARY KEY AUTOINCREMENT, meal TEXT, ingredients TEXT, price VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists meals");


    }

    public boolean insertData(String email, String fullname, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullname", fullname);
        cv.put("email", email);
        cv.put("password", password);

        long result = db.insert("users", null, cv);
        if(result == -1) return false;
        else
            return true;


    }
    public boolean insertMeal(String meal, String ingredients, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("meal", meal);
        cv.put("ingredients", ingredients);
        cv.put("price", price);

        long result = db.insert("meals", null, cv);
        if(result == -1) return false;
        else
            return true;


    }
    

    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email = ?", new String[] {email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkemeal(String meal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from meals where meal = ?", new String[] {meal});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkemailpassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from users where email = ? and password = ?" , new String[] {email, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }



    public ArrayList<UserModal> getLoggedinUserDetails(String email){

        ArrayList<UserModal> al =new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM users WHERE email='"+email+"'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            String name = cursor.getString(1);
            String email1 = cursor.getString(2);

            UserModal userModal = new UserModal();
            userModal.setName(name);
            userModal.setEmail(email);


            al.add(userModal);

        }

        return al;

    }


    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM meals" );
    }
}


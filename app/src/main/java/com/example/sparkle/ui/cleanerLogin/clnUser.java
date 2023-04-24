package com.example.sparkle.ui.cleanerLogin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class clnUser {
    private String name;
    private String password;
    private String email;
    private String contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() { return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void Save(SQLiteDatabase db){
        try{

            ContentValues values = new ContentValues();
            values.put("name",name);
            values.put("contact",contact);
            values.put("email",email);
            values.put("password",password);
            db.insert("Cleaner",null,values);


        }
        catch(Exception ex){
            throw  ex;
        }
    }
    public boolean CheckLogin(SQLiteDatabase db){
        try {
            String query = "select email,password,name,contact from cleaner where email='"+email+"' and password='"+password+"'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {
                    if (cursor.getString(0).equals(email) && cursor.getString(1).equals(password)) {
                        return true;
                    }
                }

                while (cursor.moveToNext()) ;
            }
            return  false;
        }
        catch(Exception ex){
            throw  ex;
        }
    }


}



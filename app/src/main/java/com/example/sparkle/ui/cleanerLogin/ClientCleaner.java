package com.example.sparkle.ui.cleanerLogin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sparkle.ui.Job.ClientJob;

import java.util.ArrayList;
import java.util.List;

public class ClientCleaner {

    public ClientCleaner(){}


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




    public List<ClientCleaner> GetCleaner(SQLiteDatabase db)
    {

        try {
            List<ClientCleaner>cleanerList=new ArrayList<>();
            String query = "select email,password,name,contact from cleaner ";
            Cursor cursor= db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do{
                    ClientCleaner cleaner=new ClientCleaner();
                    cleaner.setName(cursor.getString (2));
                    cleaner.setEmail(cursor.getString(0));
                    cleaner.setContact(cursor.getString(3));


                    cleanerList.add(cleaner);



                }
                while (cursor.moveToNext());


            }
            return cleanerList;

        }catch(Exception ex)
        {
            throw ex;
        }


    }






    public void save(SQLiteDatabase db) {
    }


    public void setMoreInfo() {
    }
}





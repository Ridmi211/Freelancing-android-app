package com.example.sparkle.ui.HirerReview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class HirerReview {

    private String HName;
    private String CLName;
    private String Review;


    public HirerReview()
    {

    }

    public void setReview(String review) {
        Review = review;
    }

    public String getReview() {
        return Review;
    }

    public String getCLName() {
        return CLName;
    }

    public String getHName() {
        return HName;
    }

    public void setCLName(String CLName) {
        this.CLName = CLName;
    }

    public void setHName(String HName) {
        this.HName = HName;
    }

    public void Save(SQLiteDatabase db) {// accessing database using parameter

        try{
            ContentValues values= new ContentValues();
            values.put(" HName",HName);
            values.put(" CLName",CLName);
            values.put(" Review",Review);

            db.insert("hReview",null,values);

        }



        catch(Exception ex)
        {
            throw ex;
        }
    }
    public List<HirerReview> GetReviews(SQLiteDatabase db)
    {

        try {
            List<HirerReview>hReviewList=new ArrayList<>();
            String query = "Select hname,clname, review from hReview";
            Cursor cursor= db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do{
                   HirerReview hReview=new HirerReview();

                    hReview.setHName(cursor.getString (0));
                    hReview.setCLName(cursor.getString(1));
                    hReview.setReview(cursor.getString(2));

                    hReviewList.add(hReview);



                }
                while (cursor.moveToNext());


            }
            return hReviewList;

        }catch(Exception ex)
        {
            throw ex;
        }


    }


    public void save(SQLiteDatabase db) {
    }


}




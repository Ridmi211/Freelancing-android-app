package com.example.sparkle.ui.Job;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClientJob {

        private int ID;
        private String Name;
        private String Address;
        private String Date;
         private String Rooms;
         private String Baths;
        private String Floor;
      private String Contact;
        private String moreInfo;
        private byte[] Image;
        private String Email;

        private String Price;
    private String CleanerMail;
    private String CleanerName;


        public ClientJob()
        {

        }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setContact(String contact) {   Contact = contact; }

    public String getContact() {  return Contact;  }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setRooms(String rooms) {
        Rooms = rooms;
    }

    public String getBaths() {
        return Baths;
    }

    public void setBaths(String baths) {
        Baths = baths;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public String getCleanerMail() {
        return CleanerMail;
    }

    public String getCleanerName() {
        return CleanerName;
    }

    public void setCleanerMail(String cleanerMail) {
        CleanerMail = cleanerMail;
    }

    public void setCleanerName(String cleanerName) {
        CleanerName = cleanerName;
    }


    public void setPrice(String price) {
        this.Price = price;
    }

    public String getPrice() {
        return Price;
    }



    public void Save(SQLiteDatabase db) {// accessing database using parameter

            try{
                ContentValues values= new ContentValues();
                values.put(" Name",Name);
                values.put(" Address",Address);
                values.put("Date",Date);
                values.put("Rooms",Rooms);
                values.put(" Baths",Baths);
                values.put(" Floor",Floor);
                values.put(" Contact",Contact);
                values.put(" moreInfo",moreInfo);
                values.put("Image",Image);
                values.put("Email",Email);
                values.put("Price",Price);
                values.put("CleanerMail",CleanerMail);
                values.put("CleanerName",CleanerName);

                db.insert("Job",null,values);

            }



            catch(Exception ex)
            {
                throw ex;
            }
        }
        public List<ClientJob> GetJobs(SQLiteDatabase db)
        {

            try {
                List<ClientJob>jobList=new ArrayList<>();
                String query = "Select id,name,address,date, rooms ,baths,floor,contact,moreInfo,image,email,price,CleanerMail,CleanerName from job";
                Cursor cursor= db.rawQuery(query,null);
                if(cursor.moveToFirst())
                {
                    do{
                        ClientJob job=new ClientJob();
                        job.setID(cursor.getInt (0));
                        job.setName(cursor.getString(1));
                        job.setAddress(cursor.getString(2));
                        job.setDate(cursor.getString(3));
                        job.setRooms(cursor.getString (4));
                        job.setBaths(cursor.getString (5));
                        job.setFloor(cursor.getString(6));
                        job.setContact(cursor.getString(7));
                        job.setMoreInfo(cursor.getString(8));
                        job.setImage(cursor.getBlob(9));
                        job.setEmail(cursor.getString(10));
                        job.setPrice(cursor.getString(11));
                        job.setCleanerMail(cursor.getString(12));
                        job.setCleanerName(cursor.getString(13));
                        jobList.add(job);



                    }
                    while (cursor.moveToNext());


                }
                return jobList;

            }catch(Exception ex)
            {
                throw ex;
            }


        }
/////////////////////////////////////////calculate price


    public String Calculate (String NoR, String NoBr )
    {


            int rooms=Integer.valueOf(NoR);
            int bathRooms=Integer.valueOf(NoBr);

    int price =((rooms*500)+ (bathRooms*1000))+1000;
    String total=String.valueOf(price);

            return total;

    }








    public List<ClientJob> GetPost(SQLiteDatabase db,String Email)
    {

        try {
            List<ClientJob>jobList=new ArrayList<>();
            String mail=Email;
            String query = "Select id,name,address,date, rooms ,baths,floor,contact,moreInfo,image,email ,price,CleanerMail,CleanerName from job where email='"+mail+"'";
            Cursor cursor= db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do{
                    if (cursor.getString(10).equals(mail) ) {
                        ClientJob job = new ClientJob();
                        job.setID(cursor.getInt(0));
                        job.setName(cursor.getString(1));
                        job.setAddress(cursor.getString(2));
                        job.setDate(cursor.getString(3));
                        job.setRooms(cursor.getString(4));
                        job.setBaths(cursor.getString(5));
                        job.setFloor(cursor.getString(6));
                        job.setContact(cursor.getString(7));
                        job.setMoreInfo(cursor.getString(8));
                        job.setImage(cursor.getBlob(9));
                        job.setEmail(cursor.getString(10));
                        job.setPrice(cursor.getString(11));
                        job.setCleanerMail(cursor.getString(12));
                        job.setCleanerName(cursor.getString(13));
                        jobList.add(job);

                    }

                }
                while (cursor.moveToNext());


            }
            return jobList;

        }catch(Exception ex)
        {
            throw ex;
        }


    }

    public void Delete(SQLiteDatabase db )
        {
            try{


                db.delete("job","id="+ID,null);

            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        public void Update (SQLiteDatabase db)
        {
            try
            {
                ContentValues values= new ContentValues();
                values.put(" Name",Name);
                values.put(" Address",Address);
                values.put("Date",Date);
                values.put("Rooms",Rooms);
                values.put(" Baths",Baths);
                values.put(" Floor",Floor);
                values.put("Contact",Contact);
                values.put(" moreInfo",moreInfo);
                values.put("Image",Image);
                values.put("Email",Email);
                values.put("Price",Price);
                values.put("CleanerMail",CleanerMail);
                values.put("CleanerName",CleanerName);

                db.update("job",values,"id="+ID,null);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void save(SQLiteDatabase db) {
        }


    public void setMoreInfo() {
    }

    public List<ClientJob> GetCleaner(SQLiteDatabase db,String Email){
        try {
            List<ClientJob>  jobList = new ArrayList<>();
            String CE="";
            String query = "select id,name,address,date, rooms ,baths,floor,contact,moreInfo,image,email ,price,CleanerMail,CleanerName from job where email='"+Email+"'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {
                    if (cursor.getString(12)!=CE ) {
                        ClientJob job = new ClientJob();
                        job.setID(cursor.getInt(0));
                        job.setName(cursor.getString(1));
                        job.setAddress(cursor.getString(2));
                        job.setDate(cursor.getString(3));
                        job.setRooms(cursor.getString(4));
                        job.setBaths(cursor.getString(5));
                        job.setFloor(cursor.getString(6));
                        job.setContact(cursor.getString(7));
                        job.setMoreInfo(cursor.getString(8));
                        job.setImage(cursor.getBlob(9));
                        job.setEmail(cursor.getString(10));
                        job.setPrice(cursor.getString(11));
                        job.setCleanerMail(cursor.getString(12));
                        job.setCleanerName(cursor.getString(13));
                        jobList.add(job);
                    }

                }
                while (cursor.moveToNext());
            }
            return jobList;
        }
        catch(Exception ex){
            throw ex;
        }
    }


}



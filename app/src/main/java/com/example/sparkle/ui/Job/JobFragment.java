package com.example.sparkle.ui.Job;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sparkle.MainActivity;
import com.example.sparkle.R;
import com.example.sparkle.SharedPreference;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JobFragment extends Fragment {
    private JobViewModel mViewModel;



    EditText jobName, jobDate,jobAddress, jobFloor,jobInfo,jobRooms,jobBaths,jobContact;
    ImageView jobPic;
    Button btnPost, btnEdt,btnView;
    Calendar calendar= Calendar.getInstance();
    Bitmap pic;
    SQLiteDatabase db;
    TextView txvId;


    public static JobFragment newInstance() {
        return new JobFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_job, container, false);
        setDB();
        jobName = view.findViewById(R.id.jobName);
        jobAddress = view.findViewById(R.id.jobAddress);
        jobDate = view.findViewById(R.id. jobDate);
        jobRooms = view.findViewById(R.id.jobRooms);
        jobBaths=view.findViewById(R.id.jobBaths);
        jobFloor = view.findViewById(R.id.jobFloor);
        jobContact = view.findViewById(R.id.jobContact);
        jobInfo = view.findViewById(R.id.jobInfo);
        jobPic=view.findViewById(R.id.jobPic);
        btnPost = view.findViewById(R.id.btnPost);
        btnView = view.findViewById(R.id.btnView);
        btnEdt = view.findViewById(R.id.btnEdt);
        txvId = view.findViewById(R.id.txvId);

        if(((MainActivity)getActivity()).job!=null){
            ClientJob e=((MainActivity)getActivity()).job;

            jobName.setText(e.getName());
            jobAddress.setText(e.getAddress());
            jobDate.setText(e.getDate());
            jobRooms.setText(e.getRooms());
            jobBaths.setText(e.getBaths());
            jobFloor.setText(e.getFloor());
            jobContact.setText(e.getContact());
            jobInfo.setText(e.getMoreInfo());

            byte[] imgArray= e.getImage();
            pic= BitmapFactory.decodeByteArray(imgArray,0,imgArray.length);
            jobPic.setImageBitmap(pic);
            txvId.setText(String.valueOf(e.getID()));


        }



        DatePickerDialog.OnDateSetListener listner=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String date = "dd/mm/yyyy";
                SimpleDateFormat format =new SimpleDateFormat(date, Locale.US);
                jobDate.setText(format.format(calendar.getTime()));
            }
        };
        jobDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(view.getContext(),listner,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        ActivityResultLauncher camLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                pic =(Bitmap) intent.getExtras().get("data");
                jobPic.setImageBitmap(pic);
//
            }
        });


        ActivityResultLauncher galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                Uri selectedimage = intent.getData();



                jobPic.setImageURI(selectedimage);
                pic = ((BitmapDrawable)jobPic.getDrawable()).getBitmap();
            }
        });

        jobPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(jobPic.getContext());
                builder.setMessage("Please select an option").setTitle("Image Selection").setPositiveButton("Use the camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        camLauncher.launch(intent);
                    }
                }).setNegativeButton("Select Form Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        galleryLauncher.launch(intent);
                    }
                });
                AlertDialog dialog= builder.create();
                dialog.show();


            }
        });

//


        btnPost.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    ClientJob job = new ClientJob();
                    job.setName(jobName.getText().toString());
                    job.setAddress(jobAddress.getText().toString());
                    job.setDate(jobDate.getText().toString());
                    job.setRooms(jobRooms.getText().toString());
                    job.setBaths(jobBaths.getText().toString());
                    job.setFloor(jobFloor.getText().toString());
                    job.setContact(jobContact.getText().toString());
                    job.setMoreInfo(jobInfo.getText().toString());

                    SharedPreference preference= new SharedPreference();
                    String mail = preference.GetString(getContext(),SharedPreference.USER_EMAIL);
                    job.setEmail(mail);
//




                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    byte[] imgArray = stream.toByteArray();
                    job.setImage(imgArray);
                    String Price =job.Calculate(jobRooms.getText().toString(),jobBaths.getText().toString());
                    job.setPrice(Price);
                    job.Save(db);
                    Toast.makeText(view.getContext(), "Job Posted", Toast.LENGTH_LONG).show();


                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "Error in adding Job" + ex.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("", ex.getMessage());
                }

            }
        }));



        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClientJob job = new ClientJob();
                    job.setName(jobName.getText().toString());
                    job.setAddress(jobAddress.getText().toString());
                    job.setDate(jobDate.getText().toString());
                    job.setRooms(jobRooms.getText().toString());
                    job.setBaths(jobBaths.getText().toString());
                    job.setFloor(jobFloor.getText().toString());
                    job.setContact(jobContact.getText().toString());
                    job.setMoreInfo(jobInfo.getText().toString());

                    job.setID(Integer.valueOf(txvId.getText().toString()));


                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    byte[] imgArray = stream.toByteArray();
                    job.setImage(imgArray);

                    job.setID(Integer.valueOf(txvId.getText().toString()));


                    job.Update(db);
                    Toast.makeText(view.getContext(), "Event data updated", Toast.LENGTH_LONG).show();
                }
                catch(Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(),"Error in DB"+ex.getMessage(),Toast.LENGTH_LONG).show();
                    Log.e("",ex.getMessage());
                }
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewJobFragment fragment = new ViewJobFragment();
                getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(JobViewModel.class);
        // TODO: Use the ViewModel
    }



    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("JobDB", Context.MODE_PRIVATE, null);
            db.execSQL("Create Table if not exists job(id integer primary key Autoincrement,Name text,Address text,Date text,Rooms integer,Baths integer,Floor text,Contact text,moreInfo text," +
                    "Image blob,Email text,Price text,CleanerMail text,CleanerName text)");

        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
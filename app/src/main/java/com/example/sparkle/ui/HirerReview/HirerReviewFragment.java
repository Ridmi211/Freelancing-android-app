package com.example.sparkle.ui.HirerReview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sparkle.MainActivity;
import com.example.sparkle.R;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HirerReviewFragment extends Fragment {


    private HirerReviewViewModel mViewModel;


    EditText rvw_c_name, rvw_h_name, review_hirer;
    Button btnPostReview;
    SQLiteDatabase db;




    public static HirerReviewFragment newInstance() {
        return new HirerReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_hirer_review, container, false);
        setDB();
        rvw_h_name = view.findViewById(R.id.rvw_h_name);
        rvw_c_name = view.findViewById(R.id.rvw_c_name);
        review_hirer = view.findViewById(R.id.review_hirer);

        btnPostReview = view.findViewById(R.id.btnPostReview);







        if(((MainActivity)getActivity()).hReview!=null){
           HirerReview e=((MainActivity)getActivity()).hReview;

            rvw_h_name.setText(e.getHName());
            rvw_c_name.setText(e.getCLName());
            review_hirer.setText(e.getReview());


        }










       btnPostReview.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    HirerReview  hReview = new HirerReview();
                    hReview.setHName(rvw_h_name.getText().toString());
                    hReview.setCLName(rvw_c_name.getText().toString());
                    hReview.setReview(review_hirer.getText().toString());



                    hReview.Save(db);
                    Toast.makeText(view.getContext(), "Review added", Toast.LENGTH_LONG).show();

                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "Error in adding review" + ex.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("", ex.getMessage());
                }

            }
        }));




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HirerReviewViewModel.class);
        // TODO: Use the ViewModel
    }



    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("JobDB", Context.MODE_PRIVATE, null);
            db.execSQL("Create Table if not exists hReview(id integer primary key Autoincrement,hname text, clname text,review text)");

        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
package com.example.sparkle.ui.jobApplyCleaner;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sparkle.MainActivity;
import com.example.sparkle.R;
import com.example.sparkle.SharedPreference;
import com.example.sparkle.ui.HirerReview.HirerReview;
import com.example.sparkle.ui.HirerReview.HirerReviewFragment;
import com.example.sparkle.ui.HirerReview.HirerReviewViewModel;
import com.example.sparkle.ui.Job.ClientJob;

public class ApplyFormFragment extends Fragment {

    private ApplyFormViewModel mViewModel;






    EditText edtCleanerName, edtCleanerMail;
    Button btn_form_apply;
    SQLiteDatabase db;





    public static HirerReviewFragment newInstance() {
        return new HirerReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_apply_form, container, false);
        setDB();
        edtCleanerName= view.findViewById(R.id.edtCleanerName);
        edtCleanerMail = view.findViewById(R.id.edtCleanerMail);



        btn_form_apply = view.findViewById(R.id.btn_form_apply);







        if(((MainActivity)getActivity()).job!=null){
            ClientJob e=((MainActivity)getActivity()).job;

            edtCleanerName.setText(e.getCleanerName());
            edtCleanerMail.setText(e.getCleanerMail());



        }









        btn_form_apply.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    ClientJob  Job = new  ClientJob ();

                    Job.setCleanerName( edtCleanerName.getText().toString());
                   Job.setCleanerMail(edtCleanerMail.getText().toString());





                    Job.Save(db);
                    Toast.makeText(view.getContext(), "Job Applied", Toast.LENGTH_LONG).show();


                } catch (Exception ex) {

                    Toast.makeText(getActivity().getApplicationContext(), "Error in applying" + ex.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("", ex.getMessage());
                }

            }
        }));




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApplyFormViewModel.class);
        // TODO: Use the ViewModel
    }



    private void setDB(){
        try{
            db = getActivity().openOrCreateDatabase("JobDB", Context.MODE_PRIVATE, null);
            db.execSQL("Create Table if not exists job(id integer primary key Autoincrement,Name text,Address text,Date text,Rooms text,Baths text,Floor text,MoreInfo text," +
                    "                    Image blob, Email text,Price text,CleanerMail text,CleanerName text)");

        }
        catch (Exception ex){
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB" +ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
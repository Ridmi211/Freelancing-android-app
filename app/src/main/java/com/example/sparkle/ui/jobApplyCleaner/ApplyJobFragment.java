package com.example.sparkle.ui.jobApplyCleaner;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sparkle.R;
import com.example.sparkle.ui.HirerReview.HirerReview;
import com.example.sparkle.ui.HirerReview.HirerReviewFragment;
import com.example.sparkle.ui.Job.ClientJob;
import com.example.sparkle.ui.Job.ViewJobFragment;
import com.example.sparkle.ui.Login.LoginFragment;
import com.example.sparkle.ui.backload.FrontRegisterFragment;

import java.util.List;

public class ApplyJobFragment extends Fragment {



    SQLiteDatabase db;

    private ApplyJobViewModel mViewModel;
    private Object clnJobAdapter;


    public static ApplyJobFragment newInstance() {
        return new ApplyJobFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_apply_job, container, false);
        SetDB();

        RecyclerView recyclerView= view.findViewById(R.id.clnRcvJobs);
        ClientJob job=new ClientJob();
        List<ClientJob> jobList=job.GetJobs(db);
        clnJobAdapter adapter =new clnJobAdapter(jobList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);





        return view;

    }






    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApplyJobViewModel.class);
        // TODO: Use the ViewModel
    }


    private void SetDB()
    {
        try{
            db=getActivity().openOrCreateDatabase("JobDB", android.content.Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists job(id integer primary key Autoincrement,Name text,Address text,Date text,Rooms text,Baths text,Floor text,Contact text,MoreInfo text," +
                    "Image blob,Email text,Price text,CleanerMail text,CleanerName text)");


        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }




}
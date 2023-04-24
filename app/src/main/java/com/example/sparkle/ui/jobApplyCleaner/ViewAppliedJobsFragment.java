package com.example.sparkle.ui.jobApplyCleaner;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Toast;

import com.example.sparkle.R;
import com.example.sparkle.SharedPreference;
import com.example.sparkle.ui.Job.ClientJob;
import com.example.sparkle.ui.Job.JobAdapter;

import java.util.List;

public class ViewAppliedJobsFragment extends Fragment {

    SQLiteDatabase db;

    private ViewAppliedJobsViewModel mViewModel;

    public static ViewAppliedJobsFragment newInstance() {
        return new ViewAppliedJobsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_view_applied_jobs, container, false);

        SetDB();
        RecyclerView recyclerView= view.findViewById(R.id.appliedRcvJobs);
        ClientJob job=new ClientJob();
        SharedPreference preference= new SharedPreference();
        String mail = preference.GetString(getContext(),SharedPreference.USER_EMAIL);

        List<ClientJob> jobList=job.GetPost(db,mail);
        ConfirmAdapter adapter =new ConfirmAdapter(jobList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewAppliedJobsViewModel.class);
        // TODO: Use the ViewModel
    }


    private void SetDB()
    {
        try{
            db=getActivity().openOrCreateDatabase("JobDB", android.content.Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists job(id integer primary key Autoincrement,Name text,Address text,Date text,Rooms text,Baths text,Floor text,MoreInfo text," +
                    "Image blob, Email text,Price text,CleanerMail text,CleanerName text)");


        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }



}
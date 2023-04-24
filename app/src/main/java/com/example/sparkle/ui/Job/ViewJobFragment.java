package com.example.sparkle.ui.Job;

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

import java.util.List;

public class ViewJobFragment extends Fragment {

    SQLiteDatabase db;

    private ViewJobViewModel mViewModel;
    private Object JobAdapter;

    public static ViewJobFragment newInstance() {
        return new ViewJobFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_view_job, container, false);
        SetDB();
        RecyclerView recyclerView= v.findViewById(R.id.rcvJobs);
        ClientJob job=new ClientJob();
        SharedPreference preference= new SharedPreference();
        String mail = preference.GetString(getContext(),SharedPreference.USER_EMAIL);

        List<ClientJob> jobList=job.GetPost(db,mail);
        JobAdapter adapter =new JobAdapter(jobList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        return v; }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewJobViewModel.class);
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
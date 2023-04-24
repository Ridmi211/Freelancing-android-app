package com.example.sparkle.ui.cleanerLogin;

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
import com.example.sparkle.ui.Job.ViewJobFragment;
import com.example.sparkle.ui.Job.ViewJobViewModel;
import com.example.sparkle.ui.jobApplyCleaner.clnJobAdapter;

import java.util.List;

public class ViewCleanerFragment extends Fragment {

    SQLiteDatabase db;

    private ViewCleanerViewModel mViewModel;
    private Object CleanerAdapter;

    public static ViewCleanerFragment newInstance() {
        return new ViewCleanerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_view_cleaner, container, false);
        SetDB();
        RecyclerView recyclerView= v.findViewById(R.id.RcvCleaners);
        ClientCleaner cleaner=new ClientCleaner();
        List<ClientCleaner> CleanerList=cleaner.GetCleaner(db);
        CleanerAdapter adapter =new CleanerAdapter(CleanerList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        return v; }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewCleanerViewModel.class);
        // TODO: Use the ViewModel
    }
    private void SetDB()
    {
        try{
            db=getActivity().openOrCreateDatabase("JobDB", android.content.Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists cleaner(id integer primary key Autoincrement,email text,password text,name text,contact  text)");


        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }




}
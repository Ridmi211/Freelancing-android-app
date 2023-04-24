package com.example.sparkle.ui.HirerReview;

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

import java.util.List;

public class ViewHirerReviewFragment extends Fragment {

    SQLiteDatabase db;

    private ViewHirerReviewViewModel mViewModel;
    private Object HirerReviewAdapter;

    public static ViewHirerReviewFragment newInstance() {
        return new ViewHirerReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_view_hirer_review, container, false);
        SetDB();
        RecyclerView recyclerView= v.findViewById(R.id.rcvReviews);
        HirerReview hReview=new HirerReview();
        List<HirerReview> hReviewList=hReview.GetReviews(db);
        HirerReviewAdapter adapter =new HirerReviewAdapter(hReviewList,db);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(adapter);

        return v; }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewHirerReviewViewModel.class);
        // TODO: Use the ViewModel
    }
    private void SetDB()
    {
        try{
            db=getActivity().openOrCreateDatabase("JobDB", android.content.Context.MODE_PRIVATE,null);
            db.execSQL("Create Table if not exists hReview(id integer primary key Autoincrement,hname text, clname text,review text)");


        }
        catch(Exception ex)
        {
            Toast.makeText(getActivity().getApplicationContext(),"Error in DB"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
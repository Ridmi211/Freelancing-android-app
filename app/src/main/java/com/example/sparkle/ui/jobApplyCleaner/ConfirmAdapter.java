package com.example.sparkle.ui.jobApplyCleaner;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkle.R;
import com.example.sparkle.ui.HirerReview.HirerReview;
import com.example.sparkle.ui.HirerReview.HirerReviewAdapter;
import com.example.sparkle.ui.Job.ClientJob;
import com.example.sparkle.ui.Job.JobAdapter;

import java.util.List;

public class ConfirmAdapter extends RecyclerView.Adapter<ConfirmAdapter.ViewHolder> {

    SQLiteDatabase db;
    List<ClientJob> ConfirmList;

    public ConfirmAdapter(List<ClientJob>confirms,SQLiteDatabase _db)
    {
        ConfirmList=confirms;
        db=_db;
    }

    @NonNull
    @Override
    public  ConfirmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View eventItems=inflater.inflate(R.layout.applied_job_item,parent,false);
        ConfirmAdapter.ViewHolder holder=new  ConfirmAdapter.ViewHolder(eventItems);

        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ConfirmAdapter.ViewHolder holder, int position) {


        ClientJob confirms= ConfirmList.get(position);
        holder.frl_name.setText("Freelancer Name:  "+ confirms.getCleanerName());
        holder.frl_mail.setText("Freelancer email :  "+ confirms.getCleanerMail());






    }


    @Override
    public int getItemCount() {
        return ConfirmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  frl_name, frl_mail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frl_name=itemView.findViewById(R.id.frl_name);
            frl_mail=itemView.findViewById(R.id.frl_mail);






        }
    }
}

package com.example.sparkle.ui.jobApplyCleaner;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkle.MainActivity;
import com.example.sparkle.R;
import com.example.sparkle.ui.HirerReview.HirerReviewFragment;
import com.example.sparkle.ui.Job.ClientJob;
import com.example.sparkle.ui.backload.FrontRegisterFragment;

import java.util.List;

public class clnJobAdapter extends RecyclerView.Adapter<clnJobAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<ClientJob> jobList;


    public clnJobAdapter(List<ClientJob>jobs, SQLiteDatabase _db)
    {
        jobList=jobs;
        db=_db;
    }

    @NonNull
    @Override
    public clnJobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View eventItems=inflater.inflate(R.layout.apply_job_item,parent,false);
        clnJobAdapter.ViewHolder holder=new clnJobAdapter.ViewHolder(eventItems);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull clnJobAdapter.ViewHolder holder, int position) {
        ClientJob job= jobList.get(position);

        holder.txvName.setText("House Owner :  "+ job.getName());
        holder.txvAddress.setText("Address :  "+ job.getAddress());
        holder.txvDate.setText("Date required : "+ job.getDate());
        holder.txvRooms.setText("No.of rooms:  "+ job.getRooms());
        holder.txvBaths.setText("No.of Bathrooms : "+ job.getBaths());
        holder.txvFloor.setText("Floor type :   "+ job.getFloor());
        holder.txvContact.setText("Contact No. :   "+ job.getContact());
        holder.txvPrice.setText("Payment : LKR  "  + job.getPrice());
        holder.txvInfo.setText("Additional info:   "+ job.getMoreInfo());


        Bitmap bitmap= BitmapFactory.decodeByteArray(job.getImage(),0,job.getImage().length);
        holder.imvPostPic.setImageBitmap(bitmap);

        holder.btnViewReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HirerReviewFragment fragment =new HirerReviewFragment();
                FragmentTransaction ft=((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        holder.  btnApplyJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApplyFormFragment fragment =new  ApplyFormFragment();
                FragmentTransaction ft=((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });




    }


    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvName, txvDate,txvAddress,txvRooms,txvBaths,txvFloor,txvContact,txvInfo,txvPrice;
        ImageView imvPostPic;
        Button btnApplyJob,btnViewReviews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvPostPic=itemView.findViewById(R.id.imvPostPic);
            txvName=itemView.findViewById(R.id.txvName);
            txvAddress=itemView.findViewById(R.id.txvAddress);
            txvDate=itemView.findViewById(R.id.txvDate);
            txvRooms=itemView.findViewById(R.id.txvRooms);
            txvBaths=itemView.findViewById(R.id.txvBaths);
            txvFloor=itemView.findViewById(R.id.txvFloor);
            txvContact=itemView.findViewById(R.id.txvContact);

            txvInfo=itemView.findViewById(R.id.txvInfo);
            txvPrice=itemView.findViewById(R.id.txvPrice2);

            btnApplyJob=itemView.findViewById(R.id.btnApplyJob);
           btnViewReviews=itemView.findViewById(R.id.btnViewReviews);






        }
    }

}

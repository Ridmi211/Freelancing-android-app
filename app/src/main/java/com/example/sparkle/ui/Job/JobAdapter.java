package com.example.sparkle.ui.Job;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sparkle.MainActivity;
import com.example.sparkle.R;

import java.text.DecimalFormat;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<ClientJob> jobList;


    public JobAdapter(List<ClientJob>jobs, SQLiteDatabase _db)
    {
        jobList=jobs;
        db=_db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View eventItems=inflater.inflate(R.layout.job_item,parent,false);
        ViewHolder holder=new ViewHolder(eventItems);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClientJob job= jobList.get(position);

        holder.txvName.setText("House Owner :  "+ job.getName());
        holder.txvAddress.setText("Address :  "+ job.getAddress());
        holder.txvDate.setText("Date required : "+ job.getDate());
        holder.txvRooms.setText("No.of rooms:  "+ job.getRooms());
        holder.txvBaths.setText("No.of Bathrooms : "+ job.getBaths());
        holder.txvFloor.setText("Floor type :   "+ job.getFloor());
        holder.txvContact.setText("Contact no. :   "+ job.getContact());
        holder.txvPrice.setText("Payment : LKR"+ job.getPrice());
        holder.txvInfo.setText("Additional info:   "+ job.getMoreInfo());


        Bitmap bitmap= BitmapFactory.decodeByteArray(job.getImage(),0,job.getImage().length);
        holder.imvPostPic.setImageBitmap(bitmap);

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.imvDelete.getContext());
                builder.setMessage("Are you sure, you want to delete?" ).setTitle("Confirm Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override//alert message
                    public void onClick(DialogInterface dialogInterface, int i) {
                        job.Delete(db);
                        jobList.remove(position);
                        notifyItemRemoved(position);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which ) {


                    }
                });
                AlertDialog dialog=builder.create();
                builder.show();

            }
        });
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).job = job;
                JobFragment fragment = new JobFragment();
                FragmentTransaction ft= ((MainActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
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
        ImageButton imvEdit, imvDelete;
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
            txvPrice=itemView.findViewById(R.id.txvPrice);
            txvInfo=itemView.findViewById(R.id.txvInfo);
            imvDelete=itemView.findViewById(R.id.imvDelete);
            imvEdit=itemView.findViewById(R.id.imvEdit);





        }
    }

}

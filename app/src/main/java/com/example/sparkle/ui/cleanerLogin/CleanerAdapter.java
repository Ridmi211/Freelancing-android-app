package com.example.sparkle.ui.cleanerLogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparkle.MainActivity;
import com.example.sparkle.R;
//import com.example.sparkle.ui.CleanerReview.CleanerReviewFragment;
//import com.example.sparkle.ui.CleanerReview.ViewCleanerReviewFragment;
import com.example.sparkle.ui.HirerReview.HirerReviewFragment;
import com.example.sparkle.ui.HirerReview.ViewHirerReviewFragment;

import java.util.List;

public class CleanerAdapter extends RecyclerView.Adapter<CleanerAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<ClientCleaner> cleanerList;

    public CleanerAdapter(List<ClientCleaner> cleaners, SQLiteDatabase _db)
    {
        cleanerList=cleaners;
        db=_db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View eventItems=inflater.inflate(R.layout.cleaner_item,parent,false);
        ViewHolder holder=new ViewHolder(eventItems);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       ClientCleaner cleaner= cleanerList.get(position);
        holder.txvName.setText ( cleaner.getName());
        holder.txvEmail.setText("Email :"+ cleaner.getEmail());
        holder.txvContact.setText("Contact no. :"+ cleaner.getContact());



        holder.clnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              HirerReviewFragment fragment =new HirerReviewFragment();
                FragmentTransaction ft=((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        holder. clnViewReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewHirerReviewFragment fragment =new ViewHirerReviewFragment();
                FragmentTransaction ft=((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment_content_main,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


    }


    @Override
    public int getItemCount() {
        return cleanerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvName, txvEmail,txvContact;

        Button clnReview, clnViewReview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvName=itemView.findViewById(R.id.cleaner_name);
            txvEmail=itemView.findViewById(R.id.cleaner_email);
            txvContact=itemView.findViewById(R.id.cleaner_contact);
            clnReview=itemView.findViewById(R.id.cln_review);
            clnViewReview=itemView.findViewById(R.id.cln_view_review);




        }
    }
}


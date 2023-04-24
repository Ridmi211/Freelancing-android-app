package com.example.sparkle.ui.HirerReview;

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

import com.example.sparkle.R;
import com.example.sparkle.ui.jobApplyCleaner.clnJobAdapter;

import java.util.List;

public class HirerReviewAdapter extends RecyclerView.Adapter<HirerReviewAdapter.ViewHolder>  {
    SQLiteDatabase db;
    List<HirerReview> hReviewList;

    public HirerReviewAdapter(List<HirerReview>hReviews,SQLiteDatabase _db)
    {
        hReviewList=hReviews;
        db=_db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View eventItems=inflater.inflate(R.layout.v_hirer_review_item,parent,false);
        ViewHolder holder=new ViewHolder(eventItems);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HirerReviewAdapter.ViewHolder holder, int position) {


        HirerReview hReview= hReviewList.get(position);
        holder.txvReciever.setText( hReview.getHName());
        holder.txvSender.setText("Reviewer :  "+ hReview.getCLName());
        holder.txv_Review.setText( hReview.getReview());







    }


    @Override
    public int getItemCount() {
        return hReviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvReciever, txvSender,txv_Review;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvReciever=itemView.findViewById(R.id.txvReciever);
            txvSender=itemView.findViewById(R.id.txvSender);
            txv_Review=itemView.findViewById(R.id.txv_Review);





        }
    }
}

package com.example.collegeapp.ui.notice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        NoticeData curItem = list.get(position);

        holder.noticeTitle.setText(curItem.getTitle());
        holder.date.setText(curItem.getDate());
        holder.time.setText(curItem.getTime());


        try {

            if (!curItem.getImage().isEmpty()) {
                Glide.
                        with(context)
                        .load(curItem.getImage())
                        .into(holder.noticeImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView noticeImage;
        private TextView noticeTitle, date, time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noticeImage = itemView.findViewById(R.id.noticeImage);
            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);


        }
    }
}

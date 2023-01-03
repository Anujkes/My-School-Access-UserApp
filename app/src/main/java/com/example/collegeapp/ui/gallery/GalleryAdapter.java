package com.example.collegeapp.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {


      private Context context;
      private List<String> imageList;





    public GalleryAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.gallery_img,parent ,false);



        return new GalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {


        Glide.with(context).load(imageList.get(position)).into(holder.imageView);





    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

     ImageView imageView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.img);
        }
    }
}

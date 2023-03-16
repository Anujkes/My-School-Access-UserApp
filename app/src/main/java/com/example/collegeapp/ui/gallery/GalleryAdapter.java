package com.example.collegeapp.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.example.collegeapp.ebook.PdfViewerActivity;
import com.example.collegeapp.ui.notice.Notice_Full_Image_Activity;
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


        Glide.with(context).load(imageList.get(holder.getAdapterPosition())).into(holder.imageView);


         holder.imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context, Notice_Full_Image_Activity.class);
                 intent.putExtra("image",imageList.get(holder.getAdapterPosition()));
                 context.startActivity(intent);
             }
         });


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

package com.example.collegeapp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GalleryFragment extends Fragment {

    private RecyclerView convoRecycler,indepRecycler,othersRecycler;
    private GalleryAdapter ad;

    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_gallery, container, false);

        convoRecycler=view.findViewById(R.id.convoRecycler);
        indepRecycler=view.findViewById(R.id.indepRecycler);
       othersRecycler=view.findViewById(R.id.othersRecycler);


       reference= FirebaseDatabase.getInstance().getReference().child("Gallery");


       getConvoImg();

       getIndepImg();

       getOthersImg();




      return view;
    }
//"Select Category","Convocation","Independence Day","Others"
    private void getOthersImg() {

        List<String> imgList=new ArrayList<>();
        reference.child("Others").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
                for(DataSnapshot s:snapshot.getChildren())
                {
                    ImageData data=s.getValue(ImageData.class);
                    imgList.add(data.getImage());
                }
                
                ad=new GalleryAdapter(getContext(),imgList);
                
                othersRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                othersRecycler.setAdapter(ad);

              
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "SomeThing went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIndepImg() {

        List<String> imgList=new ArrayList<>();
        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {
                    for(DataSnapshot s:snapshot.getChildren())
                    {
                        ImageData data=s.getValue(ImageData.class);
                        imgList.add(data.getImage());
                    }

                    ad=new GalleryAdapter(getContext(),imgList);

                    indepRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                    indepRecycler.setAdapter(ad);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "SomeThing went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getConvoImg() {


        List<String> imgList=new ArrayList<>();
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot s:snapshot.getChildren())
                {
                    ImageData data=s.getValue(ImageData.class);
                    imgList.add(data.getImage());
                }

                ad=new GalleryAdapter(getContext(),imgList);

                convoRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                convoRecycler.setAdapter(ad);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "SomeThing went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
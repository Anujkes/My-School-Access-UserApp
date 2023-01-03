package com.example.collegeapp.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

  private ViewPager viewPager;
  private BranchAdpater branchAdpater;
  private List<BranchModel> list;

    private ImageView map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_about, container, false);

       list=new ArrayList<>();
       list.add(new BranchModel(R.drawable.ic_computer,"Computer Science","Branch of Computer Science Computer science is a field of study that deals with the design, development and application of computers. It focuses on the theory behind such machines as well as the science and engineering of computer hardware and software."));
       list.add(new BranchModel(R.drawable.ic_mechanical,"Mechanical ","Branch of Computer Science Computer science is a field of study that deals with the design, development and application of computers. It focuses on the theory behind such machines as well as the science and engineering of computer hardware and software."));


       branchAdpater=new BranchAdpater(getContext(),list);


         viewPager=view.findViewById(R.id.viewPager);
         viewPager.setAdapter(branchAdpater);


        ImageView imageView=view.findViewById(R.id.collegeImg);

        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/admin-college-app-d29ea.appspot.com/o/AddedeData%2F1535368937php8IauG3.jpg?alt=media&token=a5cfe72f-84a1-456b-856c-899087bf8235")
                .into(imageView);



        map=view.findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }


        });

    return view;
    }


        private void openMap() {

            Uri uri=Uri.parse("geo:0,0?q=MNNIT allahabad");


            Intent intent =new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }
}
package com.example.collegeapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.collegeapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageSlider imageSlider;
    private ImageView map;
    public HomeFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment






        View view = inflater.inflate(R.layout.fragment_home, container, false);

       imageSlider=view.findViewById(R.id.image_slider);


        ArrayList<SlideModel> imageList = new ArrayList(); // Create image list

   //          imageList.add(new SlideModel(),null);

        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/admin-college-app-d29ea.appspot.com/o/AddedeData%2F313221479_1192336431366827_4570877055794331995_n.jpg?alt=media&token=d6064577-48e5-45c1-ab56-63118e3677c7", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/admin-college-app-d29ea.appspot.com/o/AddedeData%2F315380183_1199853490615121_5447733108779427226_n.jpg?alt=media&token=d4fec923-5817-421e-93db-81f56deafb47",ScaleTypes.FIT));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/admin-college-app-d29ea.appspot.com/o/AddedeData%2F316283124_1212041846062952_4384520870257801609_n.jpg?alt=media&token=cecea81f-1a60-4cd4-909b-ec1dc4b0816f",ScaleTypes.FIT));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/admin-college-app-d29ea.appspot.com/o/AddedeData%2F317620091_1221703075096829_8730529877074683706_n.jpg?alt=media&token=726575cd-0fc5-47b2-9b78-086cb7ba8346",ScaleTypes.FIT));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/admin-college-app-d29ea.appspot.com/o/AddedeData%2F319064782_1221701138430356_4968452673423590814_n.jpg?alt=media&token=0f6ad996-3541-486e-95e9-573284038224",ScaleTypes.FIT));

        imageSlider.setImageList(imageList);


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




























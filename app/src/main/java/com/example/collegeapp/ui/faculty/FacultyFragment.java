package com.example.collegeapp.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    private RecyclerView cseDept,eceDept,eeDept,meDept,btDept,othersDept;
    private LinearLayout cseNoData,eceNoData,eeNoData,meNoData,btNoData,othersNoData;
    private DatabaseReference reference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =inflater.inflate(R.layout.fragment_faculty, container, false);

        cseDept=view.findViewById(R.id.cseDept);
        eceDept=view.findViewById(R.id.eceDept);
        eeDept=view.findViewById(R.id.eeDept);
        meDept=view.findViewById(R.id.meDept);
        btDept=view.findViewById(R.id.btDept);
        othersDept=view.findViewById(R.id.othersDept);

        cseNoData = view.findViewById(R.id.cseNoData);
        eceNoData = view.findViewById(R.id.eceNoData);
        eeNoData = view.findViewById(R.id.eeNoData);
        meNoData = view.findViewById(R.id.meNoData);
        btNoData = view.findViewById(R.id.btNoData);
        othersNoData = view.findViewById(R.id.othersNoData);
        reference= FirebaseDatabase.getInstance().getReference().child("teachers");










        cseDepartment();
        eceDepartment();
        eeDepartment();
        meDepartment();
        btDepartment();
        othersDepartment();




  return view;



}


    //"Select Category","CSE","ECE","EE","ME","BioTechnology","Others"
    private void othersDepartment() {
        DatabaseReference myRef=reference.child("Others");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
                    othersNoData.setVisibility(View.VISIBLE);
                    othersDept.setVisibility(View.GONE);
                }
                else
                {

                    othersNoData.setVisibility(View.GONE);
                    othersDept.setVisibility(View.VISIBLE);

                    List<TeacherData> list=new ArrayList();
                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        TeacherData data=snaps.getValue(TeacherData.class);
                        list.add(data);
                    }


                    othersDept.setHasFixedSize(true);
                    othersDept.setLayoutManager(new LinearLayoutManager(getContext()));

                    TeacherAdapter ad=new TeacherAdapter(list, getContext());

                    othersDept.setAdapter(ad);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });







    }

    private void btDepartment() {

        DatabaseReference myRef=reference.child("BioTechnology");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
                    btNoData.setVisibility(View.VISIBLE);
                    btDept.setVisibility(View.GONE);
                }
                else
                {

                    btNoData.setVisibility(View.GONE);
                    btDept.setVisibility(View.VISIBLE);

                    List<TeacherData> list=new ArrayList();
                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        TeacherData data=snaps.getValue(TeacherData.class);
                        list.add(data);
                    }


                    btDept.setHasFixedSize(true);
                    btDept.setLayoutManager(new LinearLayoutManager(getContext()));

                    TeacherAdapter ad=new TeacherAdapter(list, getContext());

                    btDept.setAdapter(ad);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
    private void meDepartment() {
        DatabaseReference myRef=reference.child("ME");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
                    meNoData.setVisibility(View.VISIBLE);
                    meDept.setVisibility(View.GONE);
                }
                else
                {

                    meNoData.setVisibility(View.GONE);
                    meDept.setVisibility(View.VISIBLE);

                    List<TeacherData> list=new ArrayList();
                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        TeacherData data=snaps.getValue(TeacherData.class);
                        list.add(data);
                    }


                    meDept.setHasFixedSize(true);
                    meDept.setLayoutManager(new LinearLayoutManager(getContext()));

                    TeacherAdapter ad=new TeacherAdapter(list, getContext());

                    meDept.setAdapter(ad);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void eeDepartment() {
        DatabaseReference myRef=reference.child("EE");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
                    eeNoData.setVisibility(View.VISIBLE);
                    eeDept.setVisibility(View.GONE);
                }
                else
                {

                    eeNoData.setVisibility(View.GONE);
                    eeDept.setVisibility(View.VISIBLE);

                    List<TeacherData> list=new ArrayList();
                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        TeacherData data=snaps.getValue(TeacherData.class);
                        list.add(data);
                    }


                    eeDept.setHasFixedSize(true);
                    eeDept.setLayoutManager(new LinearLayoutManager(getContext()));

                    TeacherAdapter ad=new TeacherAdapter(list, getContext());

                    eeDept.setAdapter(ad);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });















    }

    private void eceDepartment() {

        DatabaseReference myRef=reference.child("ECE");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
                    eceNoData.setVisibility(View.VISIBLE);
                    eceDept.setVisibility(View.GONE);
                }
                else
                {

                    eceNoData.setVisibility(View.GONE);
                    eceDept.setVisibility(View.VISIBLE);

                    List<TeacherData> list=new ArrayList();
                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        TeacherData data=snaps.getValue(TeacherData.class);
                        list.add(data);
                    }


                    eceDept.setHasFixedSize(true);
                    eceDept.setLayoutManager(new LinearLayoutManager(getContext()));

                    TeacherAdapter ad=new TeacherAdapter(list, getContext());

                    eceDept.setAdapter(ad);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });










    }

    private void cseDepartment() {

        DatabaseReference myRef=reference.child("CSE");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists())
                {
                    cseNoData.setVisibility(View.VISIBLE);
                    cseDept.setVisibility(View.GONE);
                }
                else
                {

                    cseNoData.setVisibility(View.GONE);
                    cseDept.setVisibility(View.VISIBLE);

                    List<TeacherData> list=new ArrayList();
                    for(DataSnapshot snaps: snapshot.getChildren())
                    {
                        TeacherData data=snaps.getValue(TeacherData.class);
                        list.add(data);
                    }


                    cseDept.setHasFixedSize(true);
                    cseDept.setLayoutManager(new LinearLayoutManager(getContext()));

                    TeacherAdapter ad=new TeacherAdapter(list, getContext());

                    cseDept.setAdapter(ad);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}